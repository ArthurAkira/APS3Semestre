public class Curso {
    private String nome;
    private String nivel;
    private int ano;

    public Curso(){}
    public Curso(String nome, String nivel, int ano){
        this.nome = nome;
        this.nivel = nivel;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + "," + "Nivel: " + nivel + "," + "Ano: " + ano;
    }
}
