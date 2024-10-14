package barriga.service;

import barriga.domain.Conta;
import barriga.domain.Transacao;
import barriga.domain.builders.ContaBuilder;
import barriga.domain.exceptions.ValidationException;
import barriga.service.repositories.TransacaoDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static barriga.domain.builders.TransacaoBuilder.umTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransacaoServiceTest {

    @InjectMocks
    @Spy
    private TransacaoService service;
    @Mock
    private TransacaoDAO dao;

    @BeforeEach()
    public void setup() {
        when(service.getTime()).thenReturn(LocalDateTime.of(2024,1,1,4,30,15));
    }

    @Test
    public void deveSalvarTransacaoValida() {

        Transacao transacaoParaSalvar = umTransacao().comId(null).agora();
        when(dao.salvar(transacaoParaSalvar)).thenReturn(umTransacao().agora());


        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        Assertions.assertEquals(umTransacao().agora(), transacaoSalva);
        assertAll("Transacao",
                () -> Assertions.assertEquals(1L, transacaoSalva.getId()),
                () -> Assertions.assertEquals("Transacao valida", transacaoSalva.getDescricao()),
                () -> {
                    assertAll("Conta",
                            () -> Assertions.assertEquals("Conta Valida", transacaoSalva.getConta().getNome()),
                            () -> {
                                assertAll("Usuario",
                                        () -> Assertions.assertEquals("Usuário Válido", transacaoParaSalvar.getConta().getUsuario().getNome()),
                                        () -> Assertions.assertEquals("123456", transacaoParaSalvar.getConta().getUsuario().getSenha())
                                );
                            }
                    );
                }
        );
    }

    @ParameterizedTest(name = "{6}")
    @MethodSource(value = "cenariosObrigatorios")
    public void deveValidarCamposObrigatoriosAoSalvar(Long id, String descricao, Double valor, LocalDate data, Conta conta, boolean status, String mensagem) {
        String message = Assertions.assertThrows(ValidationException.class, () -> {
            Transacao transacao = umTransacao().comId(id).comDescricao(descricao)
                    .comValor(valor).comData(data).comConta(conta).comStatus(status).agora();

            service.salvar(transacao);
        }).getMessage();
        Assertions.assertEquals(mensagem, message);
    }

    @Test
    public void deveRejeitarTransacaoSemValor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Transacao transacao = umTransacao().comValor(null).agora();

        Method method = TransacaoService.class.getDeclaredMethod("validarCamposObrigatorios", Transacao.class);
        method.setAccessible(true);

        Exception ex =Assertions.assertThrows(Exception.class, () -> {
            method.invoke(new TransacaoService(), transacao);
        });
        Assertions.assertEquals("Valor Inexistente", ex.getCause().getMessage());
    }

    static Stream<Arguments> cenariosObrigatorios() {
        return Stream.of(
                Arguments.of(1L, null, 10D, LocalDate.now(), ContaBuilder.umaConta().agora(), true, "Descrição Inexistente"),
                Arguments.of(1L, "Descrição", null, LocalDate.now(), ContaBuilder.umaConta().agora(), true, "Valor Inexistente"),
                Arguments.of(1L, "Descrição", 10D, null, ContaBuilder.umaConta().agora(), true, "Data Inexistente"),
                Arguments.of(1L, "Descrição", 10D, LocalDate.now(), null, true, "Conta Inexistente")
        );
    }
//    public static boolean isHoraValida() {
//        return LocalDateTime.now().getHour() < 13;
//    }

}
