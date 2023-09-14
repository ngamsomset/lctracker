package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an arrays of stones where all of the number in an array represent the weight of each
 * stone. On each turn, we choose two heaviest stones and smash them together with
 * conditions of
 * 1. if x == y, both stones are destroyed
 * 2. if x != y, x is destroyed and the stone of y has new weight of y - x.
 *
 * Intuition
 * the key word here is "heaviest" stones. which means we will use maxHeap to solve this problem.
 *
 * Process.
 * 1. Create a maxHeap from a given array.
 * 2. loop through heap and get top 2 stones and compare them.
 * 3. return the last stone.
 *
 * Time - O(nlogn). Creating heap is O(n). removing, adding from heap is O(logn)
 * we remove twice and add once so logn + logn + logn = 3logn (ignore constant).
 * Space - O(n) because we create heap.
 *
 */

public class LastStoneWeight {
    public static void main(String[] args) {
        int[] stones = new int[]{2,7,4,1,8,1};
        System.out.println(maxHeaplastStoneWeight(stones));
    }

    public static int maxHeaplastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int stone:stones) {
            heap.add(stone);
        }

        while (heap.size() > 1) {
            int stone1 = heap.remove();
            int stone2 = heap.remove();

            if(stone1 != stone2) {
                heap.add(stone1 - stone2);
            }
        }

        return heap.isEmpty() ? 0 : heap.remove();
    }
}
