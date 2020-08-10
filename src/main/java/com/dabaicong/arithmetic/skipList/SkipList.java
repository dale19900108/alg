package com.dabaicong.arithmetic.skipList;


/**
 * 跳表的实现。
 * <p>
 * 生成一个上层和下层比例是0.5的64层链表。
 * 理论上可以为2^64个数提供二分查找。
 */
public class SkipList {

    // 跳表的头部
    private final static Integer MAX_LEVEL = 64;
    // 链表的填充率,即，上层和下层的期望比例。
    private final static double FILL_RATE = 0.5;
    // 跳表的头节点
    private static Node head;
    // 跳表中的最大数和最小数
    private Integer maxDate = null;
    private Integer minDate = null;
    // 跳表的各层头部节点。
    private Node[] level = new Node[MAX_LEVEL];

    /**
     * 初始化跳表，把最左面的数组初始化了。
     * 让上层指向下一层
     */
    public SkipList() {
        //
        Node preNode = null;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            Node node = new Node(null, null, null);
            if (preNode != null) {
                node.setDown(preNode);
            }
            preNode = node;
        }
        // 跳表的头结点
        head = level[0];
    }

    public boolean search(int target) {
        return false;
    }

    /**
     * 将一个数添加到跳表中。
     * <p>
     * 添加过程，生成一个随机的层数，把层数之下的都生成节点。
     * 添加过程中，把搜索到的节点保存下来，用来更新
     * <p>
     * 最坏的情况，每次都输入数比之前小，则，直接更新链表头节点，
     *
     * @param num
     */
    public void add(int num) {

    }

    public boolean erase(int num) {
        return false;
    }

    /**
     * 随机选择一层，从这层以下，都需要插入节点。
     * 根据数组的
     *
     * @return 随机选层。
     */
    private int randomLevel() {

        int level = 0;

        while (Math.random() < FILL_RATE && level < MAX_LEVEL)
            level++;

        return level;
    }
}

class Node {

    private Node right;
    private Node down;
    private Integer data;

    public Node(Node right, Node down, Integer data) {
        this.right = right;
        this.down = down;
        this.data = data;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
