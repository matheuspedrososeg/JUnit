package barriga.service;

import barriga.domain.Usuario;
import barriga.domain.builders.UsuarioBuilder;
import barriga.domain.exceptions.ValidationException;
import barriga.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static barriga.domain.builders.UsuarioBuilder.umUsuario;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    private UsuarioService service;

//    @AfterEach
//    public void tearDown(){
//        Mockito.verifyNoMoreInteractions(repository);
//    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente() {

        Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());

        verify(repository).getUserByEmail("mail@mail.com");
    }

    @Test
    public void deveRetornarUsuarioPorEmail() {


        Mockito.when(repository.getUserByEmail("mail@mail.com"))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()), Optional.of(UsuarioBuilder.umUsuario().agora()));

        Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
        System.out.println(user);
        user = service.getUserByEmail("mail@mail.com");
        System.out.println(user);
        Assertions.assertTrue(user.isPresent());



        Mockito.verify(repository, Mockito.atLeast(1)).getUserByEmail("mail@mail.com");
//        Mockito.verify(repository, Mockito.never()).getUserByEmail("outroEmail@mail.com");
    }

    @Test
    public void deveSalvarUsuarioComSucesso() {
        Usuario userTosave = umUsuario().comID(null).agora();

        when(repository.getUserByEmail(userTosave.getEmail())).thenReturn(Optional.empty());
        when(repository.salvar(userTosave)).thenReturn(umUsuario().agora());

        Usuario savedUser =service.salvar(umUsuario().comID(null).agora());
        Assertions.assertNotNull(savedUser.getID());

        verify(repository).getUserByEmail(userTosave.getEmail());
        verify(repository).salvar(userTosave);
    }
    @Test
    public void deveRejeitarUsuarioExistente() {
        Usuario userToSave = umUsuario().comID(null).agora();
        when(repository.getUserByEmail(userToSave.getEmail())).thenReturn(Optional.of(umUsuario().agora()));

        ValidationException ex =Assertions.assertThrows(ValidationException.class, () ->
            service.salvar(userToSave));
            Assertions.assertTrue(ex.getMessage().endsWith("já cadastrado."));

            verify(repository, Mockito.never()).salvar(userToSave);
    }
}
