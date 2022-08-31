package samuel.jose.mutantes_front.model;

public class NovoMutanteBody {
    private NovoMutante mutante;
    private String[] habilidades;

    private class NovoMutante {
        private String nome;
        private byte[] foto;
        private String loginUsuarioCadastro;

        public NovoMutante(String nome, byte[] foto, String loginUsuarioCadastro) {
            this.nome = nome;
            this.foto = foto;
            this.loginUsuarioCadastro = loginUsuarioCadastro;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public byte[] getFoto() {
            return foto;
        }

        public void setFoto(byte[] foto) {
            this.foto = foto;
        }

        public String getLoginUsuarioCadastro() {
            return loginUsuarioCadastro;
        }

        public void setLoginUsuarioCadastro(String loginUsuarioCadastro) {
            this.loginUsuarioCadastro = loginUsuarioCadastro;
        }
    }
}
