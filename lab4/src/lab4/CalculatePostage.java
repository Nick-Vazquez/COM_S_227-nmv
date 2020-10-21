package lab4;

import java.util.Scanner;
import postage1.PostageUtil;

public class CalculatePostage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double packageWeight;
        do {
            System.out.print("Enter the package weight: ");
            packageWeight = scanner.nextDouble();

            System.out.print("Cost to ship package: $");
            System.out.printf("%.2f\n", PostageUtil.computePostage(packageWeight));
            System.out.println("Enter 0 to quit.");
        } while (packageWeight != 0.0);
    }
}
