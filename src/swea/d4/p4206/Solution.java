package swea.d4.p4206;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static String answer;
    static int N, M, sr, sc;
    static int[][] map, virusTime, samsungTime;
    static List<int[]> virus;
    
    static final int INF = Integer.MAX_VALUE;
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
        				virus.add(new int[] {r, c});	// 바이러스 위치 리스트
            			map[r][c] = tmp;
        			} else if(map[r][c] == 3) {
        				sr = r;	// 삼성이의 현재 위치 확인
        				sc = c;
        			} else {
        				map[r][c] = tmp;
        			}
        		}
        	}

            // solve
        	calcVirusTime();
        	escapeSamsung();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // 멀티 소스 BFS : 바이러스 확산 시각 계산 
	private static void calcVirusTime() {
		// init
    	virusTime = new int[N][M];
    	for(int[] line : virusTime) Arrays.fill(line, INF);
    	Queue<int[]> q = new ArrayDeque<>();
    	
    	// 초기 바이러스 유출
    	for(int[] v : virus) {
    		q.offer(v);
    		virusTime[v[0]][v[1]] = 0;
    	}
		
		// loop
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// 4방향 탐색
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 좌표 유효성 검사
				if(!isValidArea(nr, nc)) continue;
				// 벽 여부 확인
				if(map[nr][nc] == 1) continue;
				// 방문 여부 확인
				if(virusTime[nr][nc] != INF) continue;
				// 바이러스 확산 및 시간 계산
				q.offer(new int[] {nr, nc});
				virusTime[nr][nc] = virusTime[r][c] + 1;
			}
		}
	}
    
	// BFS : 삼성이의 연구소 탈출
    private static void escapeSamsung() {
		// init
    	samsungTime = new int[N][M];
    	for(int[] line : samsungTime) Arrays.fill(line, INF);
    	Queue<int[]> q = new ArrayDeque<>();
    	boolean isSafe = true;
    	
    	// 삼성이 초기 위치 설정
    	q.offer(new int[] {sr, sc});
    	samsungTime[sr][sc] = 0;
		
		// loop
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// 4방향 탐색
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 탈출 여부 확인
				if(!isValidArea(nr, nc)) {
					answer = (samsungTime[r][c] + 1) + "";
					return;
				}
				// 벽 여부 확인
				if(map[nr][nc] == 1) continue;
				// 방문 여부 확인
				if(samsungTime[nr][nc] != INF) continue;
				// 바이러스 확산보다 느린지 확인
				if(virusTime[nr][nc] <= samsungTime[r][c] + 1) {
					isSafe = false;
					continue;
				}
				// 삼성이 이동
				q.offer(new int[] {nr, nc});
				samsungTime[nr][nc] = samsungTime[r][c] + 1;
			}
		}
		
		answer = isSafe ? "CANNOT ESCAPE" : "ZOMBIE";
	}


	// check valid area
    private static boolean isValidArea(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < M;
    }
}
