package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2<K, V>
{
    private final Map<K,Node<K, V>> cache;
    Node head = null;
    Node end = null;

    private int cacheSize;

    public LRUCache2(int cacheSize)
    {
        cache = new HashMap<>(cacheSize);
    }

    public V get(K key) {
        if(cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return null;
    }

    private void setHead(Node<K, V> node)
    {
        node.next = head;
        node.previous = null;

        if(head != null) {
            head.previous = node;
        }

        head = node;

        if(end == null) {
            end = head;
        }

    }

    public void put(K key, V value) {
        if(cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            remove(node);
            setHead(node);
        }
        else {
            Node<K, V> node = new Node<>(key, value);

            if(cache.size() >= cacheSize) {
                cache.remove(end.key);
                remove(end);
            }

            cache.put(key, node);
            setHead(node);
        }
    }

    private void remove(Node<K, V> node)
    {
        if(node.previous != null) {
            node.previous.next = node.next;
        }
        else {
            //Node is the Head of the cache
            head = node.next;
        }

        if(node.next != null) {
            node.next.previous = node.previous;
        }
        else {
            //Node is in the end
            end = node.previous;
        }

        node.next.previous = node.previous;
    }


    private class Node<K, V> {
        private K key;
        private V value;
        private Node next;
        private Node previous;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
