package lab6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class arrList
{
    public static String[] removeDuplicates(String[] words)
    {
        ArrayList<String> buffer= new ArrayList<>();

        for (int i = 0; i < words.length; i++)
        {
            if (!buffer.contains(words[i]))
            {
                buffer.add(words[i]);
            }
        }

        String[] ret = new String[buffer.size()];
        for (int i = 0; i < buffer.size(); i++)
        {
            ret[i] = buffer.get(i);
        }

        return ret;
    }
    public static void main(String[] args)
    {
        String[] strings = {"one", "one", "two", "three", "one", "four", "three", "one"};
        System.out.println(Arrays.toString(removeDuplicates(strings)));
    }
}
