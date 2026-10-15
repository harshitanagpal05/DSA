
class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[101];
            int count = 0;

            for (int j = i; j < n; j++) {
                if (freq[nums.get(j)] == 0) {
                    count++;
                }

                freq[nums.get(j)]++;

                ans += count * count;
            }
        }

        return ans;
    }
}
