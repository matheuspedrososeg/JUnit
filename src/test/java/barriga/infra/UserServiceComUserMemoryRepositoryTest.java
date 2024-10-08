package barriga.infra;
import barriga.domain.Usuario;
import barriga.domain.builders.UsuarioBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import barriga.domain.exceptions.ValidationException;
import barriga.service.UsuarioService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {

    private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValido() {
        Usuario user = service.salvar(UsuarioBuilder.umUsuario().comID(null).agora());
        Assertions.assertNotNull(user.getID());
    }
    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente() {
        ValidationException ex = assertThrows(ValidationException.class, () ->
            service.salvar(UsuarioBuilder.umUsuario().comID(null).agora()));
            assertEquals("Usuario user@mail.com já cadastrado.", ex.getMessage());
    }

}
