import java.util.Scanner;

public class CentSavings {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //takes in the number of items & the number of dividers
        int n = input.nextInt();
        int d = input.nextInt();

        //this next line takes in the prices for each n th item
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = input.nextInt();
        }
        //close the scanner
        input.close();
        //split the prices into d+1 groups
        int[] groups = new int[d + 1];
        //iterates over []prices and determines the assortment of d+1 groups which results in the minimum sum. rounds each group.
        for (int i = 0; i < n; i++) {
            int minIndex = 0;
            for (int j = 1; j < d; j++) {
                if (groups[j] < groups[minIndex]) {
                    minIndex = j;
                }
            }
            groups[minIndex] += prices[i];
        }
        //print the sum of the groups
        int sum = 0;
        for (int i = 0; i < d; i++) {
            sum += roundToNearest10(groups[i]);
        }
        System.out.println(sum);
    }

    public static int roundToNearest10(int value) {
        int remainder = value % 10;
        if (remainder < 5) {
            return value - remainder; // Round down
        } else {
            return value + (10 - remainder); // Round up
        }
    }
}