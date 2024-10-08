package barriga.service;

import barriga.domain.Usuario;
import barriga.domain.exceptions.ValidationException;
import barriga.service.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        repository.getUserByEmail(usuario.getEmail()).ifPresent(user ->{
            throw new ValidationException(String.format("Usuario %s já cadastrado.", usuario.getEmail()));
        });
        return repository.salvar(usuario);
    }
    public Optional<Usuario> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

}
