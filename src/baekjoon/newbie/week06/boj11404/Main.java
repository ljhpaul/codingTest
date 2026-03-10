package baekjoon.newbie.week06.boj11404;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int INF = 1_000_000_000;

	// main
	public static void main(String[] args) throws IOException {
		// init
    	int n = Integer.parseInt(br.readLine());
    	int m = Integer.parseInt(br.readLine());
    	int[][] dist = new int[n + 1][n + 1];
		for(int r = 1; r <= n; r++) {
			for(int c = 1; c <= n; c++) {
				if(r == c) continue;
				dist[r][c] = INF;
			}
		}
    	
    	// input
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dist[u][v] = Math.min(dist[u][v], w);
		}

        // solve
    	floydWarshall(dist);
    	String answer = setAnswer(dist);

		// output
		System.out.println(answer);
		br.close();
	}

	// Floyd-Warshall
	private static void floydWarshall(int[][] dist) {
		int N = dist.length;	// N = n + 1
		
		// for (k-i-j)
		for(int k = 1; k < N; k++) {
			for(int i = 1; i < N; i++) {
				for(int j = 1; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}

	// set answer
	private static String setAnswer(int[][] dist) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < dist.length; i++) {
			for(int j = 1; j < dist.length; j++) {
				sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}