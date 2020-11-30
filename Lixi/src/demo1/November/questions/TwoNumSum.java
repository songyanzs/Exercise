package demo1.November.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoNumSum {

    /**
     * 两数之和，逆序存储在链表，输出逆序的和链表
     * @param l1
     * @param l2
     * @return
     */
    public static List addTwoNumbers(List l1, List l2) {
        List list = new ArrayList();
        int num1 = 0,num2 =0;
        for (int i =0;i<l1.size();i++){
            int num = (int) l1.get(i);
            num1 += num * Math.pow(10, i);
        }
        for (int j =0;j<l2.size();j++){
            int num = (int) l2.get(j);
            num2 += num * Math.pow(10, j);
        }
        int num3 = num1+num2;
        String s=Integer.toString(num3);

        int count=s.length();
        for(int k = count ;k > 0;k--){
            int num4 = (int) (num3/Math.pow(10, k-1));
            num3 = (int) (num3%Math.pow(10,k-1));
            list.add(num4);
        }
        Collections.reverse(list);
        return list;
    }


    public static void main(String[] args) {
        List l1 = new ArrayList<>(Arrays.asList(2,4,3));
        List l2 = new ArrayList();
        l2.add(5);
        l2.add(6);
        l2.add(4);
        List list = addTwoNumbers(l1,l2);
        System.out.println(list.toString());
    }
}
