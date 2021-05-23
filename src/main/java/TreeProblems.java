import java.util.*;

public class TreeProblems {

    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
        TreeProblems btrsv = new TreeProblems();
        List<Integer> right = btrsv.getRightView(bt.root);
        List<Integer> left = btrsv.getLeftView(bt.root);
        List<Integer> preOrder =  new ArrayList<>();
        btrsv.getPreOrder(bt.root,preOrder);
        btrsv.getPostOrder(bt.root,preOrder);
        btrsv.getInOrder(bt.root,preOrder);
        List<List<Integer>> lists = btrsv.zigZagLevelTraversal(bt.root);
        int maxDepth = btrsv.maxDepth(bt.root);
        System.out.println("Max depth = "+maxDepth);
        int minDepth = btrsv.minDepth(bt.root);
        System.out.println("Min depth = "+minDepth);
        int[] nums = new int[]{-10,-3,0,5,9};
        TreeNode bst = btrsv.sortedArrayToBST(nums);
        System.out.println("Tree from sorted array = "+bst.toString());
        right.size();
    }

    private TreeNode sortedArrayToBST(int[] nums) {
        return helper(0,nums.length-1, nums);
    }

    public TreeNode helper(int left, int right, int[] nums){
        if(left > right) return null;
        int p = (left+right)/2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p-1,nums);
        root.right = helper(p+1, right,nums);
        return root;
    }

    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

    private void getPreOrder(TreeNode root,List<Integer> answer) {
        if(root == null){
            return;
        }
        answer.add(root.value);
        System.out.println("pre order "+root.value);
        getPreOrder(root.left,answer);
        getPreOrder(root.right,answer);

    }

    private void getInOrder(TreeNode root,List<Integer> answer) {
        if(root == null){
            return;
        }
        getInOrder(root.left,answer);
        answer.add(root.value);
        System.out.println("in order "+root.value);
        getInOrder(root.right,answer);

    }

    private void getPostOrder(TreeNode root,List<Integer> answer) {
        if(root == null){
            return;
        }
        getPreOrder(root.left,answer);
        getPreOrder(root.right,answer);
        answer.add(root.value);
        System.out.println("post order "+root.value);

    }

    public List<Integer> getRightView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);
        while (!bfs.isEmpty()) {
            int size = bfs.size();
            TreeNode rightmost = bfs.peek();
            System.out.println("righmost node "+ rightmost.value);
            // only peek to look at first/rightmost node
            ans.add(rightmost.value);

            for (int i = 1; i <= size; i++) {   // normal bfs traversal
                TreeNode node = bfs.poll();
                if (node.right != null)
                    bfs.offer(node.right);
                if (node.left != null)
                    bfs.offer(node.left);      // adding to queue with right node first

            }
        }

        return ans;
    }

    public List<Integer> getLeftView(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);

        while (!bfs.isEmpty()) {
            int size = bfs.size();
            TreeNode rightmost = bfs.peek();
            System.out.println("leftmost node "+ rightmost.value);
            // only peek to look at first/rightmost node
            ans.add(rightmost.value);
            for (int i = 1; i <= size; i++) {   // normal bfs traversal
                TreeNode node = bfs.poll();
                if (node.left != null)
                    bfs.offer(node.left);
                if (node.right != null)
                    bfs.offer(node.right);      // adding to queue with right node first
            }
        }
        return ans;
    }

    public List<List<Integer>> zigZagLevelTraversal(TreeNode root){
        boolean dir = true;
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root== null) return ans;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode val = queue.poll();
                level.add(val.value);
                if(val.left!= null) queue.add(val.left);
                if(val.right!= null) queue.add(val.right);
            }
            if(dir){
                ans.add(level);
                dir = false;
            }else{
                // reverse the list
                List<Integer> revLevel = new ArrayList<>();
                for(int i = level.size()-1; i >=0; i--){
                    revLevel.add(level.get(i));
                }
                ans.add(revLevel);
                dir = true;
            }
        }
        return ans;
    }

    public int maxDepth(TreeNode root) {
        return depthOfTree(root,0);
    }

    public int depthOfTree(TreeNode root,Integer depth){
        if(root == null){
            return depth;
        }
        else{
            return Math.max(depthOfTree(root.left,depth+1),depthOfTree(root.right,depth+1));
        }
    }
}
