package barriga.domain;

import barriga.domain.exceptions.ValidationException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha);
    }
}
