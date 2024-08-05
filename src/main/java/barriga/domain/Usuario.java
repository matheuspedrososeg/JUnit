package barriga.domain;

import barriga.domain.exceptions.ValidationException;

// AULA 18 DOMINIO: USUARIO.

public class Usuario {
    private final long ID;
    private final String nome;
    private final String email;
    private final String senha;

    public Usuario(long ID, String nome, String email, String senha) {
        if (nome == null) throw new ValidationException("Nome é obrigatório.");
        if (senha == null) throw new ValidationException("Senha é obrigatório.");
        if (email == null) throw new ValidationException("E-mail é obrigatório.");

        this.ID = ID;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public long getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

}
