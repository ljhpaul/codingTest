package baekjoon.newbie.week06.boj1197;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    static Edge[] edges; // 간선 리스트
    static int[] parent; // 유니온 파인드 배열
    static int[] size;
    
    // Edge class
    static class Edge implements Comparable<Edge> {
    	int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
    	
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
    }

	// main
	public static void main(String[] args) throws IOException {
		// init
    	long wSum = 0;
    	int eCnt = 0;
    	st = new StringTokenizer(br.readLine());
    	int V = Integer.parseInt(st.nextToken());
    	int E = Integer.parseInt(st.nextToken());
    	edges = new Edge[E];
    	parent = new int[V + 1];
    	size = new int[V + 1];
    	for(int i=1; i<=V; i++) {
    		parent[i] = i;
    		size[i] = 1;
    	}
    	
    	// input
    	for(int i=0; i<E; i++) {
    		st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	edges[i] = new Edge(u, v, w);
    	}

        // solve
    	Arrays.sort(edges);
    	for(Edge e : edges) {
    		// 사이클 확인
    		if(find(e.u) != find(e.v)) {
    			union(e.u, e.v);
    			wSum += e.w;
    			eCnt++;
    		}
    		
    		// MST 완성(간선 V-1개)
    		if(eCnt == V - 1) break;
    	}

		// output
		System.out.println(wSum);
		br.close();
	}

    
    // find
    private static int find(int x) {
    	if(parent[x] != x) {
    		parent[x] = find(parent[x]);
    	}
    	return parent[x];
    }
    
    // union
    private static void union(int a, int b) {
    	int A = find(a);
    	int B = find(b);
    	
    	if(A == B) return;
    	
    	if(size[A] > size[B]) {
    		parent[B] = A;
    		size[A] += size[B];
    	} else {
    		parent[A] = B;
    		size[B] += size[A];
    	}
    }
}