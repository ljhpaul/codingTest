package swea.trialSW.p2382;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int N, M, K;
    static List<Micro> micros;
    
    static int[] dr = {0, -1, 1, 0, 0}; // 상하좌우(1234)
    static int[] dc = {0, 0, 0, -1, 1};
    static int[] reverseDir = {0, 2, 1, 4, 3};
    
    // Micro
    static class Micro {
    	int r, c, cnt, dir;

		public Micro(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	micros = new ArrayList<>();
        	
        	// input
        	for(int i=0; i<K; i++) {
        		st = new StringTokenizer(br.readLine());
            	int r = Integer.parseInt(st.nextToken());
            	int c = Integer.parseInt(st.nextToken());
            	int cnt = Integer.parseInt(st.nextToken());
            	int dir = Integer.parseInt(st.nextToken());
            	micros.add(new Micro(r, c, cnt, dir));
        	}
            
            // solve
        	simulate();
        	int answer = getAnswer();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

	// simulate
	private static void simulate() {
		Map<Long, ArrayList<Micro>> moved;
		// M시간 동안 격리 진행
		for(int time=0; time<M; time++) {
			// init
			moved = new HashMap<>();
			
			// 미생물 순회하여 이동
			for(Micro m : micros) {
            	int cnt = m.cnt;
            	int dir = m.dir;
            	
            	int nr = m.r + dr[dir];
            	int nc = m.c + dc[dir];

            	// 약품 여부 확인
            	if(nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
            		cnt /= 2;
            		if(cnt == 0) continue;
            		dir = reverseDir[dir];
            	}
            	
            	// 이동 후 좌표 추가
            	long key = getKey(nr, nc);
            	if(!moved.containsKey(key)) {
            		moved.put(key, new ArrayList<>());
            	}
            	moved.get(key).add(new Micro(nr, nc, cnt, dir));
			}
			
			// 이동 좌표 돌며 새 군집으로 갱신
			List<Micro> next = new ArrayList<>();
			for(ArrayList<Micro> list : moved.values()) {
				int r = list.get(0).r;
				int c = list.get(0).c;
				int cntSum = 0;
				int maxCnt = 0;
				int dir = 0;
				for(Micro m : list) {
					if(maxCnt < m.cnt) {
						maxCnt = m.cnt;
						dir = m.dir;
					}
					cntSum += m.cnt;
				}
				next.add(new Micro(r, c, cntSum, dir));
			}
			
			// 미생물 리스트 최신화(덮어쓰기)
			micros = next;
		}
	}

	// get answer
    private static int getAnswer() {
    	int result = 0;
		for(Micro m : micros) {
			result += m.cnt;
		}
    	return result;
	}
    
	// get long key
	private static long getKey(int r, int c) {
		return (((long) r) << 32) | (c & 0xffffffffL);
	}
}