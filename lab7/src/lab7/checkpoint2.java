package lab7;

import java.io.File;

public class checkpoint2 {
    public static int countFiles(File f)
    {
        int counter = 0;

        if (!f.isDirectory())
        {
            // Base case: f is a file, so just print its name
            System.out.println(f.getName());
            counter++;
        }
        else
        {
            // Recursive case: f is a directory, so go through the
            // files and directories it contains, and recursively call
            // this method on each one
            System.out.println("+ " + f.getName());
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; ++i)
            {
                counter += countFiles(files[i]);
            }
        }
        return counter;
    }

    public static int countPatterns(int n)
    {
        if (n == 1 || n == 2)
        {
            return 1;
        }
        else if (n == 3)
        {
            return 2;
        }
        else
        {
            return countPatterns(n - 1) + countPatterns(n - 3);
        }
    }



    public static void main(String[] args)
    {
        // Choose the directory you want to list.
        // If running in Eclipse, "." will just be the current project directory.
        // Use ".." to list the whole workspace directory, or enter a path to
        // some other directory.
        File rootDirectory = new File(".");

        System.out.println(countFiles(rootDirectory));
        System.out.println(countPatterns(20));
    }
}
