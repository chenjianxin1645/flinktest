package test.leetcode.easy;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 *
 * 225. 用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *  
 *
 * 注意：
 *
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *  
 *
 * 示例：
 *
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *  
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 *  
 *
 * 进阶：你能否仅用一个队列来实现栈。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code225 {
    public static void main(String[] args) {

//        ["MyStack", "push", "push", "top", "pop", "empty"]
//        [[], [1], [2], [], [], []]
//        [null, null, null, 2, 2, false]


        Code225 code225 = new Code225();
        code225.init();

    }

    public void init () {
         MyStack myStack = new MyStack();
         myStack.push(1);
         myStack.push(2);
        int top = myStack.top();// 返回 2
        System.out.println(top);
        int pop = myStack.pop();// 返回 2
        System.out.println(pop);

        boolean empty = myStack.empty();// 返回 False
        System.out.println(empty);
    }

    class MyStack {
        LinkedList<Integer> link;

        public MyStack() {
            link = new LinkedList<Integer>();
        }

        public void push(int x) {
            link.push(x);
        }

        public int pop() {
            return link.pop();
        }

        public int top() {
            return link.peek();
        }

        public boolean empty() {
            return link.size() == 0;
        }
    }
}
