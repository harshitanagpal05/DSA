class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                depth++;
                if (depth > 1) {
                    ans.append(c);
                }

            } else {
                depth--;
                if (depth > 0) {
                    ans.append(c);
                }
            }
        }

        return ans.toString();
    }
}