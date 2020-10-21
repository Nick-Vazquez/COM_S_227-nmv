package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineNumberer2
{
    public static int getWordsInLine(String line)
    {
        if (line.equals(""))
        {
            return 0;
        }
        String[] splitString = line.split("\\s+");
        return splitString.length;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        //File file = new File("../lab3/src/Basketball.java");
        File file = new File("story.txt");
        Scanner scanner = new Scanner(file);
        int lineCount = 1;

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            System.out.print(getWordsInLine(line) + " Words ");
            System.out.print(lineCount + " ");
            System.out.println(line);
            lineCount += 1;
        }
        scanner.close();
    }
}