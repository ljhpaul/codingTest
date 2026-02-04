package baekjoon.roadmap.etc.boj14891;

import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] arr = new char[6][];
    static boolean[] same;
    static int[] left = {0, 6, 6, 6, 6, 0};
    static int[] right = {0, 2, 2, 2, 2, 0};

    // main
    public static void main(String[] args) throws IOException {
        // init
        int answer = 0;
        arr[1] = br.readLine().toCharArray();
        arr[2] = br.readLine().toCharArray();
        arr[3] = br.readLine().toCharArray();
        arr[4] = br.readLine().toCharArray();
        int k = Integer.parseInt(br.readLine());

        // get answer
        for(int i = 0; i < k; i++) {
            // 톱니 맞물림 최신화
            same = new boolean[5];
            for (int j = 1; j < 4; j++) {
                if(arr[j][right[j]] == arr[j + 1][left[j + 1]]) {
                    same[j] = true;
                }
            }
            // 대상 톱니 바퀴 및 방향 입력
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int clock = Integer.parseInt(st.nextToken());

            // target : 시작 톱니 번호
            // clock : 반시계(-1), 시계(1)
            // direction : 좌향(-1), 양방향(0), 우향(1)
            turnWheel(target, clock, 0);
            // System.out.println(Arrays.toString(left));
            // System.out.println(Arrays.toString(right));
        }

        if(arr[1][(left[1] + 2) % 8] == '1') answer += 1;
        if(arr[2][(left[2] + 2) % 8] == '1') answer += 2;
        if(arr[3][(left[3] + 2) % 8] == '1') answer += 4;
        if(arr[4][(left[4] + 2) % 8] == '1') answer += 8;

        // output
        System.out.println(answer);
    }

    // turnWheel
    public static void turnWheel(int target, int clock, int direction) {
        // 톱니 범위 제한 조건
        if(target < 1 || target > 4) return;

        // 좌우 같은 극 확인
        boolean leftSame = same[target - 1];
        boolean rightSame = same[target];
        // System.out.println(target + "번 톱니바퀴 좌우 " + leftSame + ", " + rightSame);

        // 톱니바퀴 회전
        if(clock == 1) {
            // 시계 방향 : 좌우 포인터 이동(+)
            // System.out.println(target + "번 톱니바퀴 시계방향 회전");
            left[target] = (left[target] - 1 + 8) % 8;
            right[target] = (right[target] - 1 + 8) % 8;
        } else if(clock == -1) {
            // 반시계 방향 : 좌우 포인터 이동(-)
            // System.out.println(target + "번 톱니바퀴 반시계방향 회전");
            left[target] = (left[target] + 1) % 8;
            right[target] = (right[target] + 1) % 8;
        }

        // 톱니 회전 전파
        if(direction == 0) {
            // 시작 톱니(0)일 경우 양방향 전파
            if(!leftSame) turnWheel(target - 1, -clock, -1);
            if(!rightSame) turnWheel(target + 1, -clock, 1);
        } else if(direction == -1) {
            // 좌향일 경우 왼쪽으로만 전파
            if(!leftSame) turnWheel(target - 1, -clock, -1);
        } else if(direction == 1) {
            // 우향일 경우 오른쪽으로만 전파
            if(!rightSame) turnWheel(target + 1, -clock, 1);
        }
    }

}