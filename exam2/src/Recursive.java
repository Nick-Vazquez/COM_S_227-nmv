import java.io.File;
import java.util.ArrayList;

public class Recursive {
    /**
     * A child named Beatrice is jumping along on a floor consisting of
     * rectangular tiles. She can jump one tile, two tiles, or three
     * tiles at a time. Write a recursive method to determine the
     * number of different ways she can cross n tiles.
     * @param tiles
     * @return
     */
    public static int crossTiles(int tiles)
    {
        if (tiles == 1 || tiles == 2)
        {
            return tiles;
        }

        else if (tiles == 3)
        {
            return 4;
        }

        else
        {
            return crossTiles(tiles - 3) + crossTiles(tiles - 2) + crossTiles(tiles - 1);
        }
    }

    public static int routesToDest(int r, int c)
    {
        if (r == 0 && c == 0)
        {
            return 0;
        }

        if ((r == 0 && c > 0) || (r > 0 && c == 0))
        {
            return 1;
        }

        else
        {
            return routesToDest(r - 1, c) + routesToDest(r, c - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(routesToDest(2, 2));
    }

    private void findJavaFiles(File file)
    {
        // If is not a directory
        if (!file.isDirectory() && file.getName().endsWith(".java"))
        {
            System.out.println(file.getName());
        }

        else
        {
            findJavaFiles();
        }
    }
}
