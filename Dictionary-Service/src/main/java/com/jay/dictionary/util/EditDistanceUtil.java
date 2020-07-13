package com.jay.dictionary.util;

import org.springframework.stereotype.Component;

@Component
public class EditDistanceUtil {

	public EditDistanceUtil() {
		super();
	}
	
	public int findDistanceBetween(String a, String b) {
		int n = a.length()+1;
		int m = b.length()+1;
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
