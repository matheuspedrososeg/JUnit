package barriga.service;

import barriga.domain.Transacao;
import barriga.service.repositories.TransacaoDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static barriga.domain.builders.TransacaoBuilder.umTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService service;
    @Mock
    private TransacaoDAO dao;

    @Test
    public void deveSalvarTransacaoValida() {
        Transacao transacaoParaSalvar = umTransacao().comId(null).agora();
        Transacao transacaoPersistida = umTransacao().agora();
        Mockito.when(dao.salvar(transacaoParaSalvar)).thenReturn(umTransacao().agora());

        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        Assertions.assertEquals(transacaoPersistida, transacaoSalva);
        assertAll("Transacao",
                () -> Assertions.assertEquals(1L, transacaoSalva.getId()),
                () -> Assertions.assertEquals("Transacao valida", transacaoSalva.getDescricao()),
                () -> {
            assertAll("Conta",
                    () -> Assertions.assertEquals("Conta Valida", transacaoSalva.getConta().getNome()),
                    () -> {
                    assertAll("Usuario",
                            () -> Assertions.assertEquals("Usuario valido", transacaoParaSalvar.getConta().getUsuario().getNome()),
                            () -> Assertions.assertEquals("123456", transacaoParaSalvar.getConta().getUsuario().getSenha())
                    );

                    }
                    );
                }
        );

    }

}
