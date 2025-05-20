package programmers;

public class chicken {

	public static void main(String[] args) {
		int result1 = solution(100);
		System.out.println(result1);
		
		int result2 = solution(1081);
		System.out.println(result2);
	}
	
    public static int solution(int chicken) {
        int answer = 0;
        
        while(chicken >= 10) {
        	answer += chicken / 10;
        	chicken = (chicken % 10) + (chicken / 10);
        }
        
        return answer;
    }
}
/* 
 * 1081 -> 1 + 108 (108), 
 * 109  -> 9 + 10  (118), 
 * 19   -> 9 + 1   (119), 
 * 10   -> 0 + 1   (120),
 * 1	-> 1 : break;
 */ 