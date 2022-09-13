package samuel.jose.mutantes_front.model;

public class NovoMutanteBody {
    private MutanteDB mutante;
    private String[] habilidades;

    public NovoMutanteBody(MutanteDB mutanteDB, String[] habilidadesArray) {
        this.mutante = mutanteDB;
        this.habilidades = habilidadesArray;
    }
}
