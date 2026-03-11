package swea.d4.p3124;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int V, E;
    static ArrayList<Node>[] graph;
    
    // Node
    static class Node implements Comparable<Node> {
    	int to, cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
    	
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	st = new StringTokenizer(br.readLine());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	graph = new ArrayList[V+1];
        	for(int i=1; i<=V; i++) graph[i] = new ArrayList<>();
        	
        	// input
        	for(int i=0; i<E; i++) {
        		st = new StringTokenizer(br.readLine());
            	int u = Integer.parseInt(st.nextToken());
            	int v = Integer.parseInt(st.nextToken());
            	int w = Integer.parseInt(st.nextToken());
            	// 무방향 그래프
            	graph[u].add(new Node(v, w));
            	graph[v].add(new Node(u, w));
        	}

            // solve
        	long costSum = primMST();

            // answer
            sb.append("#").append(tc).append(" ").append(costSum).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // Prim MST - PriorityQueue 
	private static long primMST() {
		// init
		long costSum = 0L;
		int cnt = 0;
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// start vertex
		pq.offer(new Node(1, 0));
		
		// loop
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.to]) continue;
			
			visited[curr.to] = true;
			costSum += curr.cost;
			if(++cnt == V) break;
			
			for(Node next : graph[curr.to]) {
				if(!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		// return
		return costSum;
	}
    
}