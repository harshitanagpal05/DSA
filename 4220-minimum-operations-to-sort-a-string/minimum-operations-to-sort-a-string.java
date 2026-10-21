
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check if the string is already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // A reversed string of length 2 cannot be sorted
        if (n == 2) {
            return -1;
        }

        char min = 'z';
        char max = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);
        }

        // One operation is enough if min is at the start
        // or max is at the end
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // If min or max is inside the string, answer is 2
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);

            if (c == min || c == max) {
                return 2;
            }
        }

        // Otherwise, answer is 3
        return 3;
    }
}
