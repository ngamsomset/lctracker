package heap;
import java.util.*;

public class findKLargest {
    public static void main(String[] args) {
        System.out.println(findKthLargest(Arrays.asList(1,5,7,12,16,27,55), 4));
    }

    public static int findKthLargest(List<Integer> nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int num:nums) {
            list.add(num);
        }
        return quickSelect(list, k);
    }
    public static int minHeapSolution(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num:arr) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.peek();
    }

    public static int quickSelect(List<Integer> nums, int k) {
        int pivotIndex = new Random().nextInt(nums.size());
        int pivot = nums.get(pivotIndex);

        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int num:nums) {
            if (num > pivot) {
                left.add(num);
            } else if (num < pivot) {
                right.add(num);
            } else {
                mid.add(num);
            }
        }

        if (k <= left.size()) {
            return quickSelect(left, k);
        }

        if (left.size() + mid.size() < k) {
            return quickSelect(right, k - left.size() - mid.size());
        }
        return pivot;
    }

}
