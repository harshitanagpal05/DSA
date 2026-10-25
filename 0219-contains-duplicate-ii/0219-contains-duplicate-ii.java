class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){ // does current number exist?
                if(i - map.get(nums[i]) <= k){ // current distance - index where the same no was last seen 
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}