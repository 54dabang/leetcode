/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/22 09:45
 * @description: 记得加入边界判空条件
 * 主要思想:定义三个变量 pre,head,headNext
 * head其实是指向了当前的节点，headNext指向了后置节点，pre指向的是head的前置节点
 * （1）head每次向后移动之前，需要将head的next节点指向pre,同时将head后移。每次挂载两个节点的关系即可将整个链表串起来。
 * （2）边界情况，pre是最开始的头结点时，需要将pre.next指向null
 * 当headNext执指向了null时，说明已经遍历至尾部节点返回即可。
 * 第206题 反转链表
 */
public class Reverse_Linked_List {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode pre;
        ListNode headNext = head.next;
        boolean isFirst = true;
        while (headNext != null){
            pre = head;
            head = headNext;
            headNext = head.next;
            head.next = pre;
            if(isFirst){
                pre.next = null;
            }
            isFirst = false;
        }
        return head;
    }
    public void print(ListNode head){
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.val +"->");
            cur = cur.next;
        }
        System.out.println("\n--------结束-------------");
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;


        Reverse_Linked_List reverseLinkedList = new Reverse_Linked_List();

        ListNode head = reverseLinkedList.reverseList(node1);
        reverseLinkedList.print(head);



    }
}
