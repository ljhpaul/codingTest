package swea.d4.p5643;

import java.io.*;
import java.util.*;

class Solution_FloydWarshall {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N, M;
    static boolean[][] relation;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            relation = new boolean[N + 1][N + 1];

            // input
            for(int i = 0; i < M; i++) {
            	st = new StringTokenizer(br.readLine());
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	relation[a][b] = true;
            }
            
            // solve
            floydWarshall();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // Floyd-Warshall
	private static void floydWarshall() {
		// loop (k-i-j)
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				if(!relation[i][k]) continue;
				for(int j = 1; j <= N; j++) {
					if(!relation[k][j]) continue;
					relation[i][j] = true;
				}
			}
		}
		
		// count
		for(int i = 1; i <= N; i++) {
			boolean flag = true;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				
				if(!relation[i][j] && !relation[j][i]) {
					flag = false;
					break;
				}
			}
			if(flag) answer++;
		}
	}
}