class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        int[] minFromIndex = new int[n]; // storing min element for each index
        int minEl = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; i--){
            minEl = Math.min(minEl, nums[i]);
            minFromIndex[i] = minEl;
        }
        int maxEl = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            maxEl = Math.max(maxEl, nums[i]);
            int instability = maxEl - minFromIndex[i];
            if(instability <= k){
                return i;
            }
        }
        return -1;
    }
}