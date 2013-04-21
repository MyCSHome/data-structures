import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
class TreeNode<Key, Value> {
	private Value value;
	private Key key;
	TreeNode<Key, Value> left;
	TreeNode<Key, Value> right;

	//constructor should initialise left and right
	/*public TreeNode(Key key, Value value) {
		this.value = value;
		this.key = key;
	}*/
	public void setKey(Key key) {
		this.key = key;
	}
	public TreeNode(Key key, Value value, TreeNode<Key, Value> left, TreeNode<Key, Value> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public void setLeft(TreeNode<Key, Value> left) {
		this.left = left;
	}

	public void setRight(TreeNode<Key, Value> right) {
		this.right = right;
	}

	public TreeNode<Key, Value> getLeft() {
		return left;
	}

	public TreeNode<Key, Value> getRight() {
		return right;
	}

	public Key getKey() {
		return key;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value v) {
		this.value = v;
	}
}

public class MyTree<Key extends Comparable<Key>, Value> {
	TreeNode<Key, Value> root = null;

	public boolean lookUp(Key key) {
		return lookUp(key, root);
	}

	// set the overloading method to be private
	public boolean lookUp(Key key, TreeNode<Key, Value> node) {
		if (node == null)
			return false;
		if (key.equals(node.getKey()))
			return true;
		if (key.compareTo(node.getKey()) < 0)
			return lookUp(key, node.getLeft());
		else
			return lookUp(key, node.getRight());
	}

	public void insert(Key k, Value v) {
		root = insert(k, v, root);
	}

	public TreeNode<Key, Value> insert(Key k, Value v, TreeNode<Key, Value> node) {
		if (node == null) {
			node = new TreeNode<>(k, v, null, null);
			return node;
		}
		//no duplicate keys 
		if (node.getKey().equals(k)) {
			node.setValue(v);
			return node;
		}
		//update this node...
		/*if (k.compareTo(node.getKey()) < 0) {
			return insert(k, v, node.getLeft());
		}
		else
			return insert(k, v, node.getRight());*/
		if (k.compareTo(node.getKey()) < 0) {
			node.setLeft(insert(k, v, node.getLeft()));
			return node;
		}
		else {
			node.setRight(insert(k, v, node.getRight()));
			return node;
		}

	}

	public void delete(Key k) {
		root = delete(k, root);
	}

	public TreeNode<Key, Value> delete(Key k, TreeNode<Key, Value> node) {
		if (node == null) 
			return null;
		if (node.getKey().equals(k)) {
			if (node.getLeft() == null && node.getRight() == null)
				return null;
			if (node.getLeft() == null)
				return node.getRight();
			if (node.getRight() == null)
				return node.getLeft();
			//find the min in the right sub-tree
			else {
				TreeNode<Key, Value> min = getMin(node.getRight());
				//forget update again..............
				/*delete(min.getKey(),node.getRight());
				return min;*/
				node.setKey(min.getKey());
				node.setValue(min.getValue());
				node.setRight(delete(min.getKey(),node.getRight()));
				return node;
			}
		}
		if (node.getKey().compareTo(k) < 0) {
			node.setRight(delete(k, node.getRight()));
			return node;
		}
		else {
			node.setLeft(delete(k, node.getLeft()));
			return node;
		}
	}

	public TreeNode<Key, Value> getMin(TreeNode<Key, Value> node) {
		if (node.getLeft() == null)
			return node;
		else 
			return getMin(node.getLeft());
	}
	
	
	
	
	


}

//Test
class Test{
	//in-order traversal
	public static <Key, Value> int traversal(TreeNode<Key,Value> node) {
		int count = 0;
		if (node == null)
			return 0;
		count += traversal(node.getLeft());
		System.out.print(node.getKey() + " " + node.getValue() + "->");
		count = count + traversal(node.getRight()) + 1;
		return count;
	}
	//careercup 4.1
	public static <Key, Value> int treeHeight(TreeNode<Key, Value> node) {
		if (node == null)
			return 0;
		int leftHeight = treeHeight(node.left);
		if (leftHeight == -1) {
			return -1;
		}
		int rightHeight = treeHeight(node.right);
		if (rightHeight == -1) {
			return -1;
		}
		if (Math.abs(leftHeight - rightHeight) <= 1) {
			return Math.max(leftHeight, rightHeight) + 1;
		}
		else 
			return -1;
	}
	public static <Key extends Comparable<Key>, Value> boolean isBalanced(MyTree<Key, Value> tree) {
		if (tree == null)
			return true;
		if (treeHeight(tree.root) == -1)
			return false;
		else
			return true;
		
		
	}
	
	public static void main(String[] args) {
		MyTree<Integer, String> tree = new MyTree<>();
		tree.insert(5, "one");
		tree.insert(3, "two");
		tree.insert(7, "four");
		tree.insert(6, "four");
		tree.insert(2, "four");
		tree.insert(1, "four");
		tree.insert(4, "four");
		int nodes =traversal(tree.root);
		System.out.println("\n" + nodes);
		tree.insert(7, "seven");
		traversal(tree.root);
		System.out.println("");
		tree.delete(5);
		tree.insert(9, "seven");
		tree.insert(10, "seven");
		tree.insert(11, "seven");
		System.out.println("\n" + " ********" + tree.root.getKey());
		nodes = traversal(tree.root);
		System.out.println("\n" + nodes);
		if (isBalanced(tree))
			System.out.println("\ndone.");
	
	}
	
}



	 















