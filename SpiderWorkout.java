import java.util.Arrays;
import java.util.Scanner;

public class SpiderWorkout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // Number of test scenarios
        scanner.nextLine(); // Consume the newline character

        for (int scenario = 0; scenario < N; scenario++) {
            int M = scanner.nextInt(); // Number of distances
            scanner.nextLine(); // Consume the newline character
            int[] distances = new int[M];
            for (int i = 0; i < M; i++) {
                distances[i] = scanner.nextInt();
            }
            scanner.nextLine(); // Consume the newline character

            // Maximum possible height we might need to consider
            int maxPossibleHeight = Arrays.stream(distances).sum() + 1;

            // DP table
            int[][] dp = new int[M + 1][maxPossibleHeight];
            for (int[] row : dp) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            dp[0][0] = 0;

            // Path table to store the sequence of "U" and "D"
            char[][] path = new char[M + 1][maxPossibleHeight];

            // Fill the DP table
            for (int i = 0; i < M; i++) {
                for (int h = 0; h < maxPossibleHeight; h++) {
                    if (dp[i][h] != Integer.MAX_VALUE) {
                        // Climb up
                        int upHeight = h + distances[i];
                        if (upHeight < maxPossibleHeight) {
                            if (dp[i + 1][upHeight] > Math.max(dp[i][h], upHeight)) {
                                dp[i + 1][upHeight] = Math.max(dp[i][h], upHeight);
                                path[i + 1][upHeight] = 'U';
                            }
                            scanner.close();
                        }
                        // Climb down
                        int downHeight = h - distances[i];
                        if (downHeight >= 0) {
                            if (dp[i + 1][downHeight] > dp[i][h]) {
                                dp[i + 1][downHeight] = dp[i][h];
                                path[i + 1][downHeight] = 'D';
                            }
                        }
                    }
                }
            }

            // Check if we can end at street level (height 0)
            if (dp[M][0] == Integer.MAX_VALUE) {
                System.out.println("IMPOSSIBLE");
            } else {
                // Reconstruct the path
                StringBuilder result = new StringBuilder();
                int currentHeight = 0;
                for (int i = M; i > 0; i--) {
                    result.append(path[i][currentHeight]);
                    if (path[i][currentHeight] == 'U') {
                        currentHeight -= distances[i - 1];
                    } else {
                        currentHeight += distances[i - 1];
                    }
                }
                System.out.println(result.reverse().toString());
            }
        }
    }
}
