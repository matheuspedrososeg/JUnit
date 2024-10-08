package barriga.domain;

import barriga.domain.exceptions.ValidationException;
import lombok.Data;

@Data
public class Conta {
    private long id;
    private String nome;
    private Usuario usuario;

    public Conta(long ID, String nome, Usuario usuario) {
        if (nome == null) throw new ValidationException("Nome em branco.");
        if (usuario == null) throw new ValidationException("Usuario em branco");
        this.id = ID;
        this.nome = nome;
        this.usuario = usuario;
    }



}
