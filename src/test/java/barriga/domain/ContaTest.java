package barriga.domain;

import barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static barriga.domain.builders.ContaBuilder.umaConta;
import static barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    @Test
    public void deveCriarContaValida() {
        Conta conta = umaConta().agora();
        assertAll("Conta",
                () -> assertEquals(1L, conta.id()),
                () -> assertEquals("Conta Valida", conta.nome()),
                () -> assertEquals(umUsuario().agora(), conta.usuario())
        );
    }
    @ParameterizedTest ( name = "{index} - {3}")
    @MethodSource(value = "dataProvider")
    public void deveRejeitarContaInvalida(long id, String nome, Usuario usuario, String mensagem) {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> umaConta().comID(id).comNome(nome).comUsuario(usuario).agora());
        assertEquals(mensagem, ex.getMessage());
    }
    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, umUsuario().agora(), "Nome em branco."),
                Arguments.of(1L, "Conta Valida", null, "Usuario em branco")
        );
    }
}
