package samuel.jose.mutantes_front.model;

public class EditarBody {
    private Mutante mutante;
    private String[] habilidades;

    public EditarBody(Mutante mutante, String[] habilidades) {
        this.mutante = mutante;
        this.habilidades = habilidades;
    }

    public Mutante getMutante() {
        return mutante;
    }

    public void setMutante(Mutante mutante) {
        this.mutante = mutante;
    }

    public String[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String[] habilidades) {
        this.habilidades = habilidades;
    }
}
