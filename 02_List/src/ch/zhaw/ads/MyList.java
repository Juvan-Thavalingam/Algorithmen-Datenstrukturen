package ch.zhaw.ads;

import java.util.AbstractList;


public class MyList extends AbstractList {

    protected ListNode head;
    protected ListNode tail;

    public MyList() {
        head = null;
        tail = null;
    }

    static class ListNode {
        protected Object data;
        protected ListNode next;
        protected ListNode prev;

        ListNode(Object o) {
            data = o;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(Object o) {
        ListNode node = new ListNode(o);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail = head;
            while(tail.next != null){
                tail = tail.next;
            }
            tail.next = node;
            tail.next.prev = tail;
        }
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        ListNode tail = head;
        while(tail != null){
            if (!tail.data.equals(obj)){
                tail = tail.next;
            } else {
                if (size() == 1){
                    head = null;
                    return true;
                } else if(obj.equals(head.data)) {
                    head = head.next;
                    return true;
                } else {
                    tail.prev.next = tail.next;
                    if(tail.next != null) {
                        tail.next.prev = tail.prev;
                    }
                    return true;
                }
            }

        }
        return true;
    }

    @Override
    public Object get(int pos) {
        ListNode node = head;

        for (int i = 0; i < pos; i++) {
            node = node.next;
        }


        return node.data;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        ListNode node = head;
        int pos = 1;

        if(node == null){
            return 0;
        }

        while (node.next != null) {
            node = node.next;
            pos++;
        }
        return pos;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }
}