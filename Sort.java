import java.util.Arrays;
import java.util.Random;

/**
 * Implements and compares various sorting algorithms (Merge sort, Quick Sort, Java's inbuilt sort)
 * @author ADK
 *
 */
public class Sort {

	private static int size = 2000000;
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	
	/**
	 * Insertion sort Implementation
	 * @param arr Array to be sorted
	 * @param p starting index
	 * @param r ending index
	 */
	public static <T extends Comparable<? super T>> void insertionsort(T[] arr, int p, int r) {                      //Insertion Sort
		int i, j;
		T temp;
		for (i = p + 1; i <= r; i++) {
			temp = arr[i];
			j = i;
			while (j > p && arr[j - 1].compareTo(temp) > 0) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;

		}
	}

	/**
	 * Merge Sort Implementation
	 * @param arr Array to be sorted
	 * @param p starting index
	 * @param r ending index
	 */
	public static <T extends Comparable<? super T>> void mergesort(T[] arr, T[] temp, int p, int r)   //Merge Sort
	{           
		if ((r - p + 1) <= 11) {
			insertionsort(arr, p, r);
		} else {
			int q = (p + r) / 2;
			mergesort(arr, temp, p, q);
			mergesort(arr, temp, q + 1, r);
			merge(arr, temp, p, q, r);
		}
	}

	/**
	 * Merge function
	 */
	public static <T extends Comparable<? super T>> void merge(T[] arr, T[] temp, int p, int q, int r)        //Merge Function
	{   

		for (int i = p; i <= r; i++)
			temp[i] = arr[i];
		
		int i = p, j = q + 1;
		
		for(int k=p;k<=r;k++)
		{
			if ((j > r) || (i <= q && temp[i].compareTo(temp[j]) <= 0)) {
				arr[k] = temp[i];
				i++;
			} else {
				arr[k] = temp[j];
				j++;
			}
		}
	}

	/**
	 * Quick Sort Implementation
	 * @param arr Array to be sorted
	 * @param p Start Index
	 * @param r End Index
	 */
	public static <T extends Comparable<? super T>> void quicksort(T[] arr, int p, int r)          //Quick Sort
	{
		if ((r - p + 1) <= 11) {
			insertionsort(arr, p, r);
		} else {
				int q = randomized_partition(arr, p, r);
				quicksort(arr, p, q - 1);
				quicksort(arr, q + 1, r);
		}
	}

	/**
	 * Randomized partition function
	 */
	public static <T extends Comparable<? super T>> int randomized_partition(T[] arr, int p, int r)  //Randomized Partition (Driver Function)
	{
		T temp;
		int rand = new Random().nextInt(r - p);
		int randomele = rand + p;
		temp = arr[r];
		arr[r] = arr[randomele];
		arr[randomele] = temp;
		int q = partition(arr, p, r);
		return q;
	}

	/**
	 * Partition Function
	 */
	public static <T extends Comparable<? super T>> int partition(T[] arr, int p, int r)    //Partition Function
	{
		T temp;
		int i = p - 1;
		int j;
		for (j = p; j <= r - 1; j++) {
			if (arr[j].compareTo(arr[r]) <= 0) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[r];
		arr[r] = temp;
		return i + 1;
	}

	public static void main(String args[]) {

		Integer[] arr = new Integer[size];
		Integer[] a = new Integer[size];
		Integer[] temp = new Integer[size];
		Random rand = new Random();

		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(10 * size);
		}

		a = Arrays.copyOf(arr, size);
		System.out.println("Merge Sort :");
		timer();
		mergesort(a, temp, 0, size - 1);               // Merge Sort
		timer();

		a = Arrays.copyOf(arr, size);
		System.out.println("Quick Sort :");
		timer();
		quicksort(a, 0, size - 1);                   // Quick Sort
		timer();

		a = Arrays.copyOf(arr, size);
		System.out.println("JAVA Sort :");
		timer();
		Arrays.sort(a);                            // JAVA sort
		timer();

	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / "
				+ memAvailable / 1000000 + " MB.");
	}
	
}
