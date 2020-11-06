package com.dabaicong.arithmetic.skipList;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表的实现。
 * <p>
 * 生成一个上层和下层比例是0.5的64层链表。
 * 理论上可以为2^64个数提供二分查找。
 *
 *  插入的时候，需要注意一个问题，那就是重复。如果发现重复的元素了怎么办 ？
 */
public class SkipList {

    // 跳表的头部
    private static final Integer MAX_LEVEL = 8;
    // 链表的填充率,即，上层和下层的期望比例。
    private static final double FILL_RATE = 0.5;
    // 跳表的各层头部节点。
    private SkipNode[] level = new SkipNode[MAX_LEVEL];
    // 跳表的头节点
    public SkipNode head;
    // 跳表中的最大数和最小数
    private Integer maxDate = null;
    private Integer minDate = null;

    /**
     * 初始化跳表，把最左面的数组初始化了。
     * 让上层指向下一层
     */
    public SkipList() {
        // 初始化跳表
        SkipNode preSkipNode = null;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            SkipNode skipNode = new SkipNode(null, null, Integer.MIN_VALUE);
            if (preSkipNode != null) {
                skipNode.setDown(preSkipNode);
            }
            level[i] = skipNode;
            preSkipNode = skipNode;
        }
        head = level[0];
    }

    public boolean search(Integer value) {
        if (value == null){
            return false ;
        }
        if(value<minDate || value>maxDate){
            return  false ;
        }
        SkipNode temp = head;
        // 一直探索到最底层
        while (temp.getDown() != null ) {
            // 如果是重复的元素，直接结束插入
            if (temp.getRight()!= null && temp.getRight().getData()!=null && temp.getRight().getData().intValue()==value.intValue()){
                return true;
            }
            // 先向右试探是否可以通过。右侧是空,或者是右侧节点大于值，直接向下
            if (temp.getRight() == null || (temp.getRight() != null && temp.getRight().getData() > value)) {
                // temp向下移动
                temp = temp.getDown();
            }
            // 右侧不为空，并且小于要插入的值，则向右移动
            while (temp.getRight() != null && temp.getRight().getData() < value) {
                temp = temp.getRight();
            }
        }
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
     * @param value 插入的数据
     */
    public void add(Integer value) {

        // 生成本次插入的随机层
        int randomLevel = randomLevel();
        System.out.println("value ："+value+"层数是"+randomLevel);
        List<SkipNode> visit = new ArrayList<>();
        // 向下遍历到对应待插入层，把遍历的节点都保存到数组中
        SkipNode temp = head;
        int downLevel = 0 ;
        boolean flag = false ;
        // 一直探索到最底层
        while (temp.getDown() != null ) {

            // 如果是重复的元素，直接结束插入
            if (temp.getRight()!= null && temp.getRight().getData()!=null && temp.getRight().getData().intValue()==value){
                System.out.println("downLevel:"+downLevel);
                randomLevel =downLevel;
                flag = true;
                // 保存遍历的节点
                visit.add(temp);
                // temp向下移动
                temp = temp.getDown();
            }
            // 先向右试探是否可以通过。右侧是空,或者是右侧节点大于值，直接向下
            if (temp.getRight() == null || (temp.getRight() != null && temp.getRight().getData() > value)) {
                // 保存遍历的节点
                visit.add(temp);
                // temp向下移动
                temp = temp.getDown();
                if (!flag) {
                    downLevel++;
                }
            }
            // 右侧不为空，并且小于要插入的值，则向右移动
            while (temp.getRight() != null && temp.getRight().getData() < value) {
                temp = temp.getRight();
            }

        }
        // 最后把temp加入到遍历过的节点
        visit.add(temp);



        // 从randomLevel层开始遍历list。把list的右指针指向node，把node的右指针指向
        SkipNode pre = null ;
        for (int i = MAX_LEVEL-1; i >=randomLevel; i--) {
            SkipNode node = visit.get(i);
            SkipNode right = visit.get(i).getRight();
            SkipNode entry = new SkipNode(right, pre, value);
            node.setRight(entry);
            pre = entry;
        }

        // 更新最大最小值
        if (maxDate==null || maxDate<value){
            maxDate = value ;
        }
        if (minDate==null || minDate>value){
            minDate = value ;
        }

    }

    /**
     * 删除某个元素
     *
     * @param value 对象
     * @return
     */
    public boolean erase(Integer value) {
        if(value<minDate || value>maxDate){
            return  false ;
        }
        // 记录下路过的点。key是路过的，value是确定的值
        List<SkipNode> visit = new ArrayList<>();
        SkipNode temp = head;
        // 一直探索到最底层
        while (temp!=null &&( temp.getDown() != null || temp.getData()<value)) {


            // 右侧不为空，并且小于要插入的值，则向右移动
            while (temp.getRight() != null && temp.getRight().getData() < value) {
                temp = temp.getRight();
            }
            // 先向右试探是否可以通过。右侧是空,或者是右侧节点大于值，直接向下
            if (temp.getRight() == null || (temp.getRight() != null && temp.getRight().getData() > value)) {
                // temp向下移动
                temp = temp.getDown();
            }
            // 如果是重复的元素，则说明是找到了对应元素，把这个点的right执行right的right
            if (temp.getRight()!= null && temp.getRight().getData()!=null && temp.getRight().getData().intValue()==value.intValue()){
                System.out.println(temp.getData());
                temp.setRight(temp.getRight().getRight());
                temp  = temp.getDown();
            }
        }
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

    public void print() {
        for (int i = 0; i < level.length; i++) {
            SkipNode node = level[i];
            System.out.print("=level:" + i + "=");
            while (node.getRight() != null) {
                System.out.print(node.getData() + "--->");
                node = node.getRight();
            }
            System.out.print(node.getData()+"--->Null\n");
        }
    }
}
@Data
class SkipNode {

    private SkipNode right;
    private SkipNode down;
    private Integer data;

    public SkipNode(SkipNode right, SkipNode down, Integer data) {
        this.right = right;
        this.down = down;
        this.data = data;
    }
}
