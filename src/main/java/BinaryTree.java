public class BinaryTree {

    TreeNode root;

    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public TreeNode deleteNode(TreeNode root, int value){
        if(root == null){
            return null;
        }
        if(value == root.value){
            if(root.left == null && root.right == null){
                return null;
            }
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
        }
        if(value < root.value){
            root.left = deleteNode(root.left,value);
            return root;
        }else{
            root.right = deleteNode(root.right,value);
            return root;
        }
    }

}
