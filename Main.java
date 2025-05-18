import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nMenu Principal:");
                System.out.println("1 - Cadastrar aluno");
                System.out.println("2 - Listar alunos");
                System.out.println("3 - Cadastrar curso");
                System.out.println("4 - Listar cursos");
                System.out.println("5 - Registrar notas");
                System.out.println("6 - Listar notas alunos por curso");
                System.out.println("7 - Sair");
                System.out.print("Escolha uma opção: ");

                int res = sc.nextInt();

                switch(res) {
                    case 1:
                        cadastrarAluno(sc);
                        break;
                    case 2:
                        listarAlunos();
                        break;
                    case 3:
                        cadastrarCurso(sc);
                        break;
                    case 4:
                        listarCursos();
                        break;
                    case 5:
                        registrarNotas(sc);
                        break;
                    case 6:
                        listarNotasPorCurso(sc);
                        break;
                    case 7:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção de 1 a 7.");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    private static void cadastrarAluno(Scanner sc) throws Exception {
        List<Aluno> alunos = DadosAluno.LoadAluno("src/files/dadosAlunos.csv");

        System.out.print("ID: ");
        int id = sc.nextInt();
        boolean idExiste = false;

        for(Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                idExiste = true;
                break;
            }
        }

        if(idExiste) {
            System.out.println("Erro: ID já existe! Por favor, use um ID diferente");
        } else {
            System.out.print("Nome: ");
            sc.nextLine();
            String nomeAluno = sc.nextLine();
            alunos.add(new Aluno(id, nomeAluno));
            DadosAluno.SaveAlunos(alunos, "src/files/dadosAlunos.csv");
            System.out.println("Aluno cadastrado com sucesso!");
        }
    }

    private static void listarAlunos() throws Exception {
        List<Aluno> alunos = DadosAluno.LoadAluno("src/files/dadosAlunos.csv");

        if(alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado ou arquivo vazio.");
        } else {
            System.out.println("\nLista de Alunos:");
            for(Aluno aluno : alunos) {
                System.out.println(aluno);
            }
        }
    }

    private static void cadastrarCurso(Scanner sc) throws Exception {
        List<Curso> cursos = DadosCurso.LoadCurso("src/files/dadosCursos.csv");

        System.out.print("Nome do Curso: ");
        sc.nextLine();
        String nomeCurso = sc.nextLine();
        System.out.print("Nível do Curso: ");
        String nivel = sc.nextLine();
        System.out.print("Ano do Curso: ");
        int ano = sc.nextInt();

        boolean cursoExiste = false;
        for(Curso curso : cursos) {
            if((curso.getNome().equalsIgnoreCase(nomeCurso)) &&
                    (curso.getNivel().equalsIgnoreCase(nivel)) &&
                    (curso.getAno() == ano)) {
                cursoExiste = true;
                break;
            }
        }

        if(cursoExiste) {
            System.out.println("Erro: Curso já existe! Por favor, verifique todos os dados novamente");
        } else {
            Curso curso = new Curso(nomeCurso, nivel, ano);
            cursos.add(curso);
            DadosCurso.saveCurso(cursos, "src/files/dadosCursos.csv");
            GerenciadorNotasCurso.createArquivoVazio(nomeCurso, nivel, ano);
            System.out.println("Curso cadastrado com sucesso!");
        }
    }

    private static void listarCursos() throws Exception {
        List<Curso> cursos = DadosCurso.LoadCurso("src/files/dadosCursos.csv");

        if(cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado");
        } else {
            System.out.println("\nLista de Cursos:");
            for (Curso curso : cursos) {
                System.out.println(curso);
            }
        }
    }

    private static void registrarNotas(Scanner sc) throws Exception {
        System.out.print("Nome do Curso: ");
        sc.nextLine();
        String cursoNome = sc.nextLine();
        System.out.print("Nível do Curso: ");
        String cursoNivel = sc.nextLine();
        System.out.print("Ano do Curso: ");
        int cursoAno = sc.nextInt();

        List<NotasAluno> notas = GerenciadorNotasCurso.loadNotasCurso(
                "src/cursos/" + cursoNome + "_" + cursoNivel + "_" + cursoAno + ".csv");

        System.out.print("ID do Aluno: ");
        int idAluno = sc.nextInt();

        boolean existeID = false;
        for(NotasAluno nota : notas) {
            if (nota.getId() == idAluno) {
                existeID = true;
                break;
            }
        }

        if(existeID) {
            System.out.println("ERRO: notas já foram registradas anteriormente para este aluno");
        } else {
            System.out.print("Nota 1: ");
            double nota1 = sc.nextDouble();
            System.out.print("Nota 2: ");
            double nota2 = sc.nextDouble();
            System.out.print("Nota de Reposição: ");
            double notaReposicao = sc.nextDouble();
            System.out.print("Nota de Exame: ");
            double notaExame = sc.nextDouble();

            notas.add(new NotasAluno(idAluno, nota1, nota2, notaReposicao, notaExame));
            GerenciadorNotasCurso.saveNotas(notas,
                    "src/cursos/" + cursoNome + "_" + cursoNivel + "_" + cursoAno + ".csv");
            System.out.println("Notas registradas com sucesso!");
        }
    }

    private static void listarNotasPorCurso(Scanner sc) throws Exception {
        System.out.print("Nome do Curso: ");
        sc.nextLine();
        String cursoNome = sc.nextLine();
        System.out.print("Nível do Curso: ");
        String cursoNivel = sc.nextLine();
        System.out.print("Ano do Curso: ");
        int cursoAno = sc.nextInt();

        List<NotasAluno> notaAluno = GerenciadorNotasCurso.loadNotasCurso(
                "src/cursos/" + cursoNome + "_" + cursoNivel + "_" + cursoAno + ".csv");

        if(notaAluno.isEmpty()) {
            System.out.println("Curso sem alunos ou notas registradas");
        } else {
            System.out.println("\nNotas dos Alunos no Curso:");
            for(NotasAluno nota : notaAluno) {
                System.out.println(nota);
            }
        }
    }
}