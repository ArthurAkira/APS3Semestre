public class NotasAluno {
    private int id;
    private double nota1;
    private double nota2;
    private double notaReposicao;
    private double notaExame;

    public NotasAluno() {
    }

    public NotasAluno(int id, double nota1, double nota2, double notaReposicao, double notaExame) {
        this.id = id;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.notaReposicao = notaReposicao;
        this.notaExame = notaExame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNotaReposicao() {
        return notaReposicao;
    }

    public void setNotaReposicao(double notaReposicao) {
        this.notaReposicao = notaReposicao;
    }

    public double getNotaExame() {
        return notaExame;
    }

    public void setNotaExame(double notaExame) {
        this.notaExame = notaExame;
    }

    @Override
    public String toString() {
        return  id +
                "," + nota1 +
                "," + nota2 +
                "," + notaReposicao +
                "," + notaExame;
    }
}
