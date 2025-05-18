
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DadosAluno {

    public static List<Aluno> LoadAluno(String filePath){
        List<Aluno> alunos = new ArrayList<>();

        try(InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            int i = 0;
            while((linha = br.readLine()) != null){
                System.out.println("linha:" + i );

                String[] palavra = linha.split(",");

                for(String p: palavra){
                    System.out.println("palavra: "+ p);
                }

                int id = Integer.parseInt(palavra[0]);
                String nome = palavra[1];
                Aluno aluno = new Aluno(id,nome);
                alunos.add(aluno);
            };
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return alunos;
    }

    public static void SaveAlunos(List<Aluno> alunos, String filePath){
        try{OutputStream os = new FileOutputStream(filePath/*, true*/);
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(osw, true);
            for(Aluno aluno:alunos){
                pw.println(aluno.getNome()+","+aluno.getClass());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
