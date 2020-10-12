/*
* UnionFind/Disjoint Set data structure
*/

class UnionFind {
	private int size;	// The number of elements in this union find
	private int[] sz;	// Used to track the size of each of the component
	private int[] id;	// id[i] points to the parent of i, if id[i] = i then i is a root node
	private int numComponents;	// Track the number of components in the union find

	public UnionFind(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("size <= 0 is not allowed");
		}
		this.size = numComponents = size;
		sz = new int[size];
		id = new int[size];

		for (int i = 0; i < size; i++) {
			id[i] = i;	// link to itself
			sz[i] = 1;	// each component is originally of size one
		}
	}

	// Find which component 'p' belongs to, takes amortized constant time
	public int find(int p) {
		// Find the root of the component/set
		int root = p;
		while (root != id[root]) {
			root = id[root];
		}

		// Compress the path leading back to the root.
		// Doing this operation is called "path compression"
		// and is what gives us amortized time complexity.
		while (p != root) {
			int next = id[p];
			id[p] = root;
			p = next;
		}
		return root;
	}
	// This is an alternative recursive formulation for the find method
	//public int find(int p) {
	//	if (p == id[p]) return p;
	//	return id[p] = find(id[p]);
	//}

	// Unify the components/sets containing elements 'p' and 'q'
	public void unify(int p, int q) {
		int root1 = find(p);
		int root2 = find(q);

		if (root1 == root2) {
			return;
		}

		// Merge smaller component/set into the larger one
		if (sz[root1] < sz[root2]) {
			sz[root2] += sz[root1];
			id[root1] = root2;
		} else {
			sz[root1] += sz[roo2];
			id[root2] = root1;
		}

		// Since the roots found are different we know that the
		// number of components/sets has decreased by one
		numComponents--;
	}


	// Return whether or not the elements 'p' and 'q' are in the same components/set
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	// Return the size of the components/set 'p' belongs to
	public int componentSize(int p) {
		return sz[find(p)];
	}

	// Return the number of elements in this UnionFind/Disjoint set
	public int size() {
		return size;
	}

	// Returns the number of remaining components/sets
	public int components() {
		return numComponents;
	}
}
