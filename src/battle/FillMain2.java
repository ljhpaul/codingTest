package battle;

import java.util.*;

public class FillMain2 {
	
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
	        
	        int[] me = findUnit("A");
	        int[] target = findUnit("X");
	        
	        String attackCmd = canAttack(me, target);
	        
	        if(attackCmd.isEmpty()) {
	        	output = attackCmd;
	        } else {
	        	output = bfs(me, target);
	        }
	        
	        // ─────────────────────────────────────
	        System.out.printf("[SEND] %s%n", output);
	        gameData = bridge.submit(output);
	    }
	    bridge.close();
	}

	private static int[] findUnit(String symbol) {
		// TODO Auto-generated method stub
		return new int[] {-1, -1};
	}

	private static String canAttack(int[] me, int[] target) {
		// TODO Auto-generated method stub
		return "";
	}

	private static String bfs(int[] me, int[] goal) {
		// TODO Auto-generated method stub
		return "S";
	}
}

