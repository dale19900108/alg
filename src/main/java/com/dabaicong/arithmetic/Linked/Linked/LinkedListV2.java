package com.dabaicong.arithmetic.Linked.Linked;

import lombok.Data;
import org.apache.lucene.util.RamUsageEstimator;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * 一个普通的链表的优化尝试，
 * 这次是针对node的优化，不采用node 而是采用他的软引用
 * 软引用，不一定是空间优化，因为软引用也是占空间的。
 * 如果软引用的大小，影响超过了T类型长度。那么，是值得的。基本类型，反倒会
 *
 * 放弃吧。 SoftReference  也会导致内存回收 。
 * 回收条件比较严格，需要在内存占满的时候，才回收 。
 * 触发回收可以指定堆内存大小，然后通过分配大连续数组触发
 * -Xms64m -Xmx64m
 *
 * WeakReference是弱引用，触发gc后就会回收空间。
 */
@Deprecated
public class LinkedListV2<T> {

    // 链表的头指针
    public Node<T> head;

    public LinkedListV2() {
        this.head = null;
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedListV2<Integer> list = new LinkedListV2<>();
        for (int i = 0; i < 20000; i++) {
            list.add(i+1);
        }

        Thread.sleep(1000L);

        System.gc();
        System.gc();
        System.gc();
        Thread.sleep(1000L);
        //分配大数据
        byte[] bytes = new byte[1024 * 1024 * 64];
        byte[] byte1 = new byte[1024 * 1024 * 64];
        byte[] byte2 = new byte[1024 * 1024 * 64];
        byte[] byte3 = new byte[1024 * 1024 * 64];
        byte[] byte4 = new byte[1024 * 1024 * 64];
        for (int i=0;i<byte1.length;i++) {
            bytes[i] = 1;
            byte1[i] = 2;
            byte2[i] = 3;
            byte3[i] = 4;
            byte4[i] = 5;
        }
        System.gc();
        System.gc();
        System.gc();
        Thread.sleep(1000L);


        System.gc();
        System.gc();
        System.gc();

        list.reverser3();
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
        while (head!= null && current.getNext()!=null){
            stack.push(current);
            current = current.next;
        }
        head = current ;
        do{
            Node<T> node = stack.pop();
//            current.next = new SoftReference<>(node) ;
//            current.next = new WeakReference<>(node) ;
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
            pre = pre.next;
        }
        pre.next = node;
//        pre.next = new SoftReference<>(node) ;
//        pre.next = new WeakReference<>(node) ;
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
            current = current.next;
        }
    }

    /**
     * 打印出所有元素，调试用
     */
    public void print() {
        System.out.print("Head-->");
        Node<T> current = head ;
        while (head!= null && current.next != null) {
            System.out.print((current.getData()) + "-->");
            current = current.next;

        }
        System.out.print(current.getData()+"-->End\n");
    }
}


@Data
class Node<T> {
    T data;
//    WeakReference<Node<T>> next;
//    SoftReference<Node<T>> next;
    Node<T> next;

    public Node(T entry) {
        this.data = entry;
        this.next = null;
    }
    @Override
    public void finalize(){
        //gc回收
        System.out.println(data+"被回收");
    }

}

