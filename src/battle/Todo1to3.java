package battle;

import java.util.*;

public class Todo1to3 {
    private static String NICKNAME = "서울10_이정헌";
    private static String[][] mapData;
    private static Map<String, String[]> allies = new HashMap<>();
    private static Map<String, String[]> enemies = new HashMap<>();
    private static String[] codes;

    // ─────────────────────────────────────────────
    //  방향 배열 (상 하 좌 우)
    // ─────────────────────────────────────────────
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static String[] moveCmd = {"U A", "D A", "L A", "R A"};
    static String[] fireCmd = {"U F", "D F", "L F", "R F"};

    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        String gameData = bridge.init(NICKNAME);

        while (gameData.length() > 0) {
            parseData(gameData);

            String output = "S"; // 기본값: 대기

            // ─────────────────────────────────────
            //  [문제 영역] 여기에 알고리즘을 작성합니다
            // ─────────────────────────────────────

            int[] me     = findUnit("A"); // TODO: 구현 필요
            int[] target = findUnit("X"); // TODO: 구현 필요

            System.out.printf("me = %d,%d  /  target = %d,%d%n",
                              me[0], me[1], target[0], target[1]);

            String attackCmd = canAttack(me, target); // TODO: 구현 필요
            if (!attackCmd.isEmpty()) {
                output = attackCmd;
            } else {
                output = bfs(me, target); // TODO: 구현 필요
            }

            // ─────────────────────────────────────
            System.out.printf("[SEND] %s%n", output);
            gameData = bridge.submit(output);
        }
        bridge.close();
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 1 : findUnit(String symbol)
    //  맵에서 symbol과 일치하는 칸의 [행, 열]을 반환
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static int[] findUnit(String symbol) {
        // TODO
        return new int[]{-1, -1};
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 2 : canAttack(int[] me, int[] target)
    //  me 위치에서 target을 사거리(3) 내 공격 가능하면
    //  해당 발사 커맨드(예: "U F")를 반환, 불가하면 ""
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static String canAttack(int[] me, int[] target) {
        // TODO
        return "";
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  TODO 3 : bfs(int[] start, int[] goal)
    //  goal을 공격할 수 있는 최단 위치까지 이동하는
    //  첫 번째 커맨드를 반환
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    static String bfs(int[] start, int[] goal) {
        // TODO
        return "S";
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  파싱 (수정 금지)
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
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