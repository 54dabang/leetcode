/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/03/08 09:23
 * @description:
 */
public class Merge_Two_Sorted_List {
    public class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode head,point;
        if(l1.val > l2.val){
            head = point = l2;
            l2 = l2.next;
        }else {
            head = point = l1;
            l1 = l1.next;
        }
        while (l1 != null && l2 != null){
            if(l1.val > l2.val){
                point.next = l2;
                point = point.next;
                l2 = l2.next;
            }else {
                point.next = l1;
                point = point.next;
                l1 = l1.next;
            }
        }
        if(l1 != null){
            point.next = l1;
        }
        if(l2 != null){
            point.next = l2;
        }
        return head;
  }
    public ListNode toListNode(int ...params){
        ListNode head =null,point = null;
        for(int i =0;i<params.length;i++){
            if(i ==  0){
                head = point = new ListNode(params[i]);
            }else {
                point.next = new ListNode(params[i]);
                point = point.next;
            }
        }
        return head;
    }
    public String toString(ListNode listNode){
        StringBuilder sb = new StringBuilder();
        while (listNode != null){
            sb.append(listNode.val+"->");
            listNode = listNode.next;
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        Merge_Two_Sorted_List mergeTwoSortedList = new Merge_Two_Sorted_List();
        ListNode l1 = mergeTwoSortedList.toListNode(2,4,5,9);
        ListNode l2 = mergeTwoSortedList.toListNode(0,1,7);
        System.out.println(mergeTwoSortedList.toString(l1));
        System.out.println(mergeTwoSortedList.toString(l2));
        ListNode mergedList = mergeTwoSortedList.mergeTwoLists(l1,l2);
        System.out.println(mergeTwoSortedList.toString(mergedList));
    }
}
