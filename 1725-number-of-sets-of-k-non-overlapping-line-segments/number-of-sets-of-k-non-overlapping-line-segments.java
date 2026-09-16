class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;

        int N = n + k - 1;
        int R = 2 * k;

        long ans = 1;

        // C(N, R)
        for (int i = 1; i <= R; i++) {
            ans = ans * (N - R + i) % MOD;
            ans = ans * modInverse(i, MOD) % MOD;
        }

        return (int) ans;
    }

    private long modInverse(long x, long MOD) {
        return power(x, MOD - 2, MOD);
    }

    private long power(long a, long b, long MOD) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}