
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] sumSq;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();

        long answer = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int prev = last.getOrDefault(x, -1);

            // Distinct count increases for starting indices
            // from prev + 1 through r.
            update(1, 0, n - 1, prev + 1, r, 1);

            // Sum of squares for all subarrays ending at r.
            answer = (answer + sumSq[1]) % MOD;

            last.put(x, r);
        }

        return (int) answer;
    }

    // Add delta to every value in this segment.
    private void apply(int node, int left, int right, long delta) {
        long len = right - left + 1L;

        // (a + delta)^2 = a^2 + 2*delta*a + delta^2
        sumSq[node] = (
            sumSq[node]
            + 2L * delta * sum[node]
            + delta * delta % MOD * len
        ) % MOD;

        sum[node] = (
            sum[node] + delta * len
        ) % MOD;

        lazy[node] += delta;
    }

    // Propagate ALL pending increments.
    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        long pending = lazy[node];

        apply(node * 2, left, mid, pending);
        apply(node * 2 + 1, mid + 1, right, pending);

        lazy[node] = 0;
    }

    private void pull(int node) {
        sum[node] = (
            sum[node * 2] + sum[node * 2 + 1]
        ) % MOD;

        sumSq[node] = (
            sumSq[node * 2] + sumSq[node * 2 + 1]
        ) % MOD;
    }

    // Add delta over [ql, qr].
    private void update(
        int node, int left, int right,
        int ql, int qr, long delta
    ) {
        if (qr < left || right < ql) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, delta);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr, delta);
        update(node * 2 + 1, mid + 1, right, ql, qr, delta);

        pull(node);
    }
}
