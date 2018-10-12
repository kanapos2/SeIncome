package Database;

import Model.Income;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DBconnector {

    public static void writer(Income income){
        try {
            Path file = Paths.get("/Users/kanapos/Documents/Income/incomeDB.txt");
            BufferedWriter writer = Files.newBufferedWriter(file,
                    StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            writer.write(income.getTypeTable()+","+income.getCommentTable()+","+income.getPriceTable()+"#");



            writer.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public static String readFile(){
        String s = "";
        try {
            Path file = Paths.get("/Users/kanapos/Documents/Income/incomeDB.txt");
            BufferedReader reader = Files.newBufferedReader(file,
                    StandardCharsets.UTF_8);
            String line = null;

            while ((line = reader.readLine()) != null) {
                s+=line;

            }

            reader.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return s;
    }

    public static void writerModify(Income income){
        try {
            Path file = Paths.get("/Users/kanapos/Documents/Income/incomeDB.txt");
            BufferedWriter writer = Files.newBufferedWriter(file,
                    StandardCharsets.UTF_8);

            writer.write(income.getTypeTable()+","+income.getCommentTable()+","+income.getPriceTable()+"#");



            writer.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
