package 字符串问题;

public class Solution28 {
    //KMP算法
    public static int strStr(String haystack, String needle){
        int n = haystack.length(), m = needle.length();
        if(m == 0){
            return 0;
        }
        int[] pi = new int[m];


        //构建next数组，匹配串的前缀和后缀的交集
        //j不光代表下表，同时也是前缀的长度
        for(int i = 1, j = 0; i < m; i++){
            while(j > 0 && needle.charAt(i) != needle.charAt(j)){
                j = pi[j - 1];
            }
            if(needle.charAt(i) == needle.charAt(j)){
                j++;
            }
            pi[i] = j;
        }

        for(int i =0, j = 0; i < n; i++){
            while(j > 0 && haystack.charAt(i) != needle.charAt(j)){
                j = pi[j - 1];
            }

            if(haystack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if(j == m){
                return i - m + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        String haystack = "sasadbutsad", needle = "sasac";
        System.out.println(strStr(haystack,needle));
    }

}
