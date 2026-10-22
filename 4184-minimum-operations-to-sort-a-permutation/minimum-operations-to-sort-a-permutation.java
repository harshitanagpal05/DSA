
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean increasingRight = true;
        boolean increasingLeft = true;

        for (int i = 1; i < n; i++) {
            int right = (zero + i) % n;
            int left = (zero - i + n) % n;

            // From 0, moving right should give 1, 2, 3...
            if (nums[right] != i) {
                increasingRight = false;
            }

            // From 0, moving left should give 1, 2, 3...
            if (nums[left] != i) {
                increasingLeft = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasingRight) {
            // Rotate left zero times/positions to bring 0 to front
            ans = Math.min(ans, zero);

            // Alternative using two reversals
            ans = Math.min(ans, n - zero + 2);
        }

        if (increasingLeft) {
            // Reverse and rotate, or rotate and reverse
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
