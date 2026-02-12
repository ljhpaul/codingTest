package swea.d2.p14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int answer, N;
    static int[] H;
    static int maxHeight;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            N = Integer.parseInt(br.readLine());
            H = new int[N];
            maxHeight = 0;

            // input
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, H[i]);
            }

            // solve
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // solve
    private static void solve() {
        // 최대 높이와의 차로 갱신
        for(int i=0; i<N; i++) {
            H[i] = maxHeight - H[i];
        }
        
        int cnt1 = 0;
        int cnt2 = 0;

        // 각 숫자마다 2를 최대한 가져간 뒤 나머지 1 카운팅
        for(int i=0; i<N; i++) {
            cnt2 += H[i] / 2;
            H[i] %= 2;
            cnt1 += H[i];
        }
        
        // (1+2) 사용하는 이틀치 먼저 제거
        int tmp = Math.min(cnt1, cnt2);
        answer += tmp * 2;
        cnt1 -= tmp;
        cnt2 -= tmp;

        // 나머지 계산
        if(cnt1 > 0) {
            answer += cnt1 * 2 - 1;
        } else if(cnt2 > 0) {
            answer += (cnt2 / 3) * 4;
            cnt2 %= 3;
            if(cnt2 == 1) {
                answer += 2;
            } else if(cnt2 == 2) {
                answer += 3;
            }
        }
    }
}