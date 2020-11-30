package demo1.November.questions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWord {
    /**
     * 给定一组单词words找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
     * 若有多个长度相同的结果，返回其中字典序最小的一项
     * 若没有符合要求的单词则返回空字符串。
     * **************************************************************************
     * @param words
     * @return
     */
    public static String longestWord(String[] words) {
        Arrays.sort(words,(o1, o2)->{
            if(o1.length() == o2.length())
                return o1.compareTo(o2);
            else{
                return Integer.compare(o2.length(),o1.length());
            }
        });

        Set<String> set = new HashSet<>(Arrays.asList(words));
        for(String word : words){
            set.remove(word);
            if(find(set,word))
                return word;
        }
        return "";
    }

    public static boolean find(Set<String> set, String word){
        if(word.length() == 0)
            return true;
        for(int i = 0; i < word.length(); i++){
            if(set.contains(word.substring(0,i+1)) && find(set,word.substring(i+1)))
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        String[] words = new String[]{"cat","banana","dog","nana","walk","walker","dogwalker"};
        String result = longestWord(words);
        System.out.println(result);
    }
}
