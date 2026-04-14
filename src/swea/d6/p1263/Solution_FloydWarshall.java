package swea.d6.p1263;

import java.io.*;
import java.util.*;

class Solution_FloydWarshall {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N;
    static int[][] dist;
    
    static final int INF = 1_000_000_000;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            dist = new int[N][N];

            // input
            for(int r = 0; r < N; r++) {
            	for(int c = 0; c < N; c++) {
            		int tmp = Integer.parseInt(st.nextToken());
            		if(r == c) dist[r][c] = 0;
            		else if(tmp > 0) dist[r][c] = tmp;
            		else dist[r][c] = INF;
            	}
            }
            
            // solve
            floydWarshall();
            answer = getMinCC();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

	// Floyd-warshall
	private static void floydWarshall() {
		for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
            	for(int j = 0; j < N; j++) {
            		dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            	}
            }
		}
	}

	// get min connected component
    private static int getMinCC() {
    	int minCC = INF;
		for(int r = 0; r < N; r++) {
			int sum = 0;
			for(int c = 0; c < N; c++) {
				sum += dist[r][c];
			}
			minCC = Math.min(minCC, sum);
		}
		return minCC;
	}
}