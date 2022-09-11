package samuel.jose.mutantes_front.model;

public class DashboardResponse {
    private boolean sucesso;
    private String mensagem;
    private int quantidadeMutantes;
    private Habilidade[] habilidades;

    public DashboardResponse(boolean sucesso, String mensagem, int quantidadeMutantes, Habilidade[] habilidades) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.quantidadeMutantes = quantidadeMutantes;
        this.habilidades = habilidades;
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

    public int getQuantidadeMutantes() {
        return quantidadeMutantes;
    }

    public void setQuantidadeMutantes(int quantidadeMutantes) {
        this.quantidadeMutantes = quantidadeMutantes;
    }

    public Habilidade[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidade[] habilidades) {
        this.habilidades = habilidades;
    }

    public class Habilidade {
        private String habilidade;
        private int quantidade;

        public Habilidade(String habilidade, int quantidade) {
            this.habilidade = habilidade;
            this.quantidade = quantidade;
        }

        public String getHabilidade() {
            return habilidade;
        }

        public void setHabilidade(String habilidade) {
            this.habilidade = habilidade;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
    }
}
