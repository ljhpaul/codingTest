package swea.trialSW.p5644;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};
    
    static int answer, M, A;
    static boolean[][][] isChargeable;
    static int[] move1, move2, charge;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	answer = 0;
        	st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	A = Integer.parseInt(st.nextToken());
        	move1 = new int[M];
        	move2 = new int[M];
        	isChargeable = new boolean[10][10][A];
        	charge = new int[A];
        	
        	// input
        	st = new StringTokenizer(br.readLine());
        	for(int move=0; move<M; move++) {
        		move1[move] = Integer.parseInt(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for(int move=0; move<M; move++) {
        		move2[move] = Integer.parseInt(st.nextToken());
        	}
        	
        	for(int bc=0; bc<A; bc++) {
        		st = new StringTokenizer(br.readLine());
        		int c = Integer.parseInt(st.nextToken()) - 1;
        		int r = Integer.parseInt(st.nextToken()) - 1;
        		int range = Integer.parseInt(st.nextToken());
        		int chargeAmount = Integer.parseInt(st.nextToken());
        		
        		charge[bc] = chargeAmount;
        		
        		// 충전 영역 표시
        		for(int i=0; i<10; i++) {
        			for(int j=0; j<10; j++) {
        				if((Math.abs(r-i) + Math.abs(c-j)) <= range) {
        					isChargeable[i][j][bc] = true;
        				}
        			}
        		}
        	}
        	
        	// move
        	move();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // move
    private static void move() {
    	// 처음 한 번 계산
    	int r1 = 0, c1 = 0, r2 = 9, c2 = 9;
    	charge(r1, c1, r2, c2);
    	
    	// 이동 경로 움직일 때마다 계산
    	for(int move=0; move<M; move++) {
    		r1 += dr[move1[move]];
    		c1 += dc[move1[move]];
    		r2 += dr[move2[move]];
    		c2 += dc[move2[move]];
    		charge(r1, c1, r2, c2);
    	}
    }
    
    // charge
    private static void charge(int r1, int c1, int r2, int c2) {
    	int maxCharge = 0;
    	for(int bc1=0; bc1<A; bc1++) {
        	for(int bc2=0; bc2<A; bc2++) {
        		if(isChargeable[r1][c1][bc1] && isChargeable[r2][c2][bc2]) {
        			// 둘 다 충전 가능
        			if(bc1 == bc2) {
        				maxCharge = Math.max(maxCharge, charge[bc1]);
        			} else {
        				maxCharge = Math.max(maxCharge, charge[bc1] + charge[bc2]);
        			}
        		} else if(isChargeable[r1][c1][bc1]) {
        			// 1만 충전 가능
        			maxCharge = Math.max(maxCharge, charge[bc1]);
        		} else if(isChargeable[r2][c2][bc2]) {
        			// 2만 충전 가능
        			maxCharge = Math.max(maxCharge, charge[bc2]);
        		}
        	}
    	}
    	answer += maxCharge;
    }
}