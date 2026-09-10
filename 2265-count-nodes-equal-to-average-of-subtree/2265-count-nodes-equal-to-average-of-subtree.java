class Solution {

    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // returns {sum of subtree, number of nodes in subtree}
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // First calculate left subtree
        int[] left = dfs(node.left);

        // Then calculate right subtree
        int[] right = dfs(node.right);

        // Include current node
        int sum = node.val + left[0] + right[0];
        int nodes = 1 + left[1] + right[1];

        // Calculate average
        int average = sum / nodes;

        // Check if current node equals average
        if (node.val == average) {
            count++;
        }

        return new int[]{sum, nodes};
    }
}