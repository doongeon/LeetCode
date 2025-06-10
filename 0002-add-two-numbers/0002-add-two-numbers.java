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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ln = new ListNode();
        ListNode temp = ln;
        temp.val = (l1.val + l2.val) % 10;
        int over = (l1.val + l2.val) / 10;

        while(l1.next != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;

            temp.next = new ListNode();
            temp = temp.next;
            temp.val = (l1.val + l2.val + over) % 10;
            over = (l1.val + l2.val + over) / 10;
        }

        while(l1.next != null) {
            l1 = l1.next;
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = (l1.val + over) % 10;
            over = (l1.val + over) / 10;
        }

        while(l2.next != null) {
            l2 = l2.next;
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = (l2.val + over) % 10;
            over = (l2.val + over) / 10;
        }

        if(over > 0) {
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = over;
        }


        return ln;
    }
}