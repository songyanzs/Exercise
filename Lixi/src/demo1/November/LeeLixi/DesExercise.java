package demo1.November.LeeLixi;

import java.util.ArrayList;
import java.util.List;

public class DesExercise {

    public static void main(String[] args) {
        DesExercise desExercise =new DesExercise();
        int[] arr = new int[]{2,3,-2,4,9};
        System.out.println(desExercise.maxProduct(arr));
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int a=1;
        int max=nums[0];

        for(int num:nums){
            a=a*num;
            if(max<a)max=a;
            if(num==0)a=1;

        }
        a=1;
        for(int i=nums.length-1;i>=0;i--){
            a=a*nums[i];
            if(max<a)max=a;
            if(nums[i]==0)a=1;
        }
        return max;
    }
}
