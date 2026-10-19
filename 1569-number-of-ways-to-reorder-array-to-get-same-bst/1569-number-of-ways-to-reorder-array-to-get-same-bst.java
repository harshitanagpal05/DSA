
class Solution {
    static final int MOD = 1_000_000_007;
    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations using Pascal's triangle
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((dfs(nums) - 1 + MOD) % MOD);
    }

    private long dfs(int[] nums) {
        int n = nums.length;

        // Empty or single-node subtree: one arrangement
        if (n <= 2) {
            return 1;
        }

        int root = nums[0];

        int[] left = new int[n];
        int[] right = new int[n];

        int l = 0, r = 0;

        // Divide nodes according to the BST root
        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left[l++] = nums[i];
            } else {
                right[r++] = nums[i];
            }
        }

        // Recursively count valid arrangements
        long leftWays = dfs(Arrays.copyOf(left, l));
        long rightWays = dfs(Arrays.copyOf(right, r));

        // Interleave left and right subtree elements
        long interleavings = comb[n - 1][l];

        return interleavings * leftWays % MOD
                              * rightWays % MOD;
    }
}
