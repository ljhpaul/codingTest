package swea.trialSW.p2383;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N;
    static Stair stair1, stair2;
    static List<Pos> people;
    static List<Integer> to1, to2;
    static PriorityQueue<Integer> pq1, pq2;
    
    // Pos
    static class Pos {
    	int r, c;
    	
    	public Pos(int r, int c) {
    		this.r = r;
    		this.c = c;
    	}
    }
    
    // Stair
    static class Stair {
    	int r, c, time;
    	
    	public Stair(int r, int c, int time) {
    		this.r = r;
    		this.c = c;
    		this.time = time;
    	}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            stair1 = null;
            stair2 = null;
            people = new ArrayList<>();
            to1 = new ArrayList<>();
            to2 = new ArrayList<>();
            pq1 = new PriorityQueue<>();
            pq2 = new PriorityQueue<>();

            // input
            for(int r = 0; r < N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c = 0; c < N; c++) {
            		int tmp = Integer.parseInt(st.nextToken());
            		if(tmp == 1) {
            			people.add(new Pos(r, c));
            		} else if(tmp > 1) {
            			if(stair1 == null) stair1 = new Stair(r, c, tmp);
            			else stair2 = new Stair(r, c, tmp);
            		}
            	}
            }
            
            // solve
            setDestination(0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // set destination
	private static void setDestination(int cnt) {
		// base
		if(cnt == people.size()) {
			downStair();
			return;
		}
		
		// recursion
		Pos curr = people.get(cnt);
		
		// select stair 1
		to1.add(Math.abs(curr.r - stair1.r) + Math.abs(curr.c - stair1.c) + 1);
		setDestination(cnt + 1);
		to1.remove(to1.size() - 1);
		
		// select stair 2
		to2.add(Math.abs(curr.r - stair2.r) + Math.abs(curr.c - stair2.c) + 1);
		setDestination(cnt + 1);
		to2.remove(to2.size() - 1);
	}

	// down stair
	private static void downStair() {
		// init
		int time1 = 0;
		int time2 = 0;
		Queue<Integer> q = new ArrayDeque<>();
		
		// insert pq
		for(int len : to1) pq1.add(len);
		for(int len : to2) pq2.add(len);
		
		// loop 1
		while(!pq1.isEmpty()) {
			int curr = pq1.poll();
			if(q.size() < 3) {
				q.add(curr + stair1.time);
			} else {
				if(curr < q.peek()) {
					q.add(q.peek() + stair1.time);
				} else {
					q.add(curr + stair1.time);
				}
				time1 = Math.max(time1, q.poll());
			}
		}
		while(!q.isEmpty()) {
			time1 = Math.max(time1, q.poll());
		}
		
		// loop 2
		while(!pq2.isEmpty()) {
			int curr = pq2.poll();
			if(q.size() < 3) {
				q.add(curr + stair2.time);
			} else {
				if(curr < q.peek()) {
					q.add(q.peek() + stair2.time);
				} else {
					q.add(curr + stair2.time);
				}
				time2 = Math.max(time2, q.poll());
			}
		}
		while(!q.isEmpty()) {
			time2 = Math.max(time2, q.poll());
		}
		
		// get answer
		answer = Math.min(Math.max(time1, time2), answer);
	}
}