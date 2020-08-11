package com.dabaicong.arithmetic.Linked;

import lombok.Data;
import java.util.Stack;

/**
 * 一个普通的链表。
 */
public class LinkedList<T> {

    // 链表的头指针
    public Node<T> head;

    public LinkedList() {
        this.head = null;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();
        list.remove(4);
        list.print();
//        list.reverser3();
//        list.print();
//        list.reverser2();
//        list.print();
        list.head = list.reverser1(list.head);
        list.print();

    }



    /**
     * 链表反转1
     * 通过递归反转
     * 通过这个理解了下递归，
     * 递归的两个关键点
     * 1.找到边界条件 。
     * 2.找到循环的最小条件 。
     *
     * 这里还有一点，可以这么理解递归。
     * 递归是边界条件的元素，和边界之前元素的调整 。
     *
     */
    public Node<T> reverser1(Node<T> head) {
        // 递归的边界条件。=null是链表可能是空的。 .next = null才是正常的条件。
        if (head == null || head.next==null){
            return head ;
        }
        // 触发递归 。
        Node<T> newList = reverser1(head.next);
        // 递归的解决
        Node<T> temp = head.next;
        temp.next = head ;
        head.next = null ;
        return newList ;
    }

    /**
     * 链表反转2
     * 通过双指针反转
     */
    public void reverser2() {
        Node<T> pre = null ;
        Node<T> current = head;
        do {
            // 先保存下current的next地址
            Node<T> temp = current.next;
            // 当前的next调转指向之前
            current.next = pre;
            // pre前进一步
            pre = current;
            // current复制 。
            current = temp;
        }while (current.next!= null );
        current.next = pre;
       head = current;
    }

    /**
     * 链表反转3
     * 通过栈反转。
     */
    public void reverser3() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = head ;
        while (current.getNext()!=null){
            stack.push(current);
            current = current.next;
        }
        head = current ;
        do{
            Node<T> node = stack.pop();
            current.next = node ;
            current=node ;
        }while (stack.size()!=0 );
        current.next = null ;

    }

    /**
     * 向链表中添加元素
     */
    public void add(T entry) {
        Node<T> node = new Node<>(entry);
        Node<T> pre = head;
        if (pre == null){
            head = node ;
            return;
        }
        while ( pre.getNext() != null) {
            pre = pre.getNext();
        }
        pre.next = node ;
    }

    /**
     * 移除元素
     */
    public void remove(T entry) {
        // before 指针用来保存pre之前的一个节点 。
        Node<T> pre = head;
        Node<T> current = head;
        while (head!=null &&current.getNext() != null) {
            // 找到了元素
            if (current.getData().equals(entry)) {
                pre.next = current.next;
                break;
            }
            pre = current;
            current = current.getNext();
        }
    }

    /**
     * 打印出所有元素，调试用
     */
    public void print() {
        System.out.print("Head-->");
        Node<T> current = head ;
        while (current.next != null) {
            System.out.print((current.getData()) + "-->");
            current = current.getNext();

        }
        System.out.print(current.getData()+"-->End\n");
    }
}

@Data
class Node<T> {
    T data;
    Node<T> next;

    public Node(T entry) {
        this.data = entry;
        this.next = null;
    }
}

