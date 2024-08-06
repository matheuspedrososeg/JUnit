package barriga.domain.builders;
import barriga.domain.Usuario;
import barriga.domain.Conta;

    public class ContaBuilder {
        private long ID;
        private String nome;
        private Usuario usuario;

        private ContaBuilder(){}

        public static ContaBuilder umaConta() {
            ContaBuilder builder = new ContaBuilder();
            inicializarDadosPadroes(builder);
            return builder;
        }

        private static void inicializarDadosPadroes(ContaBuilder builder) {
            builder.ID = 1L;
            builder.nome = "Conta Valida`";
            builder.usuario = UsuarioBuilder.umUsuario().agora();
        }

        public ContaBuilder comID(long ID) {
            this.ID = ID;
            return this;
        }

        public ContaBuilder comNome(String nome) {
            this.nome = nome;
            return this;
        }

        public ContaBuilder comUsuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Conta agora() {
            return new Conta(ID, nome, usuario);
        }
    }


