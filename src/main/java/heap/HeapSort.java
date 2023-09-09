package heap;
import java.util.Arrays;

/**  The process of heapsort is
 * 1. Heapify (bottom up) - convert an unsorted array into a maxHeap data structure
 * 2. use maxHeap to sort the list.
 *
 * ps. Heap data structure is better than selection sort which take O(n2)
 * while HeapSort take O(nlogn). In place heapification use O(n) + sorting use O(log n).
 * Heap sort is not stable - in practice sometime heap sort is worse than O(nlogn), "cache locality" issue.
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {7,3,2,5,6,10,9,8,1};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        //convert items in an array into heap data structure
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, arr.length, i);
        }

        //sort
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            //swap with the first element
            arr[i] = arr[0];
            arr[0] = temp;
            //we need to reduce the heap size every iteration
            maxHeapify(arr, i, 0);
        }
    }

    public static void maxHeapify(int[] arr, int heapSize, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != index) {
            int temp = arr[index];
            arr[index] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, heapSize, largest);
        }
    }
}
