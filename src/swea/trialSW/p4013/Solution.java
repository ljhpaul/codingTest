package swea.trialSW.p4013;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int[] mag;
    static boolean[] isSame, visited;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int answer = 0;
            int K = Integer.parseInt(br.readLine());
            mag = new int[4];

            // input
            for(int i=0; i<4; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j=0; j<8; j++) {
            		mag[i] = (mag[i]<<1) | Integer.parseInt(st.nextToken());
            	}
            }
            
            // solve
            for(int i=0; i<K; i++) {
            	st = new StringTokenizer(br.readLine());
            	int idx = Integer.parseInt(st.nextToken()) - 1;
            	int dir = Integer.parseInt(st.nextToken());
            	
                isSame = new boolean[3];
            	checkSame(); // 자성 같은지 확인
            	visited = new boolean[4];
            	turn(idx, dir);	// 자석 회전
            }
            if(((mag[0]>>7) & 1) == 1) answer += 1;
            if(((mag[1]>>7) & 1) == 1) answer += 2;
            if(((mag[2]>>7) & 1) == 1) answer += 4;
            if(((mag[3]>>7) & 1) == 1) answer += 8;

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // check same
    private static void checkSame() {
    	for(int i=0; i<3; i++) {
    		if(((mag[i]>>5) & 1) == ((mag[i+1]>>1) & 1)) {
    			isSame[i] = true;
    		}
    	}
    }
    
    // turn
    private static void turn(int idx, int dir) {
    	visited[idx] = true;
    	
    	// 자석 회전
    	if(dir == 1) {
    		mag[idx] = (mag[idx]>>1) | ((mag[idx] & 1)<<7);	// 시계
    	} else {
    		mag[idx] = ((mag[idx]<<1) & 0xFF) | ((mag[idx]>>7) & 1);	// 반시계
    	}
    	
    	// 좌측
    	if(idx > 0 && !isSame[idx - 1] && !visited[idx - 1]) turn(idx - 1, -dir);
    	// 우측
    	if(idx < 3 && !isSame[idx] && !visited[idx + 1]) turn(idx + 1, -dir);
    }
}