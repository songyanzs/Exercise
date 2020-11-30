package demo1.November.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListSum {

    /**
     * 冒泡排序
     * 合并三个链表并且排序
     * @param list1
     * @param list2
     * @param list3
     * @return
     */
    public static List listSum(List list1,List list2 ,List list3){
        List<Integer> resultList = new ArrayList();
        resultList.addAll(list1);
        resultList.addAll(list2);
        resultList.addAll(list3);
        System.out.println(resultList);
        int[] arr = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++) {
            arr[i] = resultList.get(i);
        }
        int index= arr.length;
        for (int y =0;y<index;y++){
            for (int j = 0 ;j<index-1;j++){
                if(arr[j]>arr[j+1]){
                    int z = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = z ;
                }
            }
            index--;
        }
        List resultList1 = new ArrayList();
        for (int x : arr){
            resultList1.add(x);
        }
        return resultList1;
    }

    public static void main(String[] args) {
        List l1 = new ArrayList<>(Arrays.asList(2,4,3,18));
        List l2 = new ArrayList();
        l2.add(5);
        l2.add(6);
        l2.add(4);
        List l3 = new ArrayList<>(Arrays.asList(7,8,9));
        List list = listSum(l1,l3,l2);
        System.out.println(list.toString());
    }
}
