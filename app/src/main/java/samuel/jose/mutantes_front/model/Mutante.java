package samuel.jose.mutantes_front.model;

public class Mutante {
    private int img;
    private String nome;
    private String habilidadeUm;
    private String habilidadeDois;
    private String habilidadeTres;


    public Mutante() {}

    public Mutante(int img, String nome, String habilidadeUm, String habilidadeDois, String habilidadeTres) {
        this.img = img;
        this.nome = nome;
        this.habilidadeUm = habilidadeUm;
        this.habilidadeDois = habilidadeDois;
        this.habilidadeTres = habilidadeTres;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHabilidadeUm() {
        return habilidadeUm;
    }

    public void setHabilidadeUm(String habilidadeUm) {
        this.habilidadeUm = habilidadeUm;
    }

    public String getHabilidadeDois() {
        return habilidadeDois;
    }

    public void setHabilidadeDois(String habilidadeDois) {
        this.habilidadeDois = habilidadeDois;
    }

    public String getHabilidadeTres() {
        return habilidadeTres;
    }

    public void setHabilidadeTres(String habilidadeTres) {
        this.habilidadeTres = habilidadeTres;
    }
}
