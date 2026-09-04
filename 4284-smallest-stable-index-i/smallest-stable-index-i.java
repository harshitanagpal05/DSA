class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] minFromIndex = new int[n]; // min no from every position
        int minEl = Integer.MAX_VALUE; // starting w the biggest no possible
        for(int i=n-1; i>=0; i--){
            minEl = Math.min(minEl, nums[i]);
            minFromIndex[i] = minEl; // at given index, the min no from here to end is minEl
        }
        int maxEl = 0;
        for(int i=0; i<n; i++){
            maxEl = Math.max(maxEl, nums[i]);

            // instability
            if(maxEl - minFromIndex[i] <= k){
                return i; // first stable index
            }
        }
        return -1;

    }
}