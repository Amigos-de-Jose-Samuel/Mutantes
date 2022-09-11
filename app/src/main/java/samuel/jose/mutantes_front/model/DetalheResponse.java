package samuel.jose.mutantes_front.model;

public class DetalheResponse {
    private boolean sucesso;
    private String mensagem;
    private Mutante mutante;
    private Habilidade[] habilidades;

    public class Habilidade {
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

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Mutante getMutante() {
        return mutante;
    }

    public void setMutante(Mutante mutante) {
        this.mutante = mutante;
    }

    public Habilidade[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidade[] habilidades) {
        this.habilidades = habilidades;
    }
}
