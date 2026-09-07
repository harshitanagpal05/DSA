class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1000000007;
        int n = s.length();

        long[] dp = new long[n + 1];

        // dp[0] = empty subsequence
        dp[0] = 1;

        int[] last = new int[26];

        for (int i = 1; i <= n; i++) {

            int ch = s.charAt(i - 1) - 'a';

            // Double all previous subsequences
            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicates
            if (last[ch] != 0) {
                dp[i] = (dp[i] - dp[last[ch] - 1] + MOD) % MOD;
            }

            // Current character's latest position
            last[ch] = i;
        }

        // Remove empty subsequence
        return (int)((dp[n] - 1 + MOD) % MOD);
    }
}