package battle;
import java.util.*;

public class Answer8 {
    private static String NICKNAME = "서울10_이정헌";
    private static String[][] mapData;
    private static Map<String, String[]> allies = new HashMap<>();
    private static Map<String, String[]> enemies = new HashMap<>();
    private static String[] codes;

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

            int[] me     = findUnit("A");
            int[] target = findUnit("X");

            int normalAmmo = Integer.parseInt(allies.get("A")[2]);
            int megaAmmo   = Integer.parseInt(allies.get("A")[3]);

            String attackCmd = canAttack(me, target);

            if (codes.length > 0 && megaAmmo < 1) {
                // 1순위: 보급시설 인접 + 메가탄 부족 → 암호 해독
                output = "G " + decodeCaesar(codes[0]);

            } else if (!attackCmd.isEmpty()) {
                // 2순위: 공격 가능 → 발사
                output = attackCmd;

            } else if (megaAmmo < 1 && !isFAdj(me)) {
                // 3순위: 메가탄 부족 + 보급소 미인접 → 보급소로 이동
                output = bfsToSupply(me);

            } else {
                // 4순위: 포탑으로 이동 (나무 파괴 포함 최단 경로)
                output = dijkstra(me, target, normalAmmo, megaAmmo);
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

    static String dijkstra(int[] start, int[] goal, int normalAmmo, int megaAmmo) {
        int R = mapData.length, C = mapData[0].length;
        int NA = normalAmmo, MA = megaAmmo;

        // [비용, 행, 열, 일반탄, 메가탄]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
        int[][][][] dist = new int[R][C][NA + 1][MA + 1];
        String[][][][] firstCmd = new String[R][C][NA + 1][MA + 1];
        for (int[][][] a : dist)
            for (int[][] b : a)
                for (int[] c : b) Arrays.fill(c, INF);

        dist[start[0]][start[1]][NA][MA] = 0;
        pq.add(new int[]{0, start[0], start[1], NA, MA});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], r = cur[1], c = cur[2], na = cur[3], ma = cur[4];

            // 이미 더 좋은 경로로 방문된 상태면 스킵
            if (dist[r][c][na][ma] < cost) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir], nc = c + dc[dir];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                String tile = mapData[nr][nc];
                int newCost, newNa, newMa;

                if (tile.equals("G") || tile.equals("A")) {
                    // 일반 이동: 1턴
                    newCost = cost + 1;
                    newNa = na;
                    newMa = ma;

                } else if (tile.equals("T")) {
                    // 나무 파괴: 2턴 + 탄약 1개
                    // 일반탄 우선, 없으면 메가탄
                    if (na > 0) {
                        newCost = cost + 2;
                        newNa = na - 1;
                        newMa = ma;
                    } else if (ma > 0) {
                        newCost = cost + 2;
                        newNa = na;
                        newMa = ma - 1;
                    } else {
                        continue; // 탄약 없음 → 진입 불가
                    }

                } else {
                    continue; // W, R, F → 이동 불가
                }

                if (dist[nr][nc][newNa][newMa] > newCost) {
                    dist[nr][nc][newNa][newMa] = newCost;
                    firstCmd[nr][nc][newNa][newMa] =
                        (dist[r][c][na][ma] == 0)
                            ? moveCmd[dir]
                            : firstCmd[r][c][na][ma];
                    pq.add(new int[]{newCost, nr, nc, newNa, newMa});
                }
            }
        }

        // 공격 가능한 칸 중 최솟값 탐색
        String cmd = "S";
        int minDist = INF;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
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

    static String decodeCaesar(String cipher) {
        String best = "";
        int bestScore = -1;
        for (int shift = 1; shift <= 25; shift++) {
            StringBuilder sb = new StringBuilder();
            for (char ch : cipher.toCharArray())
                sb.append((char)('A' + (ch - 'A' - shift + 26) % 26));
            String plain = sb.toString();
            int score = englishScore(plain);
            if (score > bestScore) { bestScore = score; best = plain; }
        }
        return best;
    }

    static int englishScore(String s) {
        int score = 0;
        for (char ch : s.toCharArray()) {
            int idx = FREQ.indexOf(ch);
            if (idx >= 0) score += (FREQ.length() - idx);
        }
        return score;
    }

    static String bfsToSupply(int[] start) {
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

                if (dist[nr][nc] < minDist && isFAdj(new int[]{nr, nc})) {
                    cmd = firstCmd[nr][nc];
                    minDist = dist[nr][nc];
                }
            }
        }
        return cmd;
    }

    static boolean isFAdj(int[] pos) {
        for (int dir = 0; dir < 4; dir++) {
            int nr = pos[0] + dr[dir], nc = pos[1] + dc[dir];
            if (nr < 0 || nr >= mapData.length || nc < 0 || nc >= mapData[0].length) continue;
            if (mapData[nr][nc].equals("F")) return true;
        }
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