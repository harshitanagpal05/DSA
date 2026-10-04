class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        // stack to character array
        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;
                high++;
            }

            // Minimum open brackets cannot be negative
            low = Math.max(low, 0);

            // Even maximum possible opens became negative
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}