package com.javiermarsicano.algorithms.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Given the value of MONEY and the cost of each flavor for t trips to the Ice Cream Parlor, help Sunny and Johnny choose two
 *  distinct flavors such that they spend their entire pool of money during each visit. For each trip to the parlor,
 *   print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated 
 *   integers on a new line. You must print the smaller ID first and the larger ID second.
 *   
 *   The first line contains an integer, , denoting the number of trips to the ice cream parlor. The  subsequent 
 *   lines describe all of Sunny and Johnny's trips to the parlor; each trip is described as follows:

The first line contains MONEY.
The second line contains n.
The third line contains n space-separated integers denoting the cost of each respective flavor.
 * 
 * */

class IceCream implements Comparable{
    int flavor;
    int index;

    public IceCream(int flavor, int index) {
        this.flavor = flavor;
        this.index = index;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(flavor, ((IceCream) o).flavor);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof IceCream) {
            return ((IceCream) o).flavor == flavor;
        } else {
            return false;
        }
    }

}

public class SolutionIceCream {
    private static final HashMap<Integer, Integer> map = new HashMap<>();

    static int binarySearch(int first, int last, IceCream[] arr, int search, IceCream iceCream) {
        if (first == last) {
            if (arr[first].flavor == search) {
                return arr[first].index;
            } else {
                return -1;
            }
        } else {
            int half = (first + last) / 2;
            if (arr[half].flavor >= search) {
                if (arr[half] != iceCream)
                    return binarySearch(first, half, arr, search, iceCream);
                else
                    return binarySearch(half + 1, last, arr, search, iceCream);
            } else {
                return binarySearch(half + 1, last, arr, search, iceCream);
            }
        }
    }

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        IceCream[] arr = createSortedIceCreamsList(cost);

        for (int index = 0; index < cost.length; index++) {
            int flavor = arr[index].flavor;

            if (flavor < money) {
                int remaining = money - flavor;
                Integer ocurrences = map.get(remaining);
                if (ocurrences != null) {
                    if ((remaining != flavor && ocurrences > 0)
                            || (remaining == flavor && ocurrences > 1)) {
                        int result = binarySearch(0, arr.length, arr, remaining, arr[index]);

                        if (result >= 0 && result != index) {
                            if (index + 1 < result)
                                System.out.printf("%d %d\n", index + 1, result);
                            else
                                System.out.printf("%d %d\n", result, index + 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static IceCream[] createSortedIceCreamsList(int[] cost) {
        IceCream[] arr = new IceCream[cost.length];
        for (int i = 0; i < cost.length; i++) {
            arr[i] = new IceCream(cost[i], i + 1);

            Integer ocurrences = map.get(cost[i]);
            if (ocurrences == null) {
                map.put(cost[i], 1);
            } else {
                map.put(cost[i], ocurrences + 1);
            }
        }

        arr = Arrays.stream(arr)
                .sorted()
                .toArray(IceCream[]::new);
        return arr;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
                        
}
/*
3
4
5
1 4 5 3 2
4
4
2 2 4 3
12
5
7 2 5 4 11

*/

