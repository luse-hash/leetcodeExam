package com.company;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private Trie[] children;
    private boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        children=new Trie[26];
        isEnd=false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node=this;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int pos=chars[i]-'a';
            if(node.children[pos]==null){
                Trie trie = new Trie();
                node.children[pos]=trie;
            }
            node=node.children[pos];
        }
        node.isEnd=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node=this;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int pos=chars[i]-'a';
            if(node.children[pos]==null){
                return false;
            }
            node=node.children[pos];
        }
        if(node.isEnd==true) return true;
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node=this;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int pos=chars[i]-'a';
            if(node.children[pos]==null){
                return false;
            }
            node=node.children[pos];
        }
        return true;
    }



    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        dfs(candidates,target,target,ans,tmp,0);
        return null;
    }

    public void dfs(int[]candidates,int target,int count,List<List<Integer>>ans,List<Integer>tmp, int pos){
        if(pos==candidates.length) return;
        if(count==0){
            ans.add(tmp);
            return;
        }
        if(count<0) return;
        dfs(candidates,target,count-candidates[pos],ans,tmp,pos+1);
        if(count>=candidates[pos]){
            tmp.add(candidates[pos]);
            dfs(candidates,target,count-candidates[pos],ans,tmp,pos);
            tmp.remove((Integer) candidates[pos]);
        }
    }




    public static void main(String[] args) {
         Trie obj = new Trie();
         obj.insert("apple");
         obj.insert("apple");
         boolean param_2 = obj.search("app");
         boolean param_3 = obj.search("app");
         boolean param_4 = obj.startsWith("app");
        obj.insert("apple");
    }
}
