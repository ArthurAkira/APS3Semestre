import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorNotasCurso {
    private static String nomeArquivo(String nomeCurso, String nivel, int ano){
        return nomeCurso + "_" + nivel + "_" + ano + ".csv";
    }


    public static List<NotasAluno> loadNotasCurso(String filePath) {
        List<NotasAluno> notas = new ArrayList<>();

        try(InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            int i = 0;
            while((linha = br.readLine()) != null){
                //System.out.println("linha:" + i );

                String[] palavra = linha.split(",");

                //for(String p: palavra){
                //    System.out.println("palavra: "+ p);
                //}

                int id = Integer.parseInt(palavra[0]);
                double nota1 = Double.parseDouble(palavra[1]);
                double nota2 = Double.parseDouble(palavra[2]);
                double notaReposicao = Double.parseDouble(palavra[3]);
                double notaExame = Double.parseDouble(palavra[4]);
                NotasAluno nota = new NotasAluno(id,nota1,nota2,notaReposicao,notaExame);
                notas.add(nota);
            };
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return notas;
    }

    public static void saveNotas(List<NotasAluno> notas, String filePath){
        try{OutputStream os = new FileOutputStream(filePath/*, true*/);
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(osw, true);
            for(NotasAluno nota:notas){
                pw.println(nota.getId()+","+nota.getNota1()+","+nota.getNota2()+","+nota.getNotaReposicao()+","+nota.getNotaExame());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void createArquivoVazio(String nomeCurso, String nivel, int ano) {
        String diretorio = "src/cursos/";
        String nomeArquivo = diretorio + nomeArquivo(nomeCurso, nivel, ano);

        try {
            new File(nomeArquivo).createNewFile();
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo vazio: " + e.getMessage());
        }
    }
}

