package baekjoon.training.ch02.boj14888;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    static int max, min, N;
    static int[] arr;

    static final int INF = 1_000_000_001;

	// main
	public static void main(String[] args) throws IOException {
		// init
        max = -INF;
        min = INF;
		N = Integer.parseInt(br.readLine());
        arr = new int[N];
		
		// input
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int mul = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

		// solve
        solve(1, arr[0], plus, minus, mul, div);

		// output
		System.out.println(max + "\n" + min);
		br.close();
	}

    // solve
    private static void solve(int idx, int num, int plus, int minus, int mul, int div) {
        // pruning
        if(num >= min && num <= max) return;

        // base
        if(idx == arr.length) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        // recursion
        if(plus > 0) solve(idx + 1, num + arr[idx], plus - 1, minus, mul, div);
        if(minus > 0) solve(idx + 1, num - arr[idx], plus, minus - 1, mul, div);
        if(mul > 0) solve(idx + 1, num * arr[idx], plus, minus, mul - 1, div);
        if(div > 0) solve(idx + 1, num / arr[idx], plus, minus, mul, div - 1);
    }
}