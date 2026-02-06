package swea.trialSW.p2115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[][] map;
    static int[][] maxMap;
    static int N;   // dfs 최대 깊이
    static int M;
    static int C;
    static int answer;   // 남은 블록 최소치 갱신(최대 폭파)

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int answer = 0;
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            // maxMap = new int[N][N-M+1];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // solve
            answer = 0;

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
}
/*



 */