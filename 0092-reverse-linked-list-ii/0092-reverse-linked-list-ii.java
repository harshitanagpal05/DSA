/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right){
            return head;
        }
        ListNode t = head;
        ListNode before = null;
        int pos = 1;
        while(t!=null){ // reach the left pos
            if(pos<left){
                before = t;
                t = t.next;
                pos++;
                continue;
            }
            break;
        }
        ListNode curr = t;
        ListNode prev = null;
        int times = right-left+1;

        // reverse the LL
        while(times-- > 0){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // connect the links (main catch)
        t.next = curr;
        if(before != null){
            before.next = prev;
            return head;
        }
        return prev;
    }
}