package samuel.jose.mutantes_front.model;

public class DetalheResponse {
    private boolean sucesso;
    private String mensagem;
    private Mutante mutante;
    private Habilidade[] habilidades;

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
