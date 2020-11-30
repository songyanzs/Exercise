package demo1.November.questions;

public class Merge {
    /*i从A的末尾，j从B末尾开始，两两比较，大的放在末端。
    比如A[4,5,7] B[1,2,6],。
    7比6大，A[5]处放置7，然后i前移。
    再次比较5 和6,6放在A[4]处。
    如此类推如果A穷尽，把B元素依次放进A的前几个位置，如果B穷尽，正好结束。*/

    public class Solution {
        public void merge(int A[], int m, int B[], int n) {
            if(m==0){
                for(int k=0;k<B.length;k++)
                    A[k]=B[k];
            }
            else{
                int i=m-1;
                int j=n-1;
                int s=m+n-1;
                while(j>=0&&i>=0){
                    if(A[i]>B[j])
                        A[s--]=A[i--];
                    else
                        A[s--]=B[j--];
                }
                if(i==-1){
                    for(;j>=0;j--)
                        A[j]=B[j];
                }
            }

        }
    }

}
