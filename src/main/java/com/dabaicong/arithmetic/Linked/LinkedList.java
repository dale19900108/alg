package com.dabaicong.arithmetic.Linked;

import lombok.Data;

/**
 * 一个普通的链表。
 * 此版本存在一个问题，那就是多线程不安全 。
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
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.print();

    }


    /**
     * 向链表中添加元素
     */
    public void add(T entry) {
        Node<T> node = new Node<>(entry);
        Node<T> pre = head;
        if (pre == null) {
            head = node;
            return;
        }
        while (pre.getNext() != null) {
            pre = pre.getNext();
        }
        pre.next = node;
    }

    /**
     * 移除元素
     */
    public void remove(T entry) {
        // before 指针用来保存pre之前的一个节点 。
        Node<T> pre = head;
        Node<T> current = head;
        while (head != null && current.getNext() != null) {
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
        Node<T> current = head;
        while (current.next != null) {
            System.out.print((current.getData()) + "-->");
            current = current.getNext();

        }
        System.out.print(current.getData() + "-->End\n");
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

