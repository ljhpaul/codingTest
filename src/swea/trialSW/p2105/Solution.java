package swea.trialSW.p2105;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxCnt, N, maxTaste;
    static int[][] dessert;
    static boolean[] isTasted;
    
    static int[] dr = {-1, 1, 1, -1};
    static int[] dc = {1, 1, -1, -1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxCnt = -1;
        	N = Integer.parseInt(br.readLine());
        	maxTaste = 0;
        	dessert = new int[N][N];
        	
        	// input
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			dessert[r][c] = Integer.parseInt(st.nextToken());
        			maxTaste = Math.max(dessert[r][c], maxTaste);
        		}
        	}
            
            // solve
        	solve();

            // answer
            sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // solve
	private static void solve() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				for(int a=1; a<N; a++) {
					for(int b=1; b<N; b++) {
						for(int d=0; d<4; d++) {
							move(r, c, a, b, d);
						}
					}
				}
			}
		}
	}
    
    // move
	private static void move(int r, int c, int a, int b, int d) {
		// init
		isTasted = new boolean[maxTaste + 1];
		int cnt = 1;
		isTasted[dessert[r][c]] = true;
		int[] len = {a, b, a, b};
		
		// first dir
		for(int i=0; i<a; i++) {
			r += dr[d];
			c += dc[d];
			
			if(r < 0 || r >= N || c < 0 || c >= N) return;
			if(isTasted[dessert[r][c]]) return;
			isTasted[dessert[r][c]] = true;
			
			cnt++;
		}
		
		// second dir
		d = (d + 1) % 4;
		for(int i=0; i<b; i++) {
			r += dr[d];
			c += dc[d];
			
			if(r < 0 || r >= N || c < 0 || c >= N) return;
			if(isTasted[dessert[r][c]]) return;
			isTasted[dessert[r][c]] = true;
			
			cnt++;
		}
		
		// third dir
		d = (d + 1) % 4;
		for(int i=0; i<a; i++) {
			r += dr[d];
			c += dc[d];
			
			if(r < 0 || r >= N || c < 0 || c >= N) return;
			if(isTasted[dessert[r][c]]) return;
			isTasted[dessert[r][c]] = true;
			
			cnt++;
		}
		
		// fourth dir
		d = (d + 1) % 4;
		for(int i=0; i<b-1; i++) {
			r += dr[d];
			c += dc[d];
			
			if(r < 0 || r >= N || c < 0 || c >= N) return;
			if(isTasted[dessert[r][c]]) return;
			isTasted[dessert[r][c]] = true;
			
			cnt++;
		}
		
		// renew maxCnt
		maxCnt = Math.max(maxCnt, cnt);
	}
}