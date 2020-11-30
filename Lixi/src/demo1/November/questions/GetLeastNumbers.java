package demo1.November.questions;

import java.util.ArrayList;
import java.util.Arrays;

public class GetLeastNumbers {

    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     *[4,5,1,6,2,7,3,8],4
     * [1,2,3,4]
     */

    public  static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        int index= input.length;
        for (int y =0;y<index;y++){
            for (int j = 0 ;j<index-1;j++){
                if(input[j]>input[j+1]){
                    int z = input[j+1];
                    input[j+1] = input[j];
                    input[j] = z ;
                }
            }
            index--;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        if(k<=input.length){
            for (int i = 0;i<k;i++){
                arr.add(input[i]);
            }
        }
        System.out.println(arr.toString());
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,1,6,2,7,3,8};
        int k = 10;
        GetLeastNumbers_Solution(nums,k);
    }
}
