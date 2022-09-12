package samuel.jose.mutantes_front.model;

public class NovoMutanteBody {
    private Mutante mutante;
    private String[] habilidades;

    public NovoMutanteBody(Mutante mutante, String[] habilidadesArray) {
        this.mutante = mutante;
        this.habilidades = habilidadesArray;
    }
}
