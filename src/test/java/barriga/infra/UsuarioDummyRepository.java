package barriga.infra;

import barriga.domain.Usuario;
import barriga.service.repositories.UsuarioRepository;

import java.util.Optional;

import static barriga.domain.builders.UsuarioBuilder.umUsuario;

public class UsuarioDummyRepository implements UsuarioRepository {

    @Override
    public Usuario salvar(Usuario usuario) {
        return umUsuario().
                comNome(usuario.getNome()).
                comEmail(usuario.getEmail()).
                comSenha(usuario.getSenha()).
                agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        if ("user@mail.com".equals(email)) { return Optional.of(umUsuario().comEmail(email).agora()); }
        return Optional.empty();
    }
}
