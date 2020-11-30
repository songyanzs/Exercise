package demo1.November.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {


    /**
     * 冒泡排序
     * 两个相邻元素比较
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if(arr.length>0){
            int index = arr.length;
            for(int i =0 ;i<arr.length;i++){
                for (int j =0 ;j<index-1;j++){
                    if(arr[j]>arr[j+1]){
                        int z = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = z;
                    }
                }
                index--;
            }
        }
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectionSort(int[] arr){
        if(arr.length>0){
            for(int i=0 ;i<arr.length;i++){
                int index = i; //记录下标
                int min = arr[i];
                for(int j=i;j<arr.length-1;j++){
                    if(min>arr[j+1]){
                        min = arr[j+1];
                        index = j+1;
                    }
                }
                int z = arr[index];
                arr[index] = arr[i];
                arr[i] = z;
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr){
        if(arr.length>0){
            for (int i = 0 ; i <arr.length ; i++){
                for (int j = i; j>0 ;j--){
                    if(arr[j]<arr[j-1]){
                        int z = arr[j-1];
                        arr[j-1] = arr[j];
                        arr[j] = z;
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        int[] arr = new int[]{12,5,4,2,1,9,8,5,7};
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
        quickSort(arr,0,arr.length-1);
        for (int i : arr){
            list.add(i);
        }
        System.out.println(list);
    }
}
