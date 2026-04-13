package battle;
import java.util.*;

public class Answer4to7_2 {
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
    
    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        String gameData = bridge.init(NICKNAME);

        while (gameData.length() > 0) {
            parseData(gameData);
            String output = "S";
            
            H = mapData.length;
            W = mapData[0].length;

            int[] me     = findUnit("A");
            int[] target = findUnit("X");

            // ─────────────────────────────────────
            //  TODO 4 적용 영역
            //  보급시설 인접 + 암호문 존재 시 해독
            //  조건: codes.length > 0
            //        AND 메가탄이 부족할 때만 해독
            //  output = "G " + decodeCaesar(codes[0])
            // ─────────────────────────────────────
            int normalAmmo = Integer.parseInt(allies.get("A")[2]);
            int megaAmmo = Integer.parseInt(allies.get("A")[3]);

            // TODO 4: 암호 해독 조건 작성
            // if ( ??? ) {
            //     output = "G " + decodeCaesar(codes[0]);
            // } else { ... }

            String attackCmd = canAttack(me, target);
            if(codes.length > 0 && megaAmmo < 1) {
            	output = "G " + decodeCaesar(codes[0]);
            }
            
            else if (!attackCmd.isEmpty()) {
                output = attackCmd;
            }
            
            else if (megaAmmo < 1 && !isFAdj(me)) {
            	output = bfsToSupply(me);
            }
            
            else {
                output = bfs(me, target);
            }

            System.out.printf("[SEND] %s%n", output);
            gameData = bridge.submit(output);
        }
        bridge.close();
    }

    static int[] findUnit(String symbol) {
        for (int r = 0; r < mapData.length; r++)
            for (int c = 0; c < mapData[r].length; c++)
                if (mapData[r][c].equals(symbol)) return new int[]{r, c};
        return new int[]{-1, -1};
    }

    static String canAttack(int[] me, int[] target) {
        direction:
        for (int dir = 0; dir < 4; dir++) {
            for (int range = 1; range <= 3; range++) {
                int nr = me[0] + dr[dir] * range;
                int nc = me[1] + dc[dir] * range;
                if (nr < 0 || nr >= mapData.length || nc < 0 || nc >= mapData[0].length) continue direction;
                if (mapData[nr][nc].equals("R")) continue direction;
                if (mapData[nr][nc].equals("F")) continue direction;
                if (mapData[nr][nc].equals("T"))  continue;
                if (mapData[nr][nc].equals("E1")) continue;
                if (mapData[nr][nc].equals("E2")) continue;
                if (mapData[nr][nc].equals("E3")) continue;
                if (mapData[nr][nc].equals("X"))  return fireCmd[dir];
            }
        }
        return "";
    }

    static String bfs(int[] start, int[] goal) {
        String cmd = "S";
        int R = mapData.length, C = mapData[0].length;
        int minDist = INF;
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[R][C];
        String[][] firstCmd = new String[R][C];
        for (int[] line : dist) Arrays.fill(line, INF);

        q.add(new int[]{start[0], start[1]});
        dist[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir], nc = c + dc[dir];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (dist[nr][nc] != INF) continue;
                if (!mapData[nr][nc].equals("G") && !mapData[nr][nc].equals("A")) continue;

                q.add(new int[]{nr, nc});
                dist[nr][nc] = dist[r][c] + 1;
                firstCmd[nr][nc] = (dist[r][c] == 0) ? moveCmd[dir] : firstCmd[r][c];

                if (dist[nr][nc] < minDist && !canAttack(new int[]{nr, nc}, goal).equals("")) {
                    cmd = firstCmd[nr][nc];
                    minDist = dist[nr][nc];
                }
            }
        }
        return cmd;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 4 : decodeCaesar(String cipher)
    //  shift 1~25 전수 탐색
    //  각 shift로 복호화 후 englishScore() 계산
    //  가장 높은 점수의 평문 반환
    //
    //  복호화 공식:
    //  plain = (ch - 'A' - shift + 26) % 26 + 'A'
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static String decodeCaesar(String cipher) {
        // TODO
    	String best = "";
    	int bestScore = -1;
    	for(int shift = 1; shift <= 25; shift++) {
    		// cipher shift 복호화 -> plain 문자열 생성
    		StringBuilder sb = new StringBuilder();
    		for(char ch : cipher.toCharArray()) {
    			sb.append((char) ((ch - 'A' - shift + 26) % 26 + 'A'));
    		}
    		String plain = sb.toString();
    		
    		// plain 영어 점수 계산
    		int score = englishScore(plain);
    		
    		// 최고 점수 갱신
    		if(score > bestScore) {
    			best = plain;
    			bestScore = score;
    		}
    	}
        return best;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 5 : englishScore(String s)
    //  문자열의 각 문자가 FREQ에 포함되면
    //  (FREQ.length() - FREQ.indexOf(c)) 점수 누적
    //  누적 점수 반환
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static int englishScore(String s) {
        // TODO
    	int score = 0;
    	for(char ch : s.toCharArray()) {
    		int idx = FREQ.indexOf(ch);
    		if(idx >= 0) {
    			score += FREQ.length() - idx;
    		}
    	}
        return score;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 6 : findSupply(int[] me)
    //  보급시설(F)까지 이동하는 첫 번째 커맨드 반환
    //  bfs()와 구조 동일하나 목표가 F 인접 칸
    //  힌트: canAttack() 대신 isFAdj() 로 체크
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static String bfsToSupply(int[] start) {
        // TODO
        return "S";
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 7 : isFAdj(int[] pos)
    //  pos의 상하좌우 중 F(보급시설)가 있으면 true
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static boolean isFAdj(int[] pos) {
        // TODO
        return false;
    }

    static void parseData(String gameData) {
        String[] rows = gameData.split("\n");
        int idx = 0;
        String[] h = rows[idx++].split(" ");
        int H = Integer.parseInt(h[0]), W = Integer.parseInt(h[1]);
        int nA = Integer.parseInt(h[2]), nE = Integer.parseInt(h[3]), nC = Integer.parseInt(h[4]);
        mapData = new String[H][W];
        for (int i = 0; i < H; i++) mapData[i] = rows[idx++].split(" ");
        allies.clear();
        for (int i = 0; i < nA; i++) {
            String[] d = rows[idx++].split(" ");
            allies.put(d[0], Arrays.copyOfRange(d, 1, d.length));
        }
        enemies.clear();
        for (int i = 0; i < nE; i++) {
            String[] d = rows[idx++].split(" ");
            enemies.put(d[0], Arrays.copyOfRange(d, 1, d.length));
        }
        codes = new String[nC];
        for (int i = 0; i < nC; i++) codes[i] = rows[idx++];
    }
}