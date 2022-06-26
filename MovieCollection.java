import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieCollection {
	public Movie[] collection;
	public int index = 0;
	public Node root;

	public MovieCollection() {
		root = null;
		collection = new Movie[100];
		index = 0;
	}
	
	public void addNode(Movie name) {

		// Create a new Node and initialize it

				Node newNode = new Node(name);

				// If there is no root this becomes root

				if (root == null) {

					root = newNode;

				} else {

					// Set root as the Node we will start
					// with as we traverse the tree

					Node focusNode = root;

					// Future parent for our new Node

					Node parent;

					while (true) {

						// root is the top parent so we start
						// there

						parent = focusNode;

						// Check if the new node should go on
						// the left side of the parent node

						if (name.getTitle().compareTo(focusNode.movie.getTitle()) < 0) {

							// Switch focus to the left child

							focusNode = focusNode.leftChild;

							// If the left child has no children

							if (focusNode == null) {

								// then place the new node on the left of it

								parent.leftChild = newNode;
								return; // All Done

							}

						} else { // If we get here put the node on the right

							focusNode = focusNode.rightChild;

							// If the right child has no children

							if (focusNode == null) {

								// then place the new node on the right of it

								parent.rightChild = newNode;
								return; // All Done

							}

						}

					}
				}
	}
	
	public Node findNode(String title) {

		// Start at the top of the tree

		Node focusNode = root;

		// While we haven't found the Node
		// keep looking

		while (!focusNode.movie.getTitle().equals(title)) {

			// If we should search to the left

			if (title.compareTo(focusNode.movie.getTitle()) < 0) {

				// Shift the focus Node to the left child

				focusNode = focusNode.leftChild;

			} else {

				// Shift the focus Node to the right child

				focusNode = focusNode.rightChild;

			}

			// The node wasn't found

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}

	public boolean removeNode(Movie name) {
		// Start at the top of the tree

		Node focusNode = root;
		Node parent = root;

		// When searching for a Node this will
		// tell us whether to search to the
		// right or left

		boolean isItALeftChild = true;

		// While we haven't found the Node
		// keep looking

		while (focusNode.movie.getTitle() != name.title) {

			parent = focusNode;

			// If we should search to the left

			if (name.title.compareTo(focusNode.movie.getTitle()) < 0) {

						isItALeftChild = true;

						// Shift the focus Node to the left child

						focusNode = focusNode.leftChild;

					} else {

						// Greater than focus node so go to the right

						isItALeftChild = false;

						// Shift the focus Node to the right child

						focusNode = focusNode.rightChild;

					}

					// The node wasn't found

					if (focusNode == null)
						return false;

				}

				// If Node doesn't have children delete it

				if (focusNode.leftChild == null && focusNode.rightChild == null) {

					// If root delete it

					if (focusNode == root)
						root = null;

					// If it was marked as a left child
					// of the parent delete it in its parent

					else if (isItALeftChild)
						parent.leftChild = null;

					// Vice versa for the right child

					else
						parent.rightChild = null;

				}

				// If no right child

				else if (focusNode.rightChild == null) {

					if (focusNode == root)
						root = focusNode.leftChild;

					// If focus Node was on the left of parent
					// move the focus Nodes left child up to the
					// parent node

					else if (isItALeftChild)
						parent.leftChild = focusNode.leftChild;

					// Vice versa for the right child

					else
						parent.rightChild = focusNode.leftChild;

				}

				// If no left child

				else if (focusNode.leftChild == null) {

					if (focusNode == root)
						root = focusNode.rightChild;

					// If focus Node was on the left of parent
					// move the focus Nodes right child up to the
					// parent node

					else if (isItALeftChild)
						parent.leftChild = focusNode.rightChild;

					// Vice versa for the left child

					else
						parent.rightChild = focusNode.rightChild;

				}
				return true;
	}
	
	public void inOrderTraverseTree(Node root) {
		if (root != null) {
					
				// Traverse the left node
		
				inOrderTraverseTree(root.leftChild);
		
				// Visit the currently focused on node
				System.out.println("Title:"+ root.movie.title);
				System.out.println("Starring:"+ root.movie.starring);
				System.out.println("Director:"+ root.movie.director);
				System.out.println("Genre:"+ root.movie.genre);
				System.out.println("Classification:"+ root.movie.classification);
				System.out.println("Duration:"+ root.movie.duration +" minutes");
				System.out.println("Release Date:"+ root.movie.releaseDate);
				System.out.println("Copies Available:"+ root.movie.available);
				System.out.println("Times Rented:"+ root.movie.time_borrowed);
				System.out.println();
				//System.out.println(focusNode);
		
				// Traverse the right node
		
				inOrderTraverseTree(root.rightChild);
		
			}
		
	}
	public void storeinTraversal(Node root) {
		
		if (root != null) {
					
				// Traverse the left node
		
			storeinTraversal(root.leftChild);
				
				collection[index++] = root.movie;
				
				// Traverse the right node
		
				storeinTraversal(root.rightChild);
		
			}
		
	}

	public void top10sort()
	{

		// If there is no root this becomes root

		if (root == null) {
			System.out.println("asdasfasf no Movie has been rented");
			return;

		} else {
			//storeinTraversal(root);
			heapSort(collection);
			
		}

	}
	

	public void heapify(Movie[] arr, int n, int i) 
	{ 
	    int largest = i; // Initialize largest as root 
	    int l = 2*i + 1; // left = 2*i + 1 
	    int r = 2*i + 2; // right = 2*i + 2 
	  
	    // If left child is larger than root 
	    if (l < n && arr[l].time_borrowed < arr[largest].time_borrowed) 
	        largest = l; 
	  
	    // If right child is larger than largest so far 
	    if (r < n && arr[r].time_borrowed < arr[largest].time_borrowed) 
	        largest = r;
	  
	    // If largest is not root 
	    if (largest != i) 
	    { 
	    	Movie temp = arr[i];
	    	arr[i] = arr[largest];
	    	arr[largest] = temp;
	  
	        // Recursively heapify the affected sub-tree 
	        heapify(arr, n, largest); 
	    } 
	} 
	
	
	// main function to do heap sort 
	void heapSort(Movie arr[]) { 
	int n = index;
	
	    // Build heap (rearrange array) 
	    for (int i = n / 2 - 1; i >= 0; i--) 
	        heapify(arr, n, i); 
	  
	    // One by one extract an element from heap 
	    for (int i=n-1; i>0; i--) 
	    { 
	        // Move current root to end 
	    	Movie temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp;
	  
	        // call max heapify on the reduced heap 
	        heapify(arr, i, 0); 
	    } 
	} 
	
	
	public void printTop10()
	{
		int count = 0;
		for (int i = 0; i < index; i++) {
			if ( collection[i].time_borrowed == 0) {
				break;
			}
			if (count >= 10)
			{
				break;
			}
			System.out.println(collection[i].title + " borrowed " + collection[i].time_borrowed + "times.");
			count++;
			
		}
	}
	
	


}
