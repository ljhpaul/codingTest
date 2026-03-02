package swea.d4.p4206;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static String answer;
    static int N, M, sr, sc;
    static int[][] map;
    static List<int[]> virus;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	answer = "";
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	map = new int[N][M];
        	virus = new ArrayList<>();
        	
        	// input
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<M; c++) {
        			int tmp = Integer.parseInt(st.nextToken());
    				map[r][c] = tmp;
        			if(map[r][c] == 2) {
        				virus.add(new int[] {r, c, 2});	// 바이러스 위치 리스트
            			map[r][c] = tmp;
        			} else if(map[r][c] == 3) {
        				sr = r;	// 삼성이의 현재 위치 확인
        				sc = c;
        			}
        		}
        	}

            // solve
        	bfs();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }
    
    // BFS
    private static void bfs() {
    	// init
    	Queue<int[]> q = new ArrayDeque<>();
    	int[][] sVisited = new int[N][M];
    	for(int[] line : sVisited) Arrays.fill(line, Integer.MAX_VALUE); 
    	boolean[][] vVisited = new boolean[N][M];
    	int samsungCnt = 0;
    	
    	// 삼성이 위치
    	q.offer(new int[] {sr, sc, 3});
    	sVisited[sr][sc] = 0;
    	samsungCnt++;
    	
    	// 바이러스 세팅
    	for(int[] v : virus) {
    		q.offer(v);
    		vVisited[v[0]][v[1]] = true;
    	}
     	
    	// loop
    	while(!q.isEmpty()) {
    		int[] curr = q.poll();
    		int r = curr[0];
    		int c = curr[1];
    		boolean isSamsung = (curr[2] == 3);
    		
			// 현재 이미 감염된 위치인 경우
			if(isSamsung && map[r][c] == 2) continue;
    		
    		for(int d=0; d<4; d++) {
    			int nr = r + dr[d];
    			int nc = c + dc[d];
    			
    			// [DEBUG]
//    			for(int[] line : map) {
//    				System.out.println(Arrays.toString(line));
//    			}
//    			System.out.println();
    			
    			// 삼성이일 경우
    			if(isSamsung) {
    				// 탈출 성공
    				if(!isValidArea(nr, nc)) {
    					answer = (sVisited[r][c] + 1) + "";
    					return;
    				}
    				// 다음 위치 판단
    				if(map[nr][nc] > 0) continue;
    				if(sVisited[nr][nc] != Integer.MAX_VALUE) continue;
    				// 삼성이 확산
    				q.offer(new int[] {nr, nc, 3});
    				sVisited[nr][nc] = sVisited[r][c] + 1;
    				map[nr][nc] = 3;
    				samsungCnt++;
    			}
    			// 바이러스일 경우
    			else {
    				// 좌표 유효성 검사
    				if(!isValidArea(nr, nc)) continue;
    				// 방문 여부 확인
    				if(vVisited[nr][nc]) continue;
    				// 벽 체크
    				if(map[nr][nc] == 1) continue;
    				// 삼성이 체크
    				if(map[nr][nc] == 3) samsungCnt--;
    				// 바이러스 확산
    				q.offer(new int[] {nr, nc, 2});
    				vVisited[nr][nc] = true;
    				map[nr][nc] = 2;
    			}
    		}
    	}
    	
    	// 탈출 실패
    	answer = samsungCnt > 0 ? "CANNOT ESCAPE" : "ZOMBIE";
		return;
    }
    
    // check valid area
    private static boolean isValidArea(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < M;
    }
}
