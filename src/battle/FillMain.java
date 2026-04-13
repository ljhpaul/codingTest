package battle;

import java.util.*;

public class FillMain {
	
    private static String NICKNAME = "서울10_이정헌";
    private static String[][] mapData;
    private static Map<String, String[]> allies = new HashMap<>();
    private static Map<String, String[]> enemies = new HashMap<>();
    private static String[] codes;
    
	public static void main(String[] args) {
	    Bridge bridge = new Bridge();
	    String gameData = bridge.init(NICKNAME);
	
	    while (gameData.length() > 0) {
//	        parseData(gameData);
	
	        String output = "S"; // 기본값: 대기
	
	        // ─────────────────────────────────────
	        //  [문제 영역] 여기에 알고리즘을 작성합니다
	        // ─────────────────────────────────────
	        
	        
	        
	        // ─────────────────────────────────────
	        System.out.printf("[SEND] %s%n", output);
	        gameData = bridge.submit(output);
	    }
	    bridge.close();
	}
}

