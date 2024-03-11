import java.io.FileWriter;
import java.io.IOException;
import java.io.*;


public class training{
    public static void main(String[] args){
        try{
            File ff = new File("high.txt");
            boolean su = ff.createNewFile();
            System.out.println(su);

            FileWriter fw = new FileWriter("high.txt");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}