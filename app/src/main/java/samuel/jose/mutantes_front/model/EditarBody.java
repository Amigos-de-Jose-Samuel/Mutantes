package samuel.jose.mutantes_front.model;

public class EditarBody {
    private MutanteDB mutante;
    private String[] habilidades;

    public EditarBody(MutanteDB mutanteDB, String[] habilidades) {
        this.mutante = mutanteDB;
        this.habilidades = habilidades;
    }

    public MutanteDB getMutante() {
        return mutante;
    }

    public void setMutante(MutanteDB mutanteDB) {
        this.mutante = mutanteDB;
    }

    public String[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String[] habilidades) {
        this.habilidades = habilidades;
    }
}
