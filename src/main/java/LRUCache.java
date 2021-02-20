import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/20 09:21
 * 第146题：https://leetcode-cn.com/problems/lru-cache/
 * @description:实现最近、最少使用的缓存机制
 * 一旦看到键值对，就应该先想到hashMap，使用双向链表的原因是能够快速找到尾部和头部元素，相比起顺序链表来说提升了查询效率
 * 每当查询、写入新的元素的时候，就将元素添加到头部。在添加之前需要先判断是否要超出容量，如果添加后已经超出容量，则需要移除尾部元素。
 */
public class LRUCache {
    private int capacity;

    private Map<Integer, LinkNode> cacheMap;

    private LinkNode head;//指向头部 其后置节点为最新写入的数据

    private LinkNode tail;//指向尾部 其前置节点为需要淘汰的内容

    class LinkNode {
        private int key;

        private int value;

        private LinkNode prev;

        private LinkNode next;

        public LinkNode() {
        }

        public LinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }


    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<Integer, LinkNode>();
        head = new LinkNode();
        tail = new LinkNode();
        head.next =tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key)){
            return -1;
        }
        LinkNode linkNode = cacheMap.get(key);
        removeNode(linkNode);
        moveToHead(linkNode);
        return linkNode.value;
    }

    public void put(int key, int value) {

        if (cacheMap.containsKey(key)) {//已经包含时，需要更新节点数据，并将其移动到头部后
            LinkNode dataNode = cacheMap.get(key);
            dataNode.value = value;
            moveToHead(dataNode);
        } else {
            if (cacheMap.keySet().size() >= capacity) {//即将超出容量，继续添加将触发移除操作
                LinkNode tempNode = tail.prev;
                cacheMap.remove(tempNode.key);//在缓存中清除数据
                removeNode(tempNode);//移除尾部节点
            }
            LinkNode newNode = new LinkNode(key,value);
            moveToHead(newNode);
            cacheMap.put(key,newNode);//记得及时在缓存种加入
        }

    }

    private void removeNode(LinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(LinkNode node) {
        if(node.prev != null && node.next != null){//说明是已经在链表中既有的节点
            removeNode(node);
        }
        LinkNode tempNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = tempNode;
        tempNode.prev = node;
    }
    public void print(){
        StringBuilder sb = new StringBuilder();
        for(LinkNode linkNode = head.next; linkNode != tail;linkNode = linkNode.next){
            sb.append(linkNode.value+"，");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2,1);
       /* lruCache.get(2);
        lruCache.print();*/
        System.out.println(lruCache.get(2));
    }

}
