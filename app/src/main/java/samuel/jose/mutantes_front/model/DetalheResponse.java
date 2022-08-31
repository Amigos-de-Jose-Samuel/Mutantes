package samuel.jose.mutantes_front.model;

public class DetalheResponse {
    private boolean sucesso;
    private String mensagem;
    private Mutante mutante;
    private Habilidade[] habilidades;

    private class Mutante {
        private int id;
        private String nome;
        private byte[] foto;
        private int idUsuario;
        private String loginUsuarioCadastro;

        public Mutante(int id, String nome, byte[] foto, int idUsuario, String loginUsuarioCadastro) {
            this.id = id;
            this.nome = nome;
            this.foto = foto;
            this.idUsuario = idUsuario;
            this.loginUsuarioCadastro = loginUsuarioCadastro;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getLoginUsuarioCadastro() {
            return loginUsuarioCadastro;
        }

        public void setLoginUsuarioCadastro(String loginUsuarioCadastro) {
            this.loginUsuarioCadastro = loginUsuarioCadastro;
        }
    }

    private class Habilidade {
        private int id;
        private String descricao;

        public Habilidade(int id, String descricao) {
            this.id = id;
            this.descricao = descricao;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    }
}
