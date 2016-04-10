import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public class main {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst=new BinarySearchTree<Integer>();
		bst.addAll(sortedArray(10));
			System.out.println(bst.size());
			System.out.println(bst.preOrder());
			bst.remove(2);
			System.out.println(bst.preOrder());
	
	}
	
	
	static ArrayList createRandomArray(int length){
		ArrayList<Integer> arr=new ArrayList<Integer>();
		Random rand=new Random();
		for (int i=0;i<length;i++){
			arr.add(rand.nextInt(10000));
		}
		return arr;
	}
	
	static ArrayList sortedArray(int length){
		ArrayList<Integer> arr=new ArrayList<Integer>();
		Random rand=new Random();
		for (int i=0;i<length;i++){
			arr.add(rand.nextInt(10));
		}
		return arr;
	}
}
