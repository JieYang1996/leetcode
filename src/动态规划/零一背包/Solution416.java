package 动态规划.零一背包;

//二维动态规划
//可以用一维节约空间

public class Solution416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        if(n < 2){
            return false;
        }

        int sum = 0, maxNum = 0;
        for(int num : nums){
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        //如果是和为奇数
        if(sum % 2 != 0){
            return false;
        }

        //最大的数超过和的一半，代表无法平均分成两个子集
        int target = sum / 2;
        if(maxNum > target){
            return false;
        }

        boolean[][] dp = new boolean[n][target + 1];
        for(int i = 0; i < n; i++){
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;

        //i从1开始因为边界0已经被初始化为true
        for(int i = 1; i < n; i++){
            int num = nums[i];
            for(int j = 1; j <= target; j++){
                if(j >= num){
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n - 1][target];
    }
}
