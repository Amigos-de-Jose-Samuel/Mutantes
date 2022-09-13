package samuel.jose.mutantes_front.model;

public class MutanteDB {
    private int id;
    private String nome;
    private byte[] foto;
    private int idUsuario = 1;
    private String loginUsuarioCadastro;

    public MutanteDB(String nome, String loginUsuarioCadastro, byte[] foto) {
        this.nome = nome;
        this.loginUsuarioCadastro = loginUsuarioCadastro;
        this.foto = foto;
    }

    public MutanteDB(int id, String nome, byte[] foto, int idUsuario, String loginUsuarioCadastro) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.idUsuario = idUsuario;
        this.loginUsuarioCadastro = loginUsuarioCadastro;
    }

    public MutanteDB(int idMutante, String nome, byte[] image) {
        this.id = idMutante;
        this.nome = nome;
        this.foto = image;
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
