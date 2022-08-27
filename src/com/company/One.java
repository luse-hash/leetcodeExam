package com.company;

import java.util.*;

public class One {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()) return false;
        int[] arr1=new int[26];
        int[] arr2=new int[26];
        int len=s1.length();
        for (int i = 0; i < len; i++) {
            arr1[s1.charAt(i)-'a']++;
            arr2[s2.charAt(i)-'a']++;
        }
        if(Arrays.equals(arr1,arr2)) return true;
        for (int i = len; i < s2.length(); i++) {
            arr2[s2.charAt(i)-'a']++;
            arr2[s2.charAt(i-len)-'a']--;
            if(Arrays.equals(arr1,arr2)){
                return true;
            }
        }
        return false;
    }


    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int move=0;
        int max=0;
        for (int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))){
                Integer pos = map.get(s.charAt(i));
                if(pos>=move){
                    move=pos+1;
                }else {
                    max=Math.max(max,i-move+1);
                }

            }else {
                max=Math.max(max,i-move+1);
            }
            map.put(s.charAt(i),i);
        }
        return max;
    }
    public int countSubstrings(String s) {
        int count=0;
        char[] arr = s.toCharArray();
        int l,r;
        for (int i = 0; i < s.length(); i++) {
            for (l=i,r=i;l>=0&&r<s.length() ;l--,r++) {
                if(arr[l]==arr[r]){
                    count++;
                }else break;
            }
            for (l=i,r=i+1;l>=0&&r<s.length() ;l--,r++) {
                if(arr[l]==arr[r]){
                    count++;
                }else break;
            }
        }
        return count;
    }



    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p=head;
        int pos=1;
        while (pos<n&&p!=null){
            p=p.next;
            pos++;
        }
        if(pos<n) return head;
        ListNode q=head,pre=head;
        while (p.next!=null){
            p=p.next;
            pre=q;
            q=q.next;
        }
        if(q==head){
            pre=pre.next;
            head.next=null;
            return pre;
        }
        else {
            pre.next=q.next;
        }
        return head;
    }


    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p=head;
        while (p!=null){
            if(set.contains(p)){
                return p;
            }
            else {
                set.add(p);
                p=p.next;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        One one = new One();
//        boolean b = one.checkInclusion("ab", "eidboaoo");
//        System.out.println(b);
//        int abcabcbb = one.lengthOfLongestSubstring("");
//        System.out.println(abcabcbb);
//        int i = one.countSubstrings("fdsklf");
//        System.out.println(i);
        ListNode listNode = new ListNode(1);
        ListNode p=listNode;
        int[] ints = new int[]{};
        for (int j = 0; j < ints.length; j++) {
            ListNode node = new ListNode(ints[j]);
            p.next=node;
            p=p.next;
            if(ints[j]==2){
                p.next=listNode;
            }
        }

//        ListNode listNode1 = one.removeNthFromEnd(listNode, 1);
//        p=listNode1;
//        while (p!=null){
//            System.out.println(p.val);
//            p=p.next;
//        }
        ListNode listNode1 = one.detectCycle(listNode);
        System.out.println(listNode1.val);



    }
}
