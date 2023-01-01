package 动态规划.字符串编辑;

public class Solution10 {
    public boolean isMatch(String s, String p) {// 字符规律p 包含 * .
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;// 初始化
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {// p[j-1]是’*’,  可以对p[j-2]匹配任意自然数次（0次是p[j-2]消失）

                    if (matches(s, p, i, j - 1)) {// 辅助函数 匹配s[i-1]和p[j-2]
                        f[i][j] = f[i][j - 2] || f[i - 1][j];//p[j-2]匹配s[i-1],扔掉s[i-1]看 f[i - 1][j]；或者 扔掉p[j-2]+星号p[j-1] 看f[i][j - 2]
                    }else{
                        f[i][j] = f[i][j - 2];// p[j-2]不匹配s[i-1],扔掉p[j-2]+星号p[j-1]
                    }
                } else {// if(p[j] != ‘*’）p[j]是小写字母，那么必须在s中匹配一个相同的字母
                    if (matches(s, p, i, j)) {// 辅助函数 匹配s[i-1]和p[j-1]
                        f[i][j] = f[i - 1][j - 1];
                    }// else  f[i][j] = false;
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {// 辅助函数 匹配s[i-1]和p[j-1]
        if (i == 0) {// s[i-1]越界，匹配失败
            return false;
        }
        if (p.charAt(j - 1) == '.') {// 当p[j-1] 为 '.' ,可以匹配任何单个字符
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);// 两个字符相等 s[i-1] == p[j-1]
    }
}
