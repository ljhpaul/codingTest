package baekjoon.roadmap.week01.boj7576;

import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static FastScanner fs = new FastScanner(System.in);

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    // main
    public static void main(String[] args) throws IOException {
        // init
        int answer = 0;
        int n = fs.nextInt();
        int m = fs.nextInt();
        int[][] grid = new int[m][n];
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        int[] dx4 = new int[]{1, 0, -1, 0};
        int[] dy4 = new int[]{0, 1, 0, -1};

        Queue<int[]> q = new ArrayDeque<>();

        // input
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = fs.nextInt();
                if (grid[i][j] == -1) {
                    visited[i][j] = true;
                } else if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // get answer
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx4[d];
                int ny = y + dy4[d];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (!visited[nx][ny]) {
                        grid[nx][ny] = 1;
                        q.offer(new int[]{nx, ny});
                        dist[nx][ny] = dist[x][y] + 1;
                        visited[nx][ny] = true;
                    }
                }
            }

        }

        outer:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dist[i][j]);
                if (grid[i][j] == 0) {
                    answer = -1;
                    break outer;
                }
            }
        }

        // output
        System.out.println(answer);
    }

}
