package swea.trialSW.p2105;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxCnt, N, startR, startC;
    static int[][] dessert;
    static Set<Integer> ate;
    static int[] movingCnt;
    
    static int[] dr = {-1, 1, 1, -1}; // 0 1 2 3 : NE SE SW NW
    static int[] dc = {1, 1, -1, -1};
    static int[][] moving = {
    		{0, 1, 2, 3},
    		{0, 3, 1, 2},
    		{1, 0, 3, 2},
    		{1, 2, 3, 0},
    		{2, 1, 0, 3},
    		{2, 3, 0, 1},
    		{3, 0, 1, 2},
    		{3, 2, 1, 0}
    };

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxCnt = -1;
        	N = Integer.parseInt(br.readLine());
        	dessert = new int[N][N];
        	
        	// input
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			dessert[r][c] = Integer.parseInt(st.nextToken());
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
    	// 시작 위치 (r, c)
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			// 8가지 경로로 시작
    			for(int mode=0; mode<8; mode++) {
    				startR = r;
    				startC = c;
    				ate = new HashSet<>();
    				movingCnt = new int[4];
    				dfs(r, c, 0, mode, 0, 0);
    			}
    		}
    	}
	}

	// dfs
    private static void dfs(int r, int c, int cnt, int mode, int moveIdx, int walk) {
		// base : 원래 위치 복귀할 경우
    	if(r == startR && c == startC && moveIdx == 3 && walk > 0) {
    		maxCnt = Math.max(cnt, maxCnt);
    		return;
    	}
    	
    	// recursion
    	int nr, nc, dir;
    	// 1. 방향 유지
    	if(moveIdx <= 2 || (moveIdx >= 3 && walk < movingCnt[moveIdx - 2])) {
    		dir = moving[mode][moveIdx];
        	nr = r + dr[dir];
        	nc = c + dc[dir];
        	if(nr >= 0 && nr < N && nc >= 0 && nc < N) {	// 좌표 유효성 검사
        		int curr = dessert[nr][nc];
        		if(!ate.contains(curr)) {	// 다른 숫자의 디저트 카페일 경우
        			movingCnt[moveIdx]++;
        			ate.add(curr);
        			dfs(nr, nc, cnt + 1, mode, moveIdx, walk + 1);
        			movingCnt[moveIdx]--;
        			ate.remove(curr);
        		}
        	}
    	}
    	
    	// 2. 방향 꺾기
    	if(++moveIdx == 4) return;
    	dir = moving[mode][moveIdx];
    	nr = r + dr[dir];
    	nc = c + dc[dir];
    	if(nr >= 0 && nr < N && nc >= 0 && nc < N) {	// 좌표 유효성 검사
    		int curr = dessert[nr][nc];
    		if(!ate.contains(curr)) {	// 다른 숫자의 디저트 카페일 경우
    			movingCnt[moveIdx]++;
    			ate.add(curr);
    			dfs(nr, nc, cnt + 1, mode, moveIdx, 1);
    			movingCnt[moveIdx]--;
    			ate.remove(curr);
    		}
    	}
	}
}