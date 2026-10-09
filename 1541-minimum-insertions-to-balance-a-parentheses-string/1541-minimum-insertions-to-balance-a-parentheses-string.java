
class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // Consume the pair ))
                } else {
                    ans++; // Insert a missing )
                }

                if (open == 0) {
                    ans++; // Insert a missing (
                } else {
                    open--;
                }
            }
        }

        ans += open * 2; // Each unmatched ( needs two )
        return ans;
    }
}
