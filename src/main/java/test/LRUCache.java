package test;


import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LRUCache {

    // 存储节点位置
    private HashMap<Integer, Node> map; // 存储node内容节点

    private Node first; // 双向链表第一个节点 虚拟
    private Node last; // 双向链表最后有个节点 虚拟

    private int capacity; // 缓存大小

    private Lock lock = new ReentrantLock(true); // 可重用公平锁


    // 初始化
    public LRUCache(int capacity) {
        this.capacity = capacity;

        map = new HashMap<>(capacity);

        first = new Node();
        last = new Node();

        first.next = last;
        last.prev = first;
    }


    // 获取节点内容，存在则置顶
    public String get(int key) {
        Node node = map.get(key);
        if (null != node) {
            // 移除当前节点位置
            remoteNode(node);

            // 添加当前节点到第一个节点处
            addAfterFirst(node);
        }

        return (null != node) ? node.value : "not found any contnet";
    }


    // 添加节点信息
    public void put(int key, String value) {
        // 判断是否存在
        Node node = map.get(key);
        if (null != node) {
            // 覆盖新值
            node.value = value;
            // 移除链表中节点关系
            remoteNode(node);
            // 添加到头部节点
            addAfterFirst(node);
        } else {
            // 新增节点，确认容量是否足够
            if (map.size() == capacity) {
                // 先删除最后一个节点
                map.remove(last.prev.key);
                remoteNode(last.prev);
            }

            Node node1 = new Node(key, value);
            map.put(key, node1);
            addAfterFirst(node1);
        }

    }


    // 移除当前节点位置
    private void remoteNode(Node node) {
        try {
            lock.lock();
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } finally {
            lock.unlock();
        }
    }


    private void addAfterFirst(Node node) {
        try {
            lock.lock();

            // 和first.next节点关系
            node.next = first.next;
            first.next.prev = node;

            // 和first节点关系
            first.next = node;
            node.prev = first;
        } finally {
            lock.unlock();
        }

    }


    // 节点具体信息
    class Node {
        private int key;
        private String value;

        private Node prev; // 当前节点前一个节点
        private Node next; // 当前节点下一个节点


        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }


        public Node() {

        }
    }


    // test
    public static void main(String[] args) {
        int key = 1;
        int key2 = 2;
        String value = "v1";
        String value2 = "v2";


        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(key));
        lruCache.put(key2, value2);
        System.out.println(lruCache.get(key2));
        lruCache.put(key, value);
        System.out.println(lruCache.get(key2));
        System.out.println(lruCache.get(key));

    }

}


