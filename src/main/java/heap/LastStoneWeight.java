package heap;

import java.util.*;

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

    /**
     * second approach(not the best)
     *
     *  array based simulation
     *  Intuition
     *  search for two largest items in an array, if there are not the same value , add a new stone back in.
     *  repeat this step until we got one stone left.
     *
     *  Time - O(n2) - the remove function will use O(n) and we need to run twice.
     *  swapping the max item with the last item is O(1)
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

    /**
     * sorted array based simulation
     * similar to the above solution.
     *
     * Time - O(n2) - the sorting part take O(nlogn) but this can't compare to the
     * main loop that take O(n) nested with adding item into an array which take another
     * O(n).
     *
     * Intuition
     * we sort an array in the beginning. each time that we put the stone back we want
     * to maintain the sorted order of an array.
     */

    public static int sortedArrayBasedlastStoneWeight(int[] stones) {
        List<Integer> stoneList = new ArrayList<>();
        for(int stone:stones) {
            stoneList.add(stone);
        }

        Collections.sort(stoneList);

        while (stoneList.size() > 1) {
            int stone1 = stoneList.remove(stoneList.size() - 1);
            int stone2 = stoneList.remove(stoneList.size() - 1);

            if (stone1 != stone2) {
                int newStone = stone1 - stone2;
                int index = Collections.binarySearch(stoneList, newStone);
                if (index < 0) {
                    stoneList.add(- index - 1, newStone);
                } else {
                    stoneList.add(index, newStone);
                }
            }
        }

        return !stoneList.isEmpty() ? stoneList.remove(0) : 0;
    }
}
