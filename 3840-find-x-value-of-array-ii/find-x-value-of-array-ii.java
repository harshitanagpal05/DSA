class Solution {

    class Node {
        int prod;
        int[] cnt;

        Node() {
            prod = 1;
            cnt = new int[5];
        }
    }

    int n, k;
    Node[] tree;

    Node merge(Node left, Node right) {
        Node res = new Node();

        // Product of the complete segment
        res.prod = (left.prod * right.prod) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Prefixes that extend into right
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            res.cnt[newRem] += right.cnt[r];
        }

        return res;
    }

    void build(int[] nums, int node, int l, int r) {

        if (l == r) {
            int rem = nums[l] % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(nums, 2 * node + 1, l, mid);
        build(nums, 2 * node + 2, mid + 1, r);

        tree[node] = merge(
            tree[2 * node + 1],
            tree[2 * node + 2]
        );
    }

    void update(int node, int l, int r, int idx, int value) {

        if (l == r) {
            tree[node] = new Node();

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(
                2 * node + 1,
                l,
                mid,
                idx,
                value
            );
        } else {
            update(
                2 * node + 2,
                mid + 1,
                r,
                idx,
                value
            );
        }

        tree[node] = merge(
            tree[2 * node + 1],
            tree[2 * node + 2]
        );
    }

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(
                2 * node + 1,
                l,
                mid,
                ql,
                qr
            );
        }

        if (ql > mid) {
            return query(
                2 * node + 2,
                mid + 1,
                r,
                ql,
                qr
            );
        }

        Node left = query(
            2 * node + 1,
            l,
            mid,
            ql,
            qr
        );

        Node right = query(
            2 * node + 2,
            mid + 1,
            r,
            ql,
            qr
        );

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k,
                             int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }

        build(nums, 0, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update
            update(
                0,
                0,
                n - 1,
                index,
                value
            );

            // Query [start, n - 1]
            Node res = query(
                0,
                0,
                n - 1,
                start,
                n - 1
            );

            ans[i] = res.cnt[x];
        }

        return ans;
    }
}