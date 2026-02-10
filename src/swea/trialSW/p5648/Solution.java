package swea.trialSW.p5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static List<Atom> atoms;
    static int totalEnergy;

    static final int MAX = 4000;
    static final int MIN = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // Atom
    static class Atom {
        int x;
        int y;
        int d;
        int k;

        Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int N = Integer.parseInt(br.readLine());
            atoms = new ArrayList<>();
            totalEnergy = 0;

            // input
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(2*x + 2000, 2*y + 2000, d, k));
            }
            
            // solve
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // solve
    private static void solve() {
        while(!atoms.isEmpty()) {
            Map<Long, int[]> moved = new HashMap<>();
            Set<Long> collided = new HashSet<>();
            List<Atom> next = new ArrayList<>();

            // 이동
            for (Atom a : atoms) {
                a.x += dx[a.d];
                a.y += dy[a.d];
                if(isOut(a.x, a.y)) continue;
                long k = key(a.x, a.y);
                if (moved.containsKey(k)) {
                    // value : int[] {cnt, sum}
                    moved.get(k)[0]++;
                    moved.get(k)[1] += a.k;
                } else {
                    moved.put(k, new int[]{1, a.k});
                }
            }

            // 충돌 처리
            for (Long k : moved.keySet()) {
                if (moved.get(k)[0] >= 2) {
                    totalEnergy += moved.get(k)[1];
                    collided.add(k);
                }
            }

            // 생존자 갱신
            for (Atom a : atoms) {
                if(isOut(a.x, a.y)) continue;
                long k = key(a.x, a.y);
                if (collided.contains(k)) continue;
                next.add(a);
            }
            atoms = next;
        }
    }

    private static boolean isOut(int nx, int ny) {
        if(nx < MIN || nx > MAX || ny < MIN || ny > MAX) {
            return true;
        }
        return false;
    }

    private static long key(int x, int y) {
        return ((long)x<<32) | (y&0xffffffffL);
    }
}