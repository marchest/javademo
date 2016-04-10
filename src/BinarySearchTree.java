import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

  private Node<T> root;
	private int size = 0;

	/**
	 * Adds a data entry to the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to add
	 */
	public void add(T data) {
		root = add(data, root);
	}
	/**
	 * Recursive add() method helper
	 */
	private Node<T> add(T data, Node<T> node){	
			if(node != null) {
				if(data == null){
					node.right = add(data, node.right);
				}else{
					if(node.getData() == null){
						node = new Node<T>(data);
						node.right = add(null, node.right);
					}
					if(data.compareTo(node.data) < 0){
						node.left = add(data, node.left);
					}
					if(data.compareTo(node.data) > 0){
						node.right = add(data, node.right);		
					}
				}			
			} 
			else{
				node = new Node<T>(data);
				size++;
			}
			
		return node;
	} 
	/**
	 * Adds each data entry from the collection to this BST
	 * 
	 * @param c
	 */
	public void addAll(Collection<? extends T> c) {
		for(T data : c){
			add(data);	
		}
	}
	/**
	 * Removes a data entry from the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry (null if nothing is removed)
	 * 
	 */
	public T remove(T data) {
		boolean b = false;
		if(contains(data)){
			b = true;
		}
		root = remove(root, data);
		if(b){
			return data;
		}
		return null;
	}
	private Node<T> remove(Node<T> here, T data) {
		if (here != null) {
			if(data == null){
				here.right = remove(here.right, data);
				if(here.getData() == null){
					here = removeNode(here);
				}
			}else{
				if (data.compareTo(here.getData()) < 0) {
					here.left = remove(here.left, data);
				} else if (data.compareTo(here.getData()) > 0) {
					here.right = remove(here.right, data);
				} else {
					here = removeNode(here);
				}
			}
			
		}
		return here;
	}
	private Node<T> removeNode(Node<T> here) {
		if (here.left == null)
			here = here.right;
		else if (here.right == null)
			here = here.left;
		else {			
				Node<T> big = here.left;
				Node<T> last = null;
				while (big.right != null) {
					last = big;
					big = big.right;
				}
				here.data = big.data;
				if (last == null) {
					here.left = big.left;
				} else {
					last.right = big.left;
				}
			}	
		return here;
	}

	/**
	 * Checks if the BST contains a data entry
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the BST 
	 */
	public boolean contains(T data) {
		if(root == null){
			return false;
		}else{
			return search(data, root);
		}
	}
	public boolean search(T data, Node<T> node){
		if(data != null){
			if(data.compareTo(node.getData()) == 0){
				return true;
			}else{
				if(data.compareTo(node.getData()) < 0){
					if(node.left == null){
						return false;
					}else{
						return search(data, node.left);
					}
				}else if(data.compareTo(node.getData()) > 0){
					if(node.right == null){
						return false;
					}else{
						return search(data, node.right);
					}
				}
			}	
		}
		else{
			Node<T> max = root;
			while(max != null){			
				if(max.getData()==null){
					return true;
				}
				max = max.right;
			}
		}
		return false;		
	}
	
	/**
	 * Finds the pre-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in pre-order
	 */
	public List<T> preOrder() {
		List<T> list = new ArrayList<T>();
		preOrder(root, list);
		return  list;	
	}
	public void preOrder(Node<T> node, List<T> list) {
		if(node != null){	
			list.add(node.getData());
			preOrder(node.left,list);
			preOrder(node.right,list);
		}	
	}

	/**
	 * Finds the in-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in in-order
	 */
	public List<T> inOrder() {
		List<T> list = new ArrayList<T>();
		inOrder(root, list);
		return list;
	}
	
	public void inOrder(Node<T> node, List<T> list){
		if(node != null){
			inOrder(node.left, list);
			list.add(node.getData());
			inOrder(node.right, list);	
		}
	}
	/**
	 * Finds the post-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in post-order
	 */
	public List<T> postOrder() {
		List<T> list = new ArrayList<T>();
		postOrder(root, list);
		return list;
	}
	public void postOrder(Node<T> node, List<T> list){
		if(node != null){
			postOrder(node.left, list);
			postOrder(node.right, list);
			list.add(node.getData());
		}
	}
	
	/**
	 * Checks to see if the BST is empty
	 * 
	 * @return If the BST is empty or not
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Clears this BST
	 */
	public void clear() {
		root = null;
	}
	
	/**
	 * @return the size of this BST
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * First clears this BST, then reconstructs the BST that is
	 * uniquely defined by the given preorder and inorder traversals
	 * 
	 * (When you finish, this BST should produce the same preorder and
	 * inorder traversals as those given)
	 * 
	 * @param preorder a preorder traversal of the BST to reconstruct
	 * @param inorder an inorder traversal of the BST to reconstruct
	 */
	
	public void reconstruct(List<? extends T> preorder, List<? extends T> inorder) {
		if(preorder == null){
			throw new NullPointerException("Cannot build a tree if preorder is null");
		}
		clear();
		root = reconstruct(preorder, inorder, 0, 0, preorder.size());
	}
	
	private Node<T> reconstruct(List<? extends T> preorder, List<? extends T> inorder, int p, int q, int length){
		if(preorder == null || length ==0){
			return null;
		}
		Node<T> node = new Node<T>(preorder.get(p));
		int offset = searchInOrderOffsetPre(inorder, 	preorder.get(p), q);
		node.left = reconstruct(preorder, inorder, p + 1, q, offset);
		node.right = reconstruct(preorder, inorder, p + offset + 1, q + offset + 1,length - (offset + 1));
		
	return node;			
	}
	private  int searchInOrderOffsetPre(List<? extends T> inorder, T key, int index) {
		int offset = 0;
		for (int i = index; i < inorder.size(); i++) {
			if(inorder.get(i) == null && key == null){
				return offset;
			}
			if (inorder.get(i).compareTo(key) == 0 ) {
				return offset;
			}	
			offset++;
		}
		return -1;
		}	

	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}
	