class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

        long k = (long) k1 + k2;

        int maxDiff = 100000;

        long[] freq = new long[maxDiff + 1];

        // Calculate differences
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
        }

        // Reduce largest differences first
        for (int d = maxDiff; d > 0 && k > 0; d--) {

            if (freq[d] == 0) {
                continue;
            }

            // Number of differences at value d
            long use = Math.min(k, freq[d]);

            // Reduce 'use' elements from d to d - 1
            freq[d] -= use;
            freq[d - 1] += use;

            k -= use;
        }

        // Calculate answer
        long ans = 0;

        for (int d = 1; d <= maxDiff; d++) {
            ans += freq[d] * d * d;
        }

        return ans;
    }
}