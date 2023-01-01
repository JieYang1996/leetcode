package 动态规划.字符串编辑;

public class Solution72 {
    //简化操作，分为三种情况
    //DP[i][j]表示A的前i个字母和B的前j个字母之间的编辑距离

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        //有一个字符串为空串
        if (n * m == 0){
            return n + m;
        }

        //DP数组
        //加一因为考虑到边界为空字符串的情况
        int[][] dp = new int[n + 1][m + 1];

        //边界条件初始化
        for(int i = 0; i < n + 1; i++){
            dp[i][0] = i;
        }

        for(int j = 0; j < m + 1; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int left_down = dp[i - 1][j - 1];
                //注意charAt是下标从0开始的
                if(word1.charAt(i - 1) != word2.charAt(j - 1)){
                    left_down += 1;
                }
                dp[i][j] = Math.min(Math.min(left, down), left_down);
            }
        }

        return dp[n][m];
    }
}
