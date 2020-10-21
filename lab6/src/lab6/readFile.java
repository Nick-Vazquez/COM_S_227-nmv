package lab6;

import plotter.Plotter;
import plotter.Polyline;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile
{
    private static Polyline parseOneLine(String line)
    {
        int width = 0;
        Polyline polyline;
        String color;
        Scanner scanner = new Scanner(line);

        if (scanner.hasNextInt())
        {
            width = scanner.nextInt();
            color = scanner.next();
            polyline = new Polyline(color, width);
        }
        else
        {
            color = scanner.next();
            polyline = new Polyline(color);
        }

        while (scanner.hasNextInt())
        {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            polyline.addPoint(new Point(x, y));
        }

        return polyline;
    }

    private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException
    {
        ArrayList<Polyline> polylineArrayList= new ArrayList<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int lineCount = 1;

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();

            char firstChar = line.length() != 0 ? line.charAt(0) : '#';

            if(line.trim().length() != 0 && firstChar != '#')
            {
                polylineArrayList.add(parseOneLine(line));
            }
        }

        return polylineArrayList;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<Polyline> list = readFile("hello.txt");
        Plotter plotter = new Plotter();

        for (Polyline p : list)
        {
            plotter.plot(p);
        }
    }
}
