class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] count = new int[31];

        // Count set bits in every position
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    count[i]++;
                }
            }
        }

        long ans = 0;
        int mod = 1_000_000_007;

        // Construct k maximum numbers greedily
        for (int i = 0; i < k; i++) {
            int num = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    num |= (1 << bit);
                    count[bit]--;
                }
            }

            ans = (ans + 1L * num * num) % mod;
        }

        return (int) ans;
    }
}