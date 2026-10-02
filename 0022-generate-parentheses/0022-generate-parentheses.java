class Solution {
    public List<String> generateParenthesis(int n) {
        // create an array list
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String current,
                            int open, int close, int n) {

        // If the string has used all parentheses
        if (current.length() == 2 * n) {
            ans.add(current);
            return;
        }

        // Add '(' if we still have opening brackets available
        if (open < n) {
            backtrack(ans, current + "(", open + 1, close, n);
        }

        // Add ')' only if it won't make the sequence invalid
        if (close < open) {
            backtrack(ans, current + ")", open, close + 1, n);
        }
    }
}