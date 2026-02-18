package swea.trialSW.p5650;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxScore;
    static int[][] map;
    static Map<Integer, List<int[]>> worm;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int[][] changeDir = {
    		{},
    		{1, 3, 0, 2},	
    		{3, 0, 1, 2},	
    		{2, 0, 3, 1},	
    		{1, 2, 3, 0},	
    		{1, 0, 3, 2}	
    };
    
    // main
    public static void main(String[] args) throws IOException {
        // test case
//        int T = Integer.parseInt(br.readLine().trim());
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxScore = 0;
//        	int N = Integer.parseInt(br.readLine());
        	int N = sc.nextInt();
        	map = new int[N+2][N+2];
        	List<int[]> blank = new ArrayList<>();
        	worm = new HashMap<>();
        	for(int i=6; i<=10; i++) {
        		worm.put(i, new ArrayList<>());
        	}

            // input
        	Arrays.fill(map[0], 5);
        	for(int r=1; r<=N; r++) {
            	Arrays.fill(map[r], 5);	// 벽면 5로 설정
//        		st = new StringTokenizer(br.readLine().trim());
        		for(int c=1; c<=N; c++) {
//        			int tmp = Integer.parseInt(st.nextToken());
        			int tmp = sc.nextInt();
        			map[r][c] = tmp;
        			if(tmp == 0) {
        				blank.add(new int[] {r, c});	// 빈칸 관리
        			} else if(tmp >= 6) {
        				worm.get(tmp).add(new int[] {r, c});	// 웜홀 관리
        			}
        		}
        	}
        	Arrays.fill(map[N+1], 5);

            // solve
        	for(int[] pos : blank) {	// 빈칸에서만 시작
        		for(int dir=0; dir<4; dir++) {	// 시작 방향 정하기
        			pinball(pos[0], pos[1], dir);
        		}
        	}

            // answer
            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }
    
    // pinball
    private static void pinball(int r, int c, int dir) {
    	int score = 0;
    	int startR = r;
    	int startC = c;
    	
    	while(true) {
    		r += dr[dir];
            c += dc[dir];
    		
    		// 블랙홀 진입 || 시작점으로 다시 돌아옴
        	if(map[r][c] == -1 || (r == startR && c == startC)) {
        		maxScore = Math.max(score, maxScore);
        		break;
        	}
        	
        	// 웜홀 확인
        	if(map[r][c] >= 6) {
        		// 웜홀 쌍 찾기 + 순간이동
        		for(int[] pos : worm.get(map[r][c])) {
        			if(r != pos[0] || c != pos[1]) {
        				r = pos[0];
        				c = pos[1];
        				break;
        			}
        		}
        		continue;
        	}
    		
    		// 벽면 또는 블록 확인
            if(1 <= map[r][c] && map[r][c] <= 5) {
            	dir = changeDir[map[r][c]][dir];
            	score++;
            }
    	}
    }
}
