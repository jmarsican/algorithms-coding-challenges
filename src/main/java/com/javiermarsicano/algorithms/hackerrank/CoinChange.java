package com.javiermarsicano.algorithms.hackerrank;

import java.util.HashMap;
import java.util.Scanner;

public class CoinChange {
	public static long makeChange(int[] coins, int money) {
		return makeChange(coins, money, 0, new HashMap<String, Long>());
	}
	
	public static long makeChange(int[] coins, int money, int index, HashMap<String, Long> memo) {
		if (money == 0) {
			return 1;
		}
		if (index >= coins.length) {
			return 0;
		}
		int amountWithCoin = 0;
		long ways = 0;
		while (amountWithCoin <= money) {
			int remaining = money - amountWithCoin;
			if (memo.get(remaining + "-" + index + 1) == null) {
				memo.put(remaining + "-" + index + 1, makeChange(coins, remaining, index + 1, memo));
			}
			ways += memo.get(remaining + "-" + index + 1);
			amountWithCoin += coins[index];
		}
		return ways;
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for(int coins_i=0; coins_i < m; coins_i++){
            coins[coins_i] = in.nextInt();
        }
        System.out.println(makeChange(coins, n));
    }
}
