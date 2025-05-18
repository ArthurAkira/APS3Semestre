import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DadosCurso {
     public static List<Curso> LoadCurso(String filePath){
         List<Curso> cursos = new ArrayList<>();

         try(InputStream is = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr);
         ) {
             String linha;
             int i = 0;
            while((linha = br.readLine()) != null){
                System.out.println("linha " + i++);

                String[] palavras = linha.split(",");

                for(String p: palavras){
                    System.out.println("palavra: " + p);
                }

                String nome = palavras[0];
                String nivel = palavras[1];
                int ano = Integer.parseInt(palavras[2]);
                Curso curso = new Curso(nome, nivel, ano);
                cursos.add(curso);
            }
         }
         catch (IOException e){
            e.printStackTrace();
         }
         return cursos;
     }

     public static void saveCurso(List <Curso> cursos, String filepath){
        try(    OutputStream os = new FileOutputStream(filepath /*, true */);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true)
        ){
            for(Curso curso:cursos){
                pw.println(curso.getNome()+","+curso.getNivel()+","+curso.getAno());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}