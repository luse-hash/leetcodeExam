package com.company;


import java.util.*;

public class Two {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode p=l1,q=l2;
        while (p!=null){
            s1.push(p.val);
            p=p.next;
        }
        while (q!=null){
            s2.push(q.val);
            q=q.next;
        }
        Integer in=0;
        ListNode tail=null;
        while (!s1.empty()&&!s2.empty()){
            Integer pop1 = s1.pop();
            Integer pop2 = s2.pop();
            Integer ben=(pop1+pop2+in)%10;
            in=(pop1+pop2+in)/10;
            ListNode node = new ListNode(ben);
            node.next=tail;
            tail=node;
        }
        while (!s1.empty()){
            Integer pop1 = s1.pop();
            Integer ben=(pop1+in)%10;
            in=(pop1+in)/10;
            ListNode node = new ListNode(ben);
            node.next=tail;
            tail=node;
        }
        while (!s2.empty()){
            Integer pop2 = s2.pop();
            Integer ben=(pop2+in)%10;
            in=(pop2+in)/10;
            ListNode node = new ListNode(ben);
            node.next=tail;
            tail=node;
        }
        if(in!=0){
            ListNode node = new ListNode(in);
            node.next=tail;
            tail=node;
        }

        return tail;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer ans=0;
        for (String token : tokens) {
            if(fuhao(token)){
                Integer pop1 = stack.pop();
                Integer pop2 = stack.pop();
                ans=cal(pop2,pop1,token);
                stack.push(ans);
            }else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public Boolean fuhao(String s){
        if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")) return true;
        return false;
    }

    public Integer cal(Integer pop1,Integer pop2,String s){
        switch (s){
            case "+":return pop1+pop2;
            case "-":return pop1-pop2;
            case "*":return pop1*pop2;
            case "/":return pop1/pop2;
        }
        return 0;
    }

    public int sumNumbers(TreeNode root) {
        int ans=0;
        caltree(root,0);
        return ans;
    }
    public int ans=0;
    public void caltree(TreeNode root,int tmp){
        if(root==null) return;
        if(root.left==null&&root.right==null){
            tmp=root.val+tmp*10;
            ans+=tmp;
            return;
        }else{
            tmp=root.val+tmp*10;
        }

        caltree(root.left,tmp);
        caltree(root.right,tmp);
        return;
    }


    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L,1);
        return dfs(root, map, 0L, targetSum);
    }


    public int dfs(TreeNode root, Map<Long,Integer> map,Long curr,int targetSum){
        if(root==null) return 0;
        curr+=root.val;
        int res=0;
        res=map.getOrDefault(curr-targetSum,0);
        map.put(curr,map.getOrDefault(curr,0)+1);
        res+=dfs(root.left,map,curr,targetSum);
        res+=dfs(root.right,map,curr,targetSum);
        map.put(curr,map.getOrDefault(curr,1)-1);
        return res;
    }


    public TreeNode convertBST(TreeNode root) {
        rnl(root,0);
        return root;
    }


    public void rnl(TreeNode root,int curr){
        if(root==null) return;
        rnl(root.right,curr);
        root.val=root.val+curr;
        curr=root.val;
        rnl(root.left,curr);
    }


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        <桶号，数组下标>
        HashMap<Long, Integer> map = new HashMap<>();
        long w=(long)t+1;
        for (int i = 0; i < nums.length; i++) {
            long bulk = bulk(nums[i],w);
            if (map.containsKey(bulk)) {
                return true;
            }
            if(map.containsKey(bulk-1)&&Math.abs(map.get(bulk-1)-i)<w) return true;
            if(map.containsKey(bulk+1)&&Math.abs(map.get(bulk+1)-i)<w) return true;
            map.put(bulk,i);
            if(i>=k){
                map.remove(bulk(nums[i-k],w));
            }
        }
        return false;
    }

    public long bulk(int num,long t){
        if(num>=0){
            return num/t;
        }else {
            return (num+1)/t-1;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root==null) return list;
        TreeNode p=root,q=root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode tmp=null;
        while (!queue.isEmpty()){
            p=queue.poll();

            if(p==q){
                list.add(q.val);
                q=tmp;
            }
            if(p.left!=null){
                queue.add(p.left);
                tmp=p.left;
            }
            if(p.right!=null){
                queue.add(p.right);
                tmp=p.right;
            }
        }
        return list;
    }

    public int findBottomLeftValue(TreeNode root) {
        TreeNode p=root,q=root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode tmp=null,ret=root;
        while (!queue.isEmpty()){
            p=queue.poll();
            if(p==q){
                q=tmp;
                ret=queue.peek();
            }
            if(p.left!=null){
                queue.add(p.left);
                tmp=p.left;
            }
            if(p.right!=null){
                queue.add(p.right);
                tmp=p.right;
            }
        }
        assert ret != null;
        return ret.val;
    }

//    java.lang.AssertionError
//  at line 37, Solution.findBottomLeftValue
//  at line 54, __DriverSolution__.__helper__
//  at line 84, __Driver__.main


    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode p=listNode;
        int[] ints = new int[]{};
        for (int j = 0; j < ints.length; j++) {
            ListNode node = new ListNode(ints[j]);
            p.next=node;
            p=p.next;
        }

//        ListNode listNode1 = new ListNode(7);
//        ListNode q=listNode1;
//        int[] int2 = new int[]{3};
//        for (int j = 0; j < int2.length; j++) {
//            ListNode node = new ListNode(int2[j]);
//            q.next=node;
//            q=q.next;
//        }
//        Two two = new Two();
//        ListNode node = two.addTwoNumbers(listNode, listNode1);
//        p=node;
//        while (p!=null){
//            System.out.println(p.val);
//            p=p.next;
//
//        String[] ss = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
//        Two two = new Two();
//        int i = two.evalRPN(ss);
//        System.out.println(i);

//        nums = [1,2,3,1], k = 3, t = 0
        int[] arr = {1,5,9,1,5,9};
        Two two = new Two();
        boolean b = two.containsNearbyAlmostDuplicate(arr, 2, 3);
        System.out.println(b);




    }
}

