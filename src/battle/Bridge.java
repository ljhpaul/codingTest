package battle;

import java.util.*;
import java.io.*;

public class Bridge {

    // ── 연습할 스테이지 로그를 여기에 붙여넣기 ──────────────────
    private static final String[] GAME_LOG = {
        // Stage 4 예시 (data_01.txt에서 발췌)
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G G G G\nA R R G R R R R\nG G G G G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G G G G\nG R R A R R R R\nG G G G G G R R\nA 100 D 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G G G G\nG R R G R R R R\nG A G G G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G G G G\nG R R G R R R R\nG G A G G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G G G G\nG R R G R R R R\nG G G A G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W A G G G G\nG R R G R R R R\nG G G G G G R R\nA 100 U 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G A G G G\nG R R G R R R R\nG G G G G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G M G G\nG R R G R R R R\nG G G G G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R G\nG R W G G G A G\nG R R G R R R R\nG G G G G G R R\nA 100 R 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G G\nG R W W W W R A\nG R W G G G G G\nG R R G R R R R\nG G G G G G R R\nA 100 U 1 0\nX 10",
        "8 8 1 1 0\nR R G G G R G X\nG G G R G G G G\nR G W W W W G G\nG G W G G G G A\nG R W W W W R G\nG R W G G G G G\nG R R G R R R R\nG G G G G G R R\nA 100 U 1 0\nX 10",
        // 마지막: 공격 후 종료 → 빈 문자열
        ""
    };
    // ──────────────────────────────────────────────────────────────

    private int turn = 0;

    public String init(String nickname) {
        System.out.println("=== MOCK BRIDGE START === nickname: " + nickname);
        return GAME_LOG[turn++];
    }

    public String submit(String command) {
        System.out.println("[SUBMIT] " + command);
        if (turn >= GAME_LOG.length) return "";
        return GAME_LOG[turn++];
    }

    public void close() {
        System.out.println("=== MOCK BRIDGE END ===");
    }
}