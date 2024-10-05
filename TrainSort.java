import java.util.Scanner;

public class TrainSort {
    public static int longestTrain(int[] weights) {
        int n = weights.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1; // Each car can at least form a train of length 1 by itself
            for (int j = 0; j < i; j++) {
                if (weights[i] >= weights[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();

        System.out.println(longestTrain(weights));
    }
}
