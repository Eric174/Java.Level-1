import java.io.*;
import java.util.Scanner;

public class Main {
    public static void stickFiles(String fileName, boolean append) {
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("total.txt", append));
            Scanner sc = new Scanner(new FileInputStream(fileName));
            while (sc.hasNext()) {
                ps.println(sc.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkWord(String fileName, String word) {
        try {
            Scanner sc = new Scanner(new FileInputStream(fileName));
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(word)) {// не будем изобретать велосипед
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        stickFiles("file1.txt", false);
        stickFiles("file2.txt", true);
        System.out.println(checkWord("total.txt", "189"));
        System.out.println(checkWord("total.txt", "loop"));
        System.out.println(checkWord("total.txt", "System"));
    }
}
