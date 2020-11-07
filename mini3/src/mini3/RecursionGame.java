package mini3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of a search for solutions to a number game inspired
 * by the game "twenty-four".
 */
public class RecursionGame
{
  /**
   * Lists all ways to obtain the given target number using arithmetic operations
   * on the values in the given mini3.IntExpression list.  Results in string form are added to
   * the given result list, where the string form of a value is obtained from 
   * the toString() of the mini3.IntExpression object.
   * <p>
   * Special rules are: 
   * 1) you are not required to use all given numbers, and 
   * 2) division is integer division, and is only allowed when remainder is zero.  
   * For addition and multiplication, a + b and b + a are considered to be 
   * distinct solutions, and likewise a * b and b * a are considered as 
   * different solutions.  See the pdf for detailed examples.
   * @param list
   *   the values to be used in forming solutions
   * @param target
   *   the target number to be obtained from the values in the list
   * @param results
   *   list in which to place results, as strings
   */
  public static void findCombinations(ArrayList<IntExpression> list, int target, ArrayList<String> results)
  {
    // TODO
    if (list.size() == 1 && list.get(0).getIntValue() == target)
    {
      results.add(list.get(0).toString());
    }
    else
    {
      for (int x = 0; x < list.size(); x++)
      {
        ArrayList<IntExpression> newList = new ArrayList<>(list);
        newList.remove(x);
        findCombinations(newList, target, results);
      }
      for (int x = 0; x < list.size(); x++)
      {
        for (int y = 0; y < list.size(); y++)
        {
          if (x != y)
          {
            IntExpression xValue = list.get(x);
            IntExpression yValue = list.get(y);

            ArrayList<IntExpression> addList = alterArrayList(list, x, y, new IntExpression(xValue, yValue, '+'));
            findCombinations(addList, target, results);

            ArrayList<IntExpression> subList = alterArrayList(list, x, y, new IntExpression(xValue, yValue, '-'));
            findCombinations(subList, target, results);

            ArrayList<IntExpression> mulList = alterArrayList(list, x, y, new IntExpression(xValue, yValue, '*'));
            findCombinations(mulList, target, results);

            if (yValue.getIntValue() != 0 && xValue.getIntValue() % yValue.getIntValue() == 0)
            {
              ArrayList<IntExpression> divList = alterArrayList(list, x, y, new IntExpression(xValue, yValue, '/'));
              findCombinations(divList, target, results);
            }
          }
        }
      }
    }
  }

  public static ArrayList<IntExpression> alterArrayList(ArrayList<IntExpression> list, int element1,
                                                        int element2, IntExpression expression)
  {
    ArrayList<IntExpression> newList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if (i != element1 && i != element2) {
        newList.add(list.get(i));
      }
    }
    newList.add(expression);
    return newList;
  }

  public static void main(String[] args) {
    int[] values = {2, 3, 3, 5};
    ArrayList<IntExpression> expressions = new ArrayList<IntExpression>();
    for (int x : values) {
      expressions.add(new IntExpression(x));
    }
    ArrayList<String> results = new ArrayList<String>();
    findCombinations(expressions, 11, results);
    ArrayList<String> uniqueResults = new ArrayList<String>();
    for (String r : results) {
      if (!uniqueResults.contains(r)) {
        uniqueResults.add(r);
      }
    }
    Collections.sort(uniqueResults);
    for (String r : uniqueResults) {
      System.out.println(r);
    }
  }
}
