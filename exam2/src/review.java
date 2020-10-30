import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class review {

    public static int maxOfArray(int[] arr)
    {
        if (arr.length == 1)
        {
            return arr[0];
        }
        else
        {
            int[] leftHalf = Arrays.copyOfRange(arr, 0,arr.length / 2);
            int[] rightHalf = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
            int maxLeft = maxOfArray(leftHalf);
            int maxRight = maxOfArray(rightHalf);
            return Math.max(maxLeft, maxRight);
        }
    }

    public static String longestWord(String str)
    {
        String largestWord = "";
        Scanner scanner = new Scanner(str);
        for (int i = 0; scanner.hasNext(); i++)
        {
            String word = scanner.next();
            if (word.length() > largestWord.length())
            {
                largestWord = word;
            }
        }

        return largestWord;
    }

    public static void removeComments() throws FileNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of your file!");
        String filename = scanner.next();

        File inputFile = new File(filename);
        System.out.println("New Filename: " + filename.replace(".java", ".out"));

        File outputFile = new File(filename.replace(".java", ".out"));
        PrintWriter outWriter = new PrintWriter(outputFile);

        Scanner inputScanner = new Scanner(inputFile);

        for (int i = 0; inputScanner.hasNextLine(); i++)
        {
            String currentLine = inputScanner.nextLine();
            int commentIndex = currentLine.indexOf("//");
            if (commentIndex < 0)
            {
                outWriter.println(currentLine);
            }
            else
            {
                outWriter.println(currentLine.substring(0, commentIndex));
            }
        }
        scanner.close();
        inputScanner.close();
        outWriter.close();
    }

    public static String getPassword()
    {
        Scanner scanner = new Scanner(System.in);

        String password1;
        String password2;

        do
        {
            System.out.println("Please enter your password.");
            password1 = scanner.next();
            System.out.println("Please verify your password.");
            password2 = scanner.next();
        }
        while (!password1.equals(password2));

        return password1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[] arr = new int[]{2, 5, 8, 25, 4, 64, 8};
        System.out.println(maxOfArray(arr));
        String str = "The quick brown fox jumped over the lazy dog.";
        System.out.println(longestWord(str));
        //removeComments();
        System.out.println(getPassword());
    }
}
