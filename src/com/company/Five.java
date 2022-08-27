package com.company;

import java.util.ArrayList;
import java.util.List;

public class Five {
//    "bsbininm"
//    "jmjkbkjkv"

//    "abcba"
//    "abcbcba"
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1+1][l2+1];

        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if(text1.charAt(i)==text2.charAt(j)){
                    dp[i+1][j+1]=dp[i][j]+1;
                }else {
                    dp[i+1][j+1]=Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[l1][l2];
    }

    public int minFlipsMonoIncr(String s) {
//        zero,one表示当前翻转的最小次数，
//        zero[i]为到第i位需要翻转的次数（i为0或被翻转为0）
//        one[i]为到第i位需要翻转的次数（i为1或被翻转为1）
//        则one[i+1]位则是从zero[i],one[i]的最小值中，再加上第i+1位的翻转与否
//        zero[i+1]，前面为0，则该位为0时，需不需要翻转
        int length = s.length();
        int[] zero = new int[length+1];
        int[] one = new int[length+1];
        char c = s.charAt(0);
        zero[1]=c=='0'?0:1;
        one[1]=c=='0'?1:0;
        for (int i = 1; i < length; i++) {
            if(s.charAt(i)=='0'){
                zero[i+1]=zero[i];
                one[i+1]=Math.min(zero[i],one[i])+1;
            }else {
                zero[i+1]=zero[i]+1;
                one[i+1]=Math.min(zero[i],one[i]);
            }
        }
        return Math.min(one[length],zero[length]);
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0]=1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i]=1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0]=grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0]=grid[i][0]+dp[i-1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];
        dp[0][0]=triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            dp[i][0]=dp[i-1][0]+triangle.get(i).get(0);
            for (int j = 1; j <= i-1; j++) {
                dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j);
            }
            dp[i][i]=dp[i-1][i-1]+triangle.get(i).get(i);
        }
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            min=Math.min(min,dp[size-1][i]);
        }
        return min;
    }

    public int res;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums,0,target,0);
        return res;
    }

    public void dfs(int[]nums,int pos,int target,int count) {
        if (pos == nums.length && count == target) {
            res++;
        }
        if(pos>=nums.length) return;

        count += nums[pos];
        dfs(nums, pos + 1, target, count);
        count -= nums[pos] * 2;
        dfs(nums, pos + 1, target, count);

    }



    public static void main(String[] args) {
        Five five = new Five();
//        int i = five.longestCommonSubsequence("abcba", "abcbcba");
//        System.out.println(i);
//        "100000001010000"
//        int i = five.minFlipsMonoIncr("00011000");
//        System.out.println(i);
//        int i = five.uniquePaths(7, 3);
//        System.out.println(i);
//        int i = five.minPathSum(new int[][]{{1,2,3}, {4,5,6}});
//        System.out.println(i);

//        [[2],[3,4],[6,5,7],[4,1,8,3]]
//        new int[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}}
//        ArrayList<List<Integer>> lists = new ArrayList<>();
//        int[][] arr = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
//        int[][] arr ={{-10}};
//        for (int i = 0; i < arr.length; i++) {
//            ArrayList<Integer> list = new ArrayList<>();
//            for (int j = 0; j < arr[i].length; j++) {
//                list.add(arr[i][j]);
//            }
//            lists.add(list);
//        }
//        int i = five.minimumTotal(lists);
//        System.out.println(i);
        int[] nums = {1};
        int i = five.findTargetSumWays(nums, 1);
        System.out.println(i);
    }
}
