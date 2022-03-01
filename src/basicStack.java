public interface basicStack<T> {

	/* * Returns size of stack less 1
	 * */
	int size();

	/* * Returns True: if stack is empty, False: if stack has items
	 * */
	boolean isEmpty();

	/* * Returns top item without removing
	 * */
	T peek();
	
	/* * Returns and Removes top item
	 * */
	T pop();
	
	/* * Adds new element to top of the data structure
	 * * @param value: item to be added
	 * */
	void offer(T value);
	
}
