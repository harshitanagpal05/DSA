class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        suffMin[n] = Integer.MAX_VALUE;

        // Prefix maximums
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        // Suffix minimums
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        // Count descents in the prefix
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1];

            if (nums[i - 1] > nums[i]) {
                prefDrop[i]++;
            }
        }

        // Check every divisor k of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                int end = start + k - 1;

                // Descents inside this block
                int drops = prefDrop[end] - prefDrop[start];

                // Circular boundary: last element -> first element
                if (nums[end] > nums[start]) {
                    drops++;
                }

                // A block must be cyclically non-decreasing
                if (drops > 1) {
                    valid = false;
                    break;
                }

                // Check that elements across blocks can be sorted
                if (prefMax[start] > suffMin[start]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}