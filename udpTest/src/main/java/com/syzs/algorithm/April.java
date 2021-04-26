package com.syzs.algorithm;

import java.util.HashSet;
import java.util.Set;

public class April {

    public static void main(String[] args) {
        April april = new April();
//        int[][] arr = {{1,4,7,11,15},{2,5,8,12,19}};
        int[] arr = new int[]{2,3,1};
        int n = 0000;
        System.out.println(april.hammingWeight(n));
    }


    /**
     * 找出数组中重复的数字。
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 最开始想使用将数组的数放入一个数组进行比较，但时间复杂度较高，放入Set中，因为set中的值是不允许重复的，set中无就添加，有就返回给重复数字。
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int i = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                i = num;
                break;
            }
        }
        return i;
    }

    /**
     *在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 现有矩阵 matrix 如下：
     [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
     ]
     给定 target = 5，返回 true。
     给定 target = 20，返回 false。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if(n == 0){
            return false;
        }
        int m = matrix[0].length;
        if(m == 0){
            return false;
        }
        for (int i = 0 ; i < n ; i++){
            if (matrix[i][m-1] == target){
                return true;
            }
            if(matrix[i][m-1] > target){
                for (int j = 0 ; j < m ;j++){
                    if(matrix[i][j] == target){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 0 <= s 的长度 <= 10000
     * 遇事不决，暴力解法QaQ
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') {
                sb.append("%20");
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     *输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     前序遍历 preorder = [3,9,20,15,7]
     中序遍历 inorder = [9,3,15,20,7]
     返回如下的二叉树：
     3
     / \
     9  20
     /  \
     15   7
     *
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if(l > --r) break;
            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if(++l > r) break;
        }
        return res;

    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     * 输入：[3,4,5,1,2]
     * 输出：1
     */
    public int minArray(int[] numbers) {
        /*if(numbers.length == 1){
            return numbers[0];
        }
        for (int i = 0; i < numbers.length -1 ; i++){
            if(numbers[i] > numbers[i+1]){
                return numbers[i+1];
            }
        }
        return numbers[0];*/
        //二分查找
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * 图的遍历等，待做。。。。
     */
    public boolean exist(char[][] board, String word) {
        int wordLength = word.length();
        String[] str = word.split("");
        int m = board.length;
        if(m == 0){
            return false;
        }
        int n = board[0].length;
        if(n == 0){
            return false;
        }
        return false;
    }

    /**
     * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     * 对一个二进制数 - 1，则该二进制从低位到高位的第一个1将变成0，其后所有的数将变成1。将该数与原数进行按位与运算，能将这些新增的1变为0。
     * 最终的效果是消去了从低位到高位的第一个1.不断重复此操作，知道所有的位数值为0，所需执行操作的次数便是1的个数。
     */
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            n &= n - 1;
            res++;
        }
        return res;
    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     * 从1打印到10的n次方-1，定义end大小的数组即可。
     */
    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10, n) - 1;
        int[] res = new int[end];
        for(int i = 0; i < end; i++)
            res[i] = i + 1;
        return res;
    }

    /**
     * 打扰了
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     */
    public boolean isNumber(String s) {
        return false;
    }

    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     */
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val){
            return head.next;
        }
        ListNode pre = head, cur = head.next;
        while(cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if(cur != null) {
            pre.next = cur.next;
        }
        return head;
    }


}
