package programmers.java.ch05_Array;

// 방문 길이
// https://school.programmers.co.kr/learn/courses/30/lessons/49994
public class P07 {

    public static void main(String[] args) {
        // String dirs = "ULURRDLLU";
        // String dirs = "LULLLLLLU";
        String dirs = "LRLRLRLRL";

        P07 p07 = new P07();
        System.out.println(p07.solution(dirs));
    }

    public int solution(String dirs) {
        int answer = 0;

        // 길 초기화 11*11 좌표에서 상하좌우(4) 순서
        int[][][] roads = new int[11][11][4];
        /*
          0 1 2 3 4 5 6 7 8 9 0
        0
        1
        2
        3
        4
        5           N
        6
        7
        8
        9
        0
         */

        // 초기 위치 설정
        int y = 5;
        int x = 5;

        // 캐릭터 길 걷기
        for(char dir : dirs.toCharArray()) {
            switch(dir) {
                case 'U' : {
                    if (y == 0) break;
                    roads[y][x][0]++;
                    y--;
                    roads[y][x][2]++;
                    break;
                }
                case 'R' : {
                    if (x == 10) break;
                    roads[y][x][1]++;
                    x++;
                    roads[y][x][3]++;
                    break;
                }
                case 'D' : {
                    if (y == 10) break;
                    roads[y][x][2]++;
                    y++;
                    roads[y][x][0]++;
                    break;
                }
                case 'L' : {
                    if (x == 0) break;
                    roads[y][x][3]++;
                    x--;
                    roads[y][x][1]++;
                    break;
                }
            }
        }

        // 처음 걸어본 길의 길이 구하기
        int cnt = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 4; k++) {
                    if (roads[i][j][k] > 0) {
                        cnt++;
                    }
                }
            }
        }

        answer = cnt / 2;
        return answer;
    }

}
