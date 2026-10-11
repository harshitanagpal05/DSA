class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i=0; i<n; i++){
            if(n%(i+1) == 0){ // special iff pos is divided by length of array
                sum+= nums[i] * nums[i];
            }
        }
        return sum;
    }
}