package com.poc.test;

import java.util.ArrayList;
import java.util.List;

public class Dfs {

    static TreeNode root;
    static List<Integer> number = new ArrayList<>();
    static List<TreeNode> bfsList = new ArrayList<>();
    public static void main(String args[]) {
        int[] nums = {-10, 9, 20, 0, 0, 15, 7};

        root = new TreeNode(nums[0]);
        root.left = new TreeNode(nums[1]);
        root.right =  new TreeNode(nums[2]);
        root.right.left =  new TreeNode(nums[5]);
        root.right.right =  new TreeNode(nums[6]);
        int sum = 0;
        bfs(root, sum);
        System.out.println(sum);
    }

    static void bfs(TreeNode root, int sum) {
        if (root == null)
            return;
        sum += root.val;
        root.visited = true;

        bfs(root.left, sum);
        bfs(root.right, sum);
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      boolean visited;
      TreeNode(int x) { val = x; }
    }
}


