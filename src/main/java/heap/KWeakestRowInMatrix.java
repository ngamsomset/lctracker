package heap;

import java.util.*;


/**
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
 * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 *
 * Intuition
 * The more straight forward approach is to count the strength of each row and keep in an array. Then we can sort array.
 * As a result we get the sorted array. BUT, we need original index of that particular value.
 *
 * HashMap
 * - create a hashmap and store the key(strength) and value(index)
 * - create a List that store keys, then we sort them.
 * - loop through the sorted list and the hashmap to match the key and value(which is the index that we want)
 * and append it to the list.
 *
 * Time - calculating strength (looping) take O(m x n). Inserting into hashmap cost O(1).
 * Sorting take O(mlogm). pulling out the value is O(k). total = O(m log m)
 * Combined two together we get m x n + m log m = m x (n + log m).
 *
 * Space - Create hashmap cost O(m);
 *
 *
 */
public class KWeakestRowInMatrix {
    public static void main(String[] args) {
        int[][] mat = {{1,1,1,0,0},
                {1,1,0,0,0},
                {0,0,0,0,0},
                {1,1,1,1,0},
                {1,1,0,0,0}};

        System.out.println(Arrays.toString(kWeakestRow(mat, 3)));

    }
    public static int[] kWeakestRow(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        //calculate all strengths and put them in the hashmap
        Map<Integer, List<Integer>> strengths = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int strength = 0;
            for (int j = 0 ; j < n; j++) {
                //count only 1
                if (mat[i][j] == 0) {
                    break;
                }
                strength++;
            }

            if (!strengths.containsKey(strength)) {
                strengths.put(strength, new ArrayList<>());
            }

            //the reason we need to use .get and .add is because our mapping value is
            //a List so we can call add method.
            strengths.get(strength).add(i);
        }

        //if use Hashmap, we need to sort the key
        //if use Treemap, we don't need the sorting part, Treemap will sort the key.
        //ps. time complexity is the same as insertion into Treemap is more expensive.
        List<Integer> sortedStrength = new ArrayList<>(strengths.keySet());
        Collections.sort(sortedStrength);

        //get indexes for the k smallest strength
        int[] indexes = new int[k];
        int i = 0;
        for (int key:sortedStrength) {
            for(int index:strengths.get(key)) {
                indexes[i] = index;
                i++;
                if (i == k) break;
            }
            if (i==k) break;
        }
        return indexes;
    }
}
