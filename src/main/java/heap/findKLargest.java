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

    /**
     * second approach(not the best)
     *
     *  array based simulation
     *  Intuition
     *  search for two largest items in an array, if there are not the same value , add a new stone back in.
     *  repeat this step until we got one stone left.
     *
     */
    private static int removeLargest(List<Integer> stones) {
        //find the largest stones in array
        int indexOfLargest = stones.indexOf(Collections.max(stones));
        int result = stones.get(indexOfLargest);
        //remove the largest stone by swap it with the last item in an array
        //1. set the largest stone = last item in array
        stones.set(indexOfLargest, stones.get(stones.size() - 1));
        //2.remove the last item in array.
        stones.remove(stones.size() - 1);
        return result;
    }

    public static int arrayBasedLastStoneWeight(int[] stones) {
        List<Integer> stoneList = new ArrayList<>();
        for(int weight: stones) {
            stoneList.add(weight);
        }

        while (stoneList.size() > 1) {
            int stone1 = removeLargest(stoneList);
            int stone2 = removeLargest(stoneList);
            if (stone1 != stone2) {
                stoneList.add(stone1 - stone2);
            }
        }

        return !stoneList.isEmpty() ? stoneList.remove(0) : 0;
    }
}
