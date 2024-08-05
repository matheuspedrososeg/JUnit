package barriga.domain.builders;

import barriga.domain.Usuario;

public class UsuarioBuilderOld {
    private long ID;
    private String nome;
    private String email;
    private String senha;


    private UsuarioBuilderOld() {}

    public static UsuarioBuilderOld umUsuario() {
        UsuarioBuilderOld builder = new UsuarioBuilderOld();
        inicializarDadosPadroes(builder);

        return builder;
    }

    private static void inicializarDadosPadroes(UsuarioBuilderOld builder) {
        builder.ID = 1L;
        builder.nome = "Usuário Válido";
        builder.email = "user@mail.com";
        builder.senha = "123456";
    }

    public UsuarioBuilderOld comID(Long param) {
        ID = param;
        return this;
    }
    public UsuarioBuilderOld comNome(String param) {
        nome = param;
        return this;
    }
    public UsuarioBuilderOld comEmail(String param) {
        email = param;
        return this;
    }
    public UsuarioBuilderOld comSenha(String param) {
        senha = param;
        return this;
    }

    public Usuario agora() {
        return new Usuario(ID, nome, email, senha);
    }


}
