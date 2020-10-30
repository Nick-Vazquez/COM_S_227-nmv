package lab7;

import java.util.Arrays;

public class checkpoint1 {
    public static int getPyramidCount(int numLevels)
    {
        if (numLevels == 1)
        {
            return 1;
        }
        else
        {
            int balls = (int) Math.pow(numLevels, 2);
            return balls + getPyramidCount(numLevels - 1);
        }
    }

    public static int arrMaxVal(int[] valArray)
    {
        if (valArray.length == 1)
        {
            return valArray[0];
        }
        else
        {
            int mid = valArray.length / 2;
            int[] leftSide = Arrays.copyOfRange(valArray, 0, mid);
            int[] rightSide = Arrays.copyOfRange(valArray, mid, valArray.length);
            int maxLeft = arrMaxVal(leftSide);
            int maxRight = arrMaxVal(rightSide);
            return Math.max(maxLeft, maxRight);
        }
    }

    public static void main(String[] args) {
        System.out.println(getPyramidCount(8));
        System.out.println(arrMaxVal(new int[]{1, 2, 8, 7, 5, 98, 97, 2}));
    }
}
