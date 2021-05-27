package com.syzs.algorithm;

public class May {

    public static void main(String[] args) {
        May may = new May();
        int[] nums = new int[]{1,1,2};
        System.out.println(may.removeDuplicates(nums));
    }


    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     */
    public ListNode reverseList(ListNode head) {
        return null;
    }

    /**
     给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     输入：nums = [0,0,1,1,1,2,2,3,3,4]
     输出：5, nums = [0,1,2,3,4]
     解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length ==0){
            return 0;
        }
        int left = 0;
        for(int right = 1; right < nums.length; right++){
            if(nums[left]!=nums[right]){
                left++;
                nums[left] = nums[right];
            }
        }
        return left+1;
    }
}
