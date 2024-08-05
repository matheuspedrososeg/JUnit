package barriga.domain;

import barriga.domain.builders.UsuarioBuilder;
import barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domínio: Usuário")
public class UsuarioTeste {

    @Test
    @DisplayName("Deve criar o usuário válido.")
    public void deveCriarUsuarioValido() {
        Usuario usuario = umUsuario().agora();
        assertAll("Usuario", () -> assertEquals(1L, usuario.getID()),
                () -> assertEquals("Usuário Válido", usuario.getNome()),
                () -> assertEquals("user@mail.com", usuario.getEmail()),
                () -> assertEquals("123456" , usuario.getSenha())
        );
    }

    // deveCriarUsuarioValido refatorado para utilizar lambdas pois ao decorrer do teste, é melhor
    // saber quais campos estao errados, em vez de ir achando um por um.

    @Test
    public void deveRejeitarUsuarioSemNome() {
        ValidationException ex = assertThrows(ValidationException.class, () ->
            umUsuario().comNome(null).agora());
            assertEquals("Nome é obrigatório.", ex.getMessage());
    }
    @Test
    public void deveRejeitarUsuarioSemEmail() {
        ValidationException ex = assertThrows(ValidationException.class, () ->
                umUsuario().comEmail(null).agora());
        assertEquals("E-mail é obrigatório.", ex.getMessage());
    }
    @Test
    public void deveRejeitarUsuarioSemSenha() {
        ValidationException ex = assertThrows(ValidationException.class, () ->
                umUsuario().comSenha(null).agora());
        assertEquals("Senha é obrigatório.", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource (strings = {"Teste1", "Teste2","Teste 3"})
    public void testStrings(String params) {
        System.out.println(params);
        assertNotNull(params);
    }
}
