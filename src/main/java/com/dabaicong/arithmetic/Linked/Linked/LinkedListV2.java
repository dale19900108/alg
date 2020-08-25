package com.dabaicong.arithmetic.Linked.Linked;

import lombok.Data;
import org.apache.lucene.util.RamUsageEstimator;

import java.lang.ref.SoftReference;
import java.util.Stack;

/**
 * 一个普通的链表的优化，
 * 这次是针对node的优化，不采用node 而是采用他的软引用
 * 软引用，不一定是空间优化，因为软引用也是占空间的。
 * 如果软引用的大小，超过了T类型长度。那么，是值得的。基本类型，反倒会影响
 *
 * RamUsageEstimator在4.0.0版本时候，可以计算链表，但是有安全问题。
 * 升级到最新版本，却不能计算链表了
 */
public class LinkedListV2<T> {

    // 链表的头指针
    public Node<T> head;

    public LinkedListV2() {
        this.head = null;
    }

    public static void main(String[] args) {
        LinkedListV2<Integer> list = new LinkedListV2<>();
        list.add(1);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.add(2);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.add(3);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.add(4);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.add(5);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.print();
        list.remove(4);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.print();
        list.reverser3();
//        list.print();
//        list.reverser2();
//        list.print();
//        list.head = list.reverser1(list.head);
        System.out.println("LinkedList object size :"+ RamUsageEstimator.shallowSizeOf(list));
        list.print();

        /*
LinkedList object size :56
LinkedList object size :184
LinkedList object size :264
LinkedList object size :344
LinkedList object size :424
Head-->1-->2-->3-->4-->5-->End
LinkedList object size :344
Head-->1-->2-->3-->5-->End
LinkedList object size :344
Head-->5-->3-->2-->1-->End
         */

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
//    public Node<T> reverser1(Node<T> head) {
//        // 递归的边界条件。=null是链表可能是空的。 .next = null才是正常的条件。
//        if (head == null || head.next==null){
//            return head ;
//        }
//        // 触发递归 。
//        Node<T> newList = reverser1(head.next);
//        // 递归的解决
//        Node<T> temp = head.next;
//        temp.next = head ;
//        head.next = null ;
//        return newList ;
//    }

    /**
     * 链表反转2
     * 通过双指针反转
     */
//    public void reverser2() {
//        Node<T> pre = null ;
//        Node<T> current = head;
//        do {
//            // 先保存下current的next地址
//            Node<T> temp = current.next;
//            // 当前的next调转指向之前
//            current.next = pre;
//            // pre前进一步
//            pre = current;
//            // current复制 。
//            current = temp;
//        }while (current.next!= null );
//        current.next = pre;
//       head = current;
//    }

    /**
     * 链表反转3
     * 通过栈反转。
     */
    public void reverser3() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = head ;
        while (head!= null && current.getNext()!=null){
            stack.push(current);
            current = current.next.get();
        }
        head = current ;
        do{
            Node<T> node = stack.pop();
            current.next = new SoftReference<>(node) ;
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
            pre = pre.getNext().get();
        }
        pre.next = new SoftReference<>(node) ;
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
            current = current.getNext().get();
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
            current = current.getNext().get();

        }
        System.out.print(current.getData()+"-->End\n");
    }
}


@Data
class Node<T> {
    T data;
    SoftReference<Node<T>> next;

    public Node(T entry) {
        this.data = entry;
        this.next = null;
    }
}

