package com.jay.dictionary.util;

import org.springframework.stereotype.Component;

@Component
public class EditDistanceUtil {
	
	/* *
	 * EditDistance is an algorithm used to find the number of insert and delete 
	 * operations required to convert one string into another string. 
	 */
	public EditDistanceUtil() {
		super();
	}
	
	/*
	 * This method is the implementation of the actual algorithm.
	 * It returns the number of operations required to convert string a into string b or vice versa
	 */
	public int findDistanceBetween(String a, String b) {
		int n = a.length()+1;
		int m = b.length()+1;
		// dp means Dynamic Programming
		int dp[][] = new int[n][m];
		
		for(int i=0; i<n; i++) dp[i][0] = i;
		for(int j=0; j<m; j++) dp[0][j] = j;
		
		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
				}
			}
		}
		
		return dp[n-1][m-1];
	}
	
}
