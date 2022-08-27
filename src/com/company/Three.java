package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Three {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k,(o1, o2) -> {
            return nums1[o1[0]]+nums2[o1[1]]-nums1[o2[0]]-nums2[o2[1]];
        });
        ArrayList<List<Integer>> ans = new ArrayList<>();
        int l1=nums1.length;
        int l2=nums2.length;
        for (int i = 0; i < Math.min(l1,k); i++) {
            queue.add(new int[]{i,0});
        }

        while (k-->0&&!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int[] poll = queue.poll();
            list.add(nums1[poll[0]]);
            list.add(nums2[poll[1]]);
            ans.add(list);
            if(poll[1]+1<l2){
                queue.add(new int[]{poll[0],poll[1]+1});
            }
        }
        return ans;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        dfs(candidates,target,target,ans,tmp,0);
        return ans;
    }


    public void dfs(int[]candidates,int target,int count,List<List<Integer>>ans,List<Integer>tmp, int pos){
        if(pos==candidates.length) return;
        if(count==0){
            ans.add(new ArrayList<>(tmp));
            return;
        }
        if(count<0) return;

        if(count>=candidates[pos]){
            tmp.add(candidates[pos]);
            dfs(candidates,target,count-candidates[pos],ans,tmp,pos);
            tmp.remove(tmp.size()-1);
        }
        dfs(candidates,target,count,ans,tmp,pos+1);

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        dfs1(candidates,target,0,target,ans,tmp);
        return ans;
    }

    public void dfs1(int[]candidates,int target,int pos,int count,List<List<Integer>> ans,List<Integer>tmp){
        if(count==0){
            ans.add(new ArrayList<>(tmp));
            return;
        }
        if(pos>= candidates.length||count<candidates[pos]) return;

        for (int i = pos; i <candidates.length ; i++) {
            if(i>pos&&(candidates[i - 1] == candidates[i])) continue; //这一步很重要，即使有重复的【1，1，6】这样的答案，也只会在第一次时重复
            tmp.add(candidates[i]);
            dfs1(candidates, target, i+1, count - candidates[i], ans, tmp);
            tmp.remove(tmp.size() - 1);

        }

    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        boolean[] isUsed = new boolean[nums.length];
        dfs2(nums,ans,tmp,0,isUsed);
        return ans;
    }

    public void dfs2(int[]nums,List<List<Integer>>ans,List<Integer>tmp,int pos,boolean[]isUsed){
        if(pos==nums.length){
            ans.add(new ArrayList<>(tmp));
            return;
        }
        if(pos> nums.length) return;
        int last=-11;
        for (int i = 0; i < nums.length; i++) {
            if(!isUsed[i]&&last!=nums[i]) {
                tmp.add(nums[i]);
                isUsed[i]=true;
                dfs2(nums,ans,tmp,i+1,isUsed);
                isUsed[i]=false;
                tmp.remove(tmp.size()-1);
                last=nums[i];
            }

        }
    }


    public static void main(String[] args) {
        Three three = new Three();
////        nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//        List<List<Integer>> lists = three.kSmallestPairs(new int[]{1,2}, new int[]{3}, 3);
//
//        System.out.println(lists);
//        candidates = [2,3,6,7], target = 7
//        List<List<Integer>> lists = three.combinationSum(new int[]{1}, 2);
//        System.out.println(lists);
//        List<List<Integer>> lists = three.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
//        System.out.println(lists);
        List<List<Integer>> lists = three.permuteUnique(new int[]{1, 1, 2});
        System.out.println(lists);

    }
}
