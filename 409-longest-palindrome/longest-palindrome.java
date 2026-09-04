class Solution {
    public int longestPalindrome(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int ans = 0; // length of palindrome
        boolean odd = false;
        for (int freq : map.values()) {
            ans += (freq / 2) * 2; // forming pairs of 2
            if (freq % 2 == 1) {
                odd = true;
            }
        }
        if (odd) {
            ans++;
        }
        return ans;
    }
}