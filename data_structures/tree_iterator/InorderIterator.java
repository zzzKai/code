class InorderIterator {
	private Deque<TreeNode> stack;

	public InorderInterator(TreeNode root) {
		stack = new ArrayDeque<>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public Integer next() {
		if (!hasNext()) {
			throw NoSuchElementException("End of the iterator!");
		}
		TreeNode cur = stack.pop();
		TreeNode tmp = cur.right;
		while (tmp != null) {
			stack.push(tmp);
			tmp = tmp.left;
		}
		return cur.val;
	}







	// We can use firstNode function to aggregate the logic
	private Deque<TreeNode> stack;

	public InorderInterator(TreeNode root) {
		stack = new ArrayDeque<>();
		firstNode(root);
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public Integer next() {
		if (!hasNext()) {
			throw NoSuchElementException("End of the iterator!");
		}
		TreeNode cur = stack.pop();
		firstNode(cur.right);
		return cur.val;
	}

	private void firstNode(TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}
}
