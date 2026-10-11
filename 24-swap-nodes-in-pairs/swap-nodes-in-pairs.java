
class Solution {

    public ListNode reverse(ListNode head, int times) {
        ListNode curr = head;
        ListNode prev = null;

        while (times-- > 0) {
            ListNode nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }

        return prev;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode left = head;
        ListNode right;
        ListNode res = null;
        ListNode prevleft = null;
        int size = 2;

        while (true) {
            right = left;

            for (int i = 0; i < size - 1; i++) {
                if (right == null) {
                    break;
                }
                right = right.next;
            }

            if (right != null) {
                ListNode nextleft = right.next;

                reverse(left, size);

                if (prevleft != null) {
                    prevleft.next = right;
                }

                prevleft = left;

                if (res == null) {
                    res = right;
                }

                left = nextleft;
            } else {
                if (prevleft != null) {
                    prevleft.next = left;
                }

                if (res == null) {
                    res = left;
                }

                break;
            }
        }

        return res;
    }
}
