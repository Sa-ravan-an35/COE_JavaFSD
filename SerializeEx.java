import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SerializeEx {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,"); 
        } else {
            sb.append(root.val).append(",");
            serializeHelper(root.left, sb); 
            serializeHelper(root.right, sb); 
        }
    }

    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        String value = queue.poll();
        
        if (value.equals("#")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(value));
        
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        
        return node;
    }

    public static void main(String[] args) {
         SerializeEx serializer = new SerializeEx();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        String serialized = serializer.serialize(root);
        System.out.println("Serialized: " + serialized);

        TreeNode deserializedRoot = serializer.deserialize(serialized);
        System.out.println("Deserialized: " + deserializedRoot.val); 
    }
}
