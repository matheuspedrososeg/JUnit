package barriga.domain.builders;
import barriga.domain.Usuario;
public class UsuarioBuilder {
    private Long ID;
    private String nome;
    private String email;
    private String senha;

    private UsuarioBuilder(){}

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(UsuarioBuilder builder) {
        builder.ID = 1L;
        builder.nome = "Usuário Válido";
        builder.email = "user@mail.com";
        builder.senha = "123456";
    }

    public UsuarioBuilder comID(Long ID) {
        this.ID = ID;
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Usuario agora() {
        return new Usuario(ID, nome, email, senha);
    }
}
