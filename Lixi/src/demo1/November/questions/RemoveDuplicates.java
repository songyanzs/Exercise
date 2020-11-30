package demo1.November.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {

    /**
     * 去除有序链表的重复数字，返回长度
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        // 使用双指针
        System.out.println(Arrays.toString(nums));
        if(nums==null || nums.length == 1){
            return nums.length;
        }
        //i：不相等的次数
        int i = 0,j =1;
        while(j<nums.length){
            if(nums[i]==nums[j]){
                j++;
            }else{
                i++;
                nums[i]=nums[j];
                j++;
            }
        }
        return i+1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{0,0,1,1,1,2,2,3,3,4};
        int b = removeDuplicates(a);
        System.out.println(b);
    }
}
