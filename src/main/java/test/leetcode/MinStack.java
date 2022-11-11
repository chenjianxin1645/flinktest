package test.leetcode;


import java.util.LinkedList;

public class MinStack {
    LinkedList<Integer> minStack;
    LinkedList<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        minStack = new LinkedList<Integer>(); // 辅助最小栈
        stack = new LinkedList<Integer>(); // 基本栈

    }

    public void push(int val) {
        stack.add(val);
//        System.out.println(stack.peekLast());
//        System.out.println(stack);

        // 判断最小栈当前值
        Integer peekLast = minStack.peekLast();
//        System.out.println(peekLast);
        if (peekLast == null) {
            minStack.add(val);
        } else {
            if (peekLast > val) {
                // 需要加入进来
                minStack.add(val);
            } else {
                minStack.add(peekLast);
            }
        }
    }

    public void pop() {
        stack.pollLast();
        minStack.pollLast();
    }

    public int top() {
        return stack.peekLast();
    }
//
    public int getMin() {
        return minStack.peekLast();
    }


    public static void main(String[] args) {
//        输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]

//        输出：
//[null,null,null,null,-3,null,0,-2]

//        解释：
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);

        System.out.println(minStack.getMin());   // --> 返回 0
        minStack.pop(); // 删除栈顶元素
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);

        System.out.println(minStack.getMin());   // 0
        minStack.pop(); // 删除栈顶元素
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);

        System.out.println(minStack.getMin());   // --> 返回 -2.
        minStack.pop(); // 删除栈顶元素
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);

        System.out.println(minStack.getMin());   // --> 返回 -2.

    }
}
