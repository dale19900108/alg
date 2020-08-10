package com.dabaicong.arithmetic.Linked;

import lombok.Data;

/**
 * 一个普通的链表。
 */
public class LinkedList<T> {

    // 链表的头指针
    public Node<T> head;

    public LinkedList() {
        Node<T> head = new Node<T>();
        head.next = null;
        this.head = head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.print();

    }

    /**
     * 链表反转1
     * 通过递归反转
     */
    public void reverser1() {

    }

    /**
     * 链表反转2
     * 通过双指针反转
     */
    public void reverser2() {

    }

    /**
     * 链表反转3
     * 通过栈反转。
     */
    public void reverser3() {

    }

    /**
     * 向链表中添加元素
     */
    public void add(T entry) {
        Node<T> node = new Node<>(entry);
        Node<T> pre = head;
        while (pre.getNext() != null) {
            pre = pre.getNext();
        }
        pre.next = node;
        node.next = null;
    }

    /**
     * 移除元素
     */
    public void remove(T entry) {
        // before 指针用来保存pre之前的一个节点 。
        Node<T> pre = head;
        Node<T> current = head;
        while (current.getNext() != null) {

            // 找到了元素
            if (current.getData().equals(entry)) {
                pre.next = current.next;
                break;
            }
            pre = head;
            current = current.getNext();
        }
    }

    /**
     * 打印出所有元素，调试用
     */
    public void print() {
        System.out.print("Head-->");
        while (head.next != null) {
            System.out.println((head.getData()) + "-->");
        }
        System.out.print("End");
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

