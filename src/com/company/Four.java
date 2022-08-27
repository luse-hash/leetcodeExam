package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Four {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<>();
        dfs(ans,n,0,0,0,new char[n*2],0);
        return ans;
    }

    public void dfs(List<String>ans,int n,int lc,int rc,int count,char[] tmp,int isLegal){
        if(lc==n&&rc==n){


            ans.add(new String(tmp));
            return;
        }
        if(lc>n||rc>n||isLegal<0) return;
        tmp[count]='(';
        dfs(ans,n,lc+1,rc,count+1,tmp,isLegal+1);
        tmp[count]=')';
        dfs(ans,n,lc,rc+1,count+1,tmp,isLegal-1);
    }



    public int countSubstrings(String s) {
        int length = s.length();
        int count=0;
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                dp[i][j]=true;
            }
        }
        for (int i = length-1; i >=0; i--) {
            count++;
            for (int j = i+1; j < length; j++) {
                if(s.charAt(i)!=s.charAt(j)||!dp[i+1][j-1]){
                    dp[i][j]=false;
                }else count++;
            }
        }
        return count;
    }

    public String[][] partition(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                dp[i][j]=true;
            }
        }
        for (int i = length-1; i >=0; i--) {
            for (int j = i+1; j < length; j++) {
                if(s.charAt(i)!=s.charAt(j)||!dp[i+1][j-1]) dp[i][j]=false;
            }
        }
        ArrayList<List<String>> ans = new ArrayList<>();
        dfs(s,dp,new ArrayList<>(),ans,0);
        System.out.println(ans);
        String[][] strings1 = new String[ans.size()][];
        int i=0;
        for (int j = 0; j < ans.size(); j++) {
            strings1[j]=new String[ans.get(j).size()];
            for (int k = 0; k < ans.get(j).size(); k++) {
                strings1[j][k]=ans.get(j).get(k);
            }
        }
        return strings1;
    }

    public void dfs(String s,boolean[][]dp,List<String> tmp,List<List<String>> ans,int pos){
        if(pos==s.length()){
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if(dp[pos][i]){
                tmp.add(s.substring(pos,i+1));
                dfs(s,dp,tmp,ans,i+1);
                tmp.remove(tmp.size()-1);
            }
        }
    }



    public static void main(String[] args) {
        Four four = new Four();
//        List<String> list = four.generateParenthesis(1);
//        System.out.println(list);
//        int abc = four.countSubstrings("aaa");
//        System.out.println(abc);
        String[][] partition = four.partition("google");
        for (String[] strings : partition) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println(" ");
        }
    }
}
