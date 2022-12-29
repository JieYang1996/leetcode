package 动态规划.零一背包;

public class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];

        //i有一位的偏移，因为从dp[i - 1]子状态开始
        for(int i = 1; i <= length; i++){
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            //0是边界条件，m, n是可以取到的
            for(int j = 0; j <= m; j++){
                for(int k = 0; k <= n; k++){
                    //先把上一行抄下来，再修改状态
                    //合并两种情况的写法
                    dp[i][j][k] = dp[i - 1][j][k];
                    if(j >= zeros && k >= ones){
                        dp[i][j][k] = Math.max(dp[i - 1][j - zeros][k - ones] + 1, dp[i][j][k]);
                    }
                }
            }
        }

        return dp[length][m][n];

    }

    public int[] getZerosOnes(String strs){
        int[] zerosOnes = new int[2];
        int length = strs.length();

        for(int i = 0; i < length; i++){
            zerosOnes[strs.charAt(i) - '0']++;
        }

        return zerosOnes;
    }
}
