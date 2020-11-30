package demo1.November.LeeLixi;


import java.util.*;

public class NovExercise {

    public static void main(String[] args) {
        NovExercise novExercise = new NovExercise();
//        int[] arr = new int[]{3,2,3,1,2,4,5,5,6};
    }


    /**
     * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
     * 输出：true
     * 解释：重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
     * @param hand
     * @param W
     * @return
     */
    public static boolean isNStraightHand(int[] hand, int W) {
        int n=hand.length;
        if(n%W!=0) return false;
        Arrays.sort(hand);
        boolean visited[]=new boolean[n];
        int i=0;
        while(i<n){
            if(!visited[i]){
                int key=hand[i],j=i; //从i往后找w个
                while(key<hand[i]+W)
                {
                    if(j>=n) return false;
                    if(!visited[j] && hand[j]==key) {
                        visited[j]=true;
                        key+=1;
                    }
                    j++;
                }
            }
            i++;
        }
        return true;
    }

    /**
     * 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
     * 字符（'a' - 'i'）分别用（'1' - '9'）表示。
     * 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。
     * @param s
     * @return
     */
    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < s.length()){
            if(i + 2 < s.length() && s.charAt(i + 2) == '#'){
                sb.append((char)('a' - 1 + Integer.parseInt(s.substring(i,i+2))));
                i += 3;
            }else{
                sb.append((char)('a' - 1 + s.charAt(i) - '0'));
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 升序有序数组，出现次数超过25%的数字（只有一个）
     * @param arr
     * @return
     */
    public static int findSpecialInteger(int[] arr) {
        int len = arr.length / 4;
        for(int i = 0; i + len < arr.length; i++){
            if(arr[i] == arr[i + len]){
                return arr[i];
            }
        }
        return arr[0];
    }

    /**
     * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        double y = x;
        if(n<0){
            n = - n;
            y = 1/x;
            for(int i = 1 ; i<n;i++){
                y = 1/x*y;
            }
        }else if(n == 0 ){
            return 1;
        }else {
            for(int i = 1 ; i<n;i++){
                y = y*x;
            }
        }
        return y;
    }


    /**
     * 合并两个有序数组
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        for(int i = 0; i<n ; i++){
            A[m+i] = B[i];
        }
        Arrays.sort(A);
    }

    /**
     * 最大子序列和
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * @param nums
     * @return
     */
    public  int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;

        /*自己想的，暴力法，过于复杂，用例有超时
        int n =0 ;
        for (int i = 1 ; i<=nums.length;i++){
            for(int j = 0 ;j<nums.length;j++){
                int m = nums[j];
                for (int z = 1 ;z<i;z++){
                    if(j+z<nums.length){
                        m = m + nums[j+z];
                    }
                }
                if(m>n){
                    n = m;
                }
            }
        }
        return n ;*/
    }

    /**
     * 给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
       /*直接暴力法
        int[] arr = new int[2];
        for(int i = 0 ; i< nums.length-1;i++){
            for(int j = i+1 ; j<nums.length ;j++){
                if(nums[i]+nums[j]==target){
                    arr[0]= i;
                    arr[1]= j;
                }
            }
        }
        return arr;*/
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = 0 ;
        if(prices.length<2){
            return 0;
        }
        int begin =prices[0], end = prices[1];
        for(int i =0 ;i<prices.length;i++){
            begin = prices[i];
            for (int j = i+1; j<prices.length;j++){
                end = prices[j];
                if(end-begin>0&&n < end-begin){
                    n = end - begin;
                }
            }
        }
        return n ;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        /* 我的思路，还是过于复杂了
        int number = nums[0];
        int cishu = 0 ;
        int index = (int) Math.ceil((double) nums.length/2);
        List list = new ArrayList();
        for(int i =0 ; i < nums.length;i++){
            if(!list.contains(nums[i])){
                cishu = 1;
                list.add(nums[i]);
                for (int j = i ;j<nums.length;j++){
                    if(nums[j]== nums[i]){
                        cishu ++;
                    }
                }
                if(cishu > index){
                    number = nums[i];
                }
            }
        }
        return number ;*/
        //先排序 ,众数必是中位数
        Arrays.sort(nums);
        return nums[(nums.length/2)];
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有0移动到数组的末尾，同时保持非零元素的相对顺序。
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        /*我的思路，时间复杂度太大，类似与插入排序的方法
        for(int i = 0 ;i< nums.length;i++){
            for (int j = 0 ; j< nums.length-i-1 ;j++){
                if(nums[j] == 0){
                    int z= nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = z ;
                }
            }
        }*/
        //双指针的方法，只比较一次即可
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }

    /**
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * 不占用其他空间
     * @param nums
     * @return
     */

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] > 0) {
                nums[Math.abs(nums[i]) - 1] = - nums[Math.abs(nums[i]) - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                results.add(i + 1);
            }
        }
        return results;
    }

    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int result = nums[0];
        if(nums.length>0){
            for (int i = 0 ; i <nums.length ; i++){
                for (int j = i; j>0 ;j--){
                    if(nums[j]<nums[j-1]){
                        int z = nums[j-1];
                        nums[j-1] = nums[j];
                        nums[j] = z;
                    }
                }
            }
        }
        /*result = nums[nums.length-1];
        int index = 0 ;
        for(int j = nums.length-1;j>=0;j--){
            if(result>=nums[j]){
                result = nums[j];
                index++;
            }
            if(index==k){
                return result;
            }
        }*/
        result = nums[nums.length-k];
        return result;
    }

}
