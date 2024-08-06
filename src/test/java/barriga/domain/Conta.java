package barriga.domain;

import barriga.domain.exceptions.ValidationException;

public class Conta {
    private long ID;
    private String nome;
    private Usuario usuario;

    public Conta(long ID, String nome, Usuario usuario) {
        if (nome == null) throw new ValidationException("Nome em branco.");
        if (usuario == null) throw new ValidationException("Usuario em branco");
        this.ID = ID;
        this.nome = nome;
        this.usuario = usuario;
    }
    public long id() {
        return ID;
    }
    public String nome() {
        return nome;
    }
    public Usuario usuario() {
        return usuario;
    }



}
