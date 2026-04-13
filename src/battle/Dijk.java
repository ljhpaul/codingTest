package battle;

import java.util.*;

public class Dijk {
    private static String NICKNAME = "서울10_이정헌";
    private static String[][] mapData;
    private static Map<String, String[]> allies = new HashMap<>();
    private static Map<String, String[]> enemies = new HashMap<>();
    private static String[] codes;

    static int H, W;
    static final int INF = 1_000_000_000;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static final String[] moveCmd = {"U A", "D A", "L A", "R A"};
    static final String[] fireCmd = {"U F", "D F", "L F", "R F"};
    static final String FREQ = "ETAOINSHRDLU";
	
	static String dijkstra(int[] start, int[] goal, int normalAmmo, int megaAmmo) {
	    int NA = normalAmmo, MA = megaAmmo;
	
	    // ① PriorityQueue — 비용 작은 것 먼저
	    PriorityQueue<int[]> pq = new PriorityQueue<>(
	        Comparator.comparingInt(a -> a[0])
	    );
	
	    // ② dist 4차원 — (행, 열, 일반탄, 메가탄)
	    int[][][][] dist = new int[H][W][NA + 1][MA + 1];
	    String[][][][] firstCmd = new String[H][W][NA + 1][MA + 1];
	    for (int[][][] a : dist)
	        for (int[][] b : a)
	            for (int[] c : b) Arrays.fill(c, INF);
	
	    // ③ 시작점
	    dist[start[0]][start[1]][NA][MA] = 0;
	    pq.add(new int[]{0, start[0], start[1], NA, MA});
	
	    while (!pq.isEmpty()) {
	        int[] cur = pq.poll();
	        int cost = cur[0], r = cur[1], c = cur[2], na = cur[3], ma = cur[4];
	
	        // ④ 이미 더 좋은 경로로 방문됐으면 스킵
	        if (dist[r][c][na][ma] < cost) continue;
	
	        for (int dir = 0; dir < 4; dir++) {
	            int nr = r + dr[dir], nc = c + dc[dir];
	            if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
	
	            String tile = mapData[nr][nc];
	            int newCost, newNa, newMa;
	
	            // ⑤ 타일별 비용 계산
	            if (tile.equals("G") || tile.equals("A")) {
	                newCost = cost + 1;
	                newNa = na;
	                newMa = ma;
	
	            } else if (tile.equals("T")) {
	                if      (na > 0) { newCost = cost + 2; newNa = na - 1; newMa = ma; }
	                else if (ma > 0) { newCost = cost + 2; newNa = na;     newMa = ma - 1; }
	                else continue;
	
	            } else continue; // W, R, F 이동 불가
	
	            // ⑥ 더 좋은 비용이면 갱신
	            if (dist[nr][nc][newNa][newMa] > newCost) {
	                dist[nr][nc][newNa][newMa] = newCost;
	                firstCmd[nr][nc][newNa][newMa] =
	                    (dist[r][c][na][ma] == 0) ? moveCmd[dir] : firstCmd[r][c][na][ma];
	                pq.add(new int[]{newCost, nr, nc, newNa, newMa});
	            }
	        }
	    }
	
	    // ⑦ 루프 완료 후 공격 가능 칸 중 최솟값 탐색
	    String cmd = "S";
	    int minDist = INF;
	    for (int r = 0; r < H; r++) {
	        for (int c = 0; c < W; c++) {
	            if (canAttack(new int[]{r, c}, goal).isEmpty()) continue;
	            for (int na = 0; na <= NA; na++) {
	                for (int ma = 0; ma <= MA; ma++) {
	                    if (firstCmd[r][c][na][ma] == null) continue;
	                    if (dist[r][c][na][ma] < minDist) {
	                        minDist = dist[r][c][na][ma];
	                        cmd = firstCmd[r][c][na][ma];
	                    }
	                }
	            }
	        }
	    }
	    return cmd;
	}

	private static String canAttack(int[] is, int[] goal) {
		// TODO Auto-generated method stub
		return null;
	}
}

