import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class exam2
{
    public static String makePalindrome(String s)
    {
        String newString = "";
        for (int i = s.length() - 1; i >= 0; i--)
        {
            newString += s.charAt(i);
        }
        return newString + s;
    }

    public static boolean sumOrDifference(int[] arr, int target)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (i != j && (arr[i] + arr[j] == target || arr[i] - arr[j] == target))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] swapFirstSecond(int[] array)
    {
        int[] firstHalf = Arrays.copyOfRange(array, 0, array.length/2);
        int[] secondHalf = Arrays.copyOfRange(array, array.length/2, array.length);
        ArrayList<Integer> reversed = new ArrayList<>();
        //return secondHalf + firstHalf;
        return new int[] {0};
    }

    public int possibleRoutes(int r, int c, int previousRoutes)
    {
        while( r > 0 || c > 0)
        {
            if (r > 0 && c > 0)
            {
                previousRoutes += 2;
                possibleRoutes(r - 1, c - 1, previousRoutes);
            } else if (r > 0 || c > 0)
            {
                previousRoutes += 1;

            }
            else
            {
                return previousRoutes;
            }
        }

        return possibleRoutes(4,5, 9);
    }

        public static void main(String[] args)
    {
        System.out.println(makePalindrome("taco"));
        int[] array = {2, 3, 5, 7};
        System.out.println(sumOrDifference(array, 4));
    }
}
