package heap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Given an array if nums [1,1,1,2,2,3] and k = 2 return the top k frequent elements
 * We need to find a way to store a number in a given array and it's frequency
 * using Hashmap is the best data structure to store this kind of information.
 *
 * Naive approach
 * - store the number and it's frequency in the Hashmap
 * - create a new list that store a key(value) and value(frequency) which look like
 * [[1,3],[2,2],[3,1]] and then sort them from the frequency.
 * - time O(nlogn), space O(n)
 *
 * a better approaches.
 * 1. using hashmap and heap.
 * - create a hashmap with value:frequency
 * - create a minHeap with the size of k if the element more than k we poll it out.
 * the value of the node in the heap is the value itself not frequency
 * we sort the heap based on it's frequency (the most frequent will be at the top).
 * at the end the top k answer will be what ever left in the heap.
 */

public class topKthFrequent {
    public static void main(String[] args) {

        int[] nums = new int[]{1,1,1,2,2,3};
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));

    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        //create hashmap
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int num:nums) {
            hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
        }

        //create heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> hashmap.get(n1) - hashmap.get(n2));

        //add item to the heap, if item over k, remove it
        for(int n: hashmap.keySet()) {
            heap.add(n);
            if(heap.size() > k) {
                heap.poll();
            }
        }

        //create output array
        int[] top = new int[k];
        for (int i = 0; i < k; i++) {
            top[i] = heap.poll();
        }

        return top;
    }
}
