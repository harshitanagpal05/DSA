class Solution {
    public int numDistinct(String s, String t) {

        int m = s.length(); // length of s
        int n = t.length(); // length of t

        // dp[i][j] =
        // number of ways to form first j characters of t
        // using first i characters of s
        int[][] dp = new int[m + 1][n + 1];

        // Empty t can always be formed by choosing nothing
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // Go through s
        for (int i = 1; i <= m; i++) {

            // Go through t
            for (int j = 1; j <= n; j++) {

                // Characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    // 1. Take the character
                    // 2. Don't take the character
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

                } else {

                    // Characters don't match,
                    // so skip s's current character
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Answer for complete s and complete t
        return dp[m][n];
    }
}