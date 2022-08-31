package samuel.jose.mutantes_front.model;

public class ListarMutantesResponse {
    private String sucesso;
    private String mensagem;
    private Mutante[] mutantes;

    public ListarMutantesResponse() {}

    public ListarMutantesResponse(String sucesso, String mensagem, Mutante[] mutantes) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.mutantes = mutantes;
    }

    public String getSucesso() {
        return sucesso;
    }

    public void setSucesso(String sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Mutante[] getMutantes() {
        return mutantes;
    }

    public void setMutantes(Mutante[] mutantes) {
        this.mutantes = mutantes;
    }
}
