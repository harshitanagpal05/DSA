import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (isValid(curr)) {
                ans.add(curr);
                found = true;
            }

            // Once valid strings are found at this level,
            // don't generate strings with more removals.
            if (found) {
                continue;
            }

            for (int i = 0; i < curr.length(); i++) {
                char ch = curr.charAt(i);

                // Only remove parentheses
                if (ch != '(' && ch != ')') {
                    continue;
                }

                String next = curr.substring(0, i)
                            + curr.substring(i + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return ans;
    }

    private boolean isValid(String s) {
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;

                // Closing bracket without matching opening bracket
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}