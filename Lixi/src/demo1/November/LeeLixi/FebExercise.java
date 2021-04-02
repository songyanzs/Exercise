package demo1.November.LeeLixi;

public class FebExercise {

    public static void main(String[] args) {
        int[] nums = new int[]{4,2,3,5};
        FebExercise febExercise = new FebExercise();
        boolean b = febExercise.checkPossibility(nums);
    }

    /**
     *
     *给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     *我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     */

    public boolean checkPossibility(int[] nums) {
        return true;
    }

}
