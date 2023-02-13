package fyi.implement.code;

public class OperationToMakePalindrom {
    public static void main(String[] args) {
        String s1 = "abdc";
        String s2 = "dc";
        int minOperations = minOperations(s1, s2);
        System.out.println("Minimum number of operations: " + minOperations);
    }

    public static int minOperations(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else if (s1.charAt(i - 1) == s1.charAt(m - j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int left = 0;
        int right = m - n;
        int minOperations = Integer.MAX_VALUE;
        for (int k = 0; k <= n; k++) {
            int currentOperations = dp[left + k][right + n - k];
            if (k > 0) {
                currentOperations = Math.min(currentOperations, dp[left + k - 1][right + n - k]);
            }
            if (k < n) {
                currentOperations = Math.min(currentOperations, dp[left + k][right + n - k - 1]);
            }
            minOperations = Math.min(minOperations, currentOperations);
        }
        return minOperations;
    }
}
