package swea.d5.p7793;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static String answer;
	static int R, C, suyeonR, suyeonC;
	static List<int[]> demons;
	static char[][] map;
	static int[][] demonTime, suyeonTime;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	// init
    		answer = "";
    		st = new StringTokenizer(br.readLine());
    		R = Integer.parseInt(st.nextToken());
    		C = Integer.parseInt(st.nextToken());
    		demons = new ArrayList<>();
    		map = new char[R][C];
    		demonTime = new int[R][C];
    		suyeonTime = new int[R][C];
    		for(int[] line : demonTime) Arrays.fill(line, Integer.MAX_VALUE);
    		for(int[] line : suyeonTime) Arrays.fill(line, Integer.MAX_VALUE);
    		
    		// input
    		for(int r=0; r<R; r++) {
    			String line = br.readLine();
    			for(int c=0; c<C; c++) {
    				char tmp = line.charAt(c);
    				map[r][c] = tmp;
    				if(tmp == 'S') {
    					suyeonR = r;
    					suyeonC = c;
    				} else if(tmp == '*') {
    					demons.add(new int[] {r, c});
    				}
    			}
    		}
    		
    		// solve
    		calcdemonTime();
    		moveKaktus();


            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
 // calcdemonTime - bfs
 	private static void calcdemonTime() {
 		// init
 		Queue<int[]> q = new ArrayDeque<>();
 		
 		// first
 		for(int[] demon : demons) {
 			q.offer(demon);
 			demonTime[demon[0]][demon[1]] = 0;
 		}
 		
 		// loop
 		while(!q.isEmpty()) {
 			// cur
 			int[] cur = q.poll();
 			int r = cur[0];
 			int c = cur[1];
 			
 			// next
 			for(int d=0; d<4; d++) {
 				int nr = r + dr[d];
 				int nc = c + dc[d];
 				
 				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
 				if(map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
 				
 				if(demonTime[r][c] + 1 < demonTime[nr][nc]) {
 					q.offer(new int[] {nr, nc});
 					demonTime[nr][nc] = demonTime[r][c] + 1;
 				}
 			}
 		}
 	}

 	// moveKaktus - bfs
 	private static void moveKaktus() { 
 		// init
 		Queue<int[]> q = new ArrayDeque<>();
 		
 		// first
 		q.offer(new int[] {suyeonR, suyeonC});
 		suyeonTime[suyeonR][suyeonC] = 0;
 		
 		// loop
 		while(!q.isEmpty()) {
 			// cur
 			int[] cur = q.poll();
 			int r = cur[0];
 			int c = cur[1];
 			
 			// check
 			if(map[r][c] == 'D') {
 				answer = suyeonTime[r][c] + "";
 				return;
 			}
 			
 			// next
 			for(int d=0; d<4; d++) {
 				int nr = r + dr[d];
 				int nc = c + dc[d];
 				
 				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
 				if(map[nr][nc] == 'X') continue;
 				if(suyeonTime[r][c] + 1 >= suyeonTime[nr][nc]) continue;
 				
 				if(suyeonTime[r][c] + 1 < demonTime[nr][nc]) {
 					q.offer(new int[] {nr, nc});
 					suyeonTime[nr][nc] = suyeonTime[r][c] + 1; 
 				}
 			}
 		}
 		
 		// failed
 		answer = "GAME OVER";
 	}
}