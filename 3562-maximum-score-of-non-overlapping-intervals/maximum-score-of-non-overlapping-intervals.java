import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    Result[][] dp;
    int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new Interval[n];

        // Store original index
        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);
            arr[i] = new Interval(x.get(0), x.get(1), x.get(2), i);
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        dp = new Result[n + 1][5];

        Result ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    private Result solve(int pos, int remaining) {

        // We can choose nothing
        if (pos == n || remaining == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (dp[pos][remaining] != null) {
            return dp[pos][remaining];
        }

        // Option 1: Skip current interval
        Result skip = solve(pos + 1, remaining);

        // Option 2: Take current interval
        int next = findNext(pos);

        Result nextResult = solve(next, remaining - 1);

        List<Integer> takeIndices = new ArrayList<>();
        takeIndices.add(arr[pos].idx);
        takeIndices.addAll(nextResult.indices);

        Collections.sort(takeIndices);

        Result take = new Result(
            arr[pos].w + nextResult.score,
            takeIndices
        );

        // Pick the better result
        Result best;

        if (take.score > skip.score) {
            best = take;
        } 
        else if (take.score < skip.score) {
            best = skip;
        } 
        else {
            // Same score -> lexicographically smaller indices
            if (compareLexicographically(take.indices, skip.indices) < 0) {
                best = take;
            } else {
                best = skip;
            }
        }

        dp[pos][remaining] = best;
        return best;
    }

    // Find first interval whose start > current interval's end
    private int findNext(int pos) {

        int low = pos + 1;
        int high = n;

        int end = arr[pos].r;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid].l > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Compare two lists lexicographically
    private int compareLexicographically(List<Integer> a, List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}