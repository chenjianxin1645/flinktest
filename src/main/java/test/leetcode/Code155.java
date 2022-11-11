package test.leetcode;


import java.util.*;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());

        minStack.pop(); // 删除栈顶元素
        minStack.top(); // 获取栈顶元素
        System.out.println(minStack.getMin());

        minStack.push(-1);
        minStack.push(-8);
        System.out.println(minStack.getMin());
    }

    static class MinStack {
        /**
         * Deque
         * 普通队列(一端进另一端出):
         * Queue queue = new LinkedList()或Deque deque = new LinkedList()
         * 双端队列(两端都可进出)
         * Deque deque = new LinkedList()
         * 堆栈
         * Deque deque = new LinkedList()
         */
        Deque<Integer> xStack; // 数据栈
        Deque<Integer> minStack; // 最小辅助栈 插入移除都需要和数据栈保持同步

        public MinStack() {
            // 初始化
            xStack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        // 保持同步操作
        public void push(int x) {
            xStack.push(x);

            // 需要判断当前最小值入栈
            minStack.push(Math.min(minStack.peek(), x));
        }


        // 移除栈顶元素 保持同步
        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        // 获取栈顶元素，不移除，无须同步最小栈
        public int top() {
            return xStack.peek();
        }

        // 获取最小栈的元素即可
        public int getMin() {
            return minStack.peek();
        }
    }
}


