package baekjoon.roadmap.etc.boj17307;

import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    // main
    public static void main(String[] args) throws IOException {
        // init
        int btn = 0;
        long minCnt = Long.MAX_VALUE;
        int n = sc.nextInt();
        long c = sc.nextLong();
        long[] arr = new long[n];

        // input
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        // get answer
        for(int i = 0; i < n; i++) {
            long now = arr[i];
            long cnt = 0;

            // 포인터 초기 세팅
            int p1 = i;
            int p2 = i;
            while(p1 > 0) {
                if(now == arr[p1 - 1]) {
                    p1--;
                } else {
                    break;
                }
            }
            while(p2 < n - 1) {
                if(now == arr[p2 + 1]) {
                    p2++;
                } else {
                    break;
                }
            }

//            System.out.println(p1 + ", " + p2);

            // cnt 구하기
            while(p1 > 0 || p2 < n - 1) {
                long left = p1 == 0 ? Long.MAX_VALUE : arr[p1 - 1];
                long right = p2 == n - 1 ? Long.MAX_VALUE : arr[p2 + 1];
                long gapLeft = (now < left) ? left - now : c + left - now;
                long gapRight = (now < right) ? right - now : c + right - now;

                if(gapLeft < gapRight) {
                    now = left;
                    cnt += gapLeft;
                } else {
                    now = right;
                    cnt += gapRight;
                }

                while(p1 > 0) {
                    if(now == arr[p1 - 1]) {
                        p1--;
                    } else {
                        break;
                    }
                }
                while(p2 < n - 1) {
                    if(now == arr[p2 + 1]) {
                        p2++;
                    } else {
                        break;
                    }
                }
            }

            // cnt 최소값 갱신
            if(cnt < minCnt) {
                btn = i + 1;
                minCnt = cnt;
            }
        }

        // output
        System.out.println(btn);
        System.out.println(minCnt);
    }

}