package swea.d4.p3124;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
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
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	long costSum = 0;
        	int edgeCnt = 0;
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
        			costSum += e.w;
        			edgeCnt++;
        		}
        		
        		// MST 완성(간선 V-1개)
        		if(edgeCnt == V - 1) break;
        	}

            // answer
            sb.append("#").append(tc).append(" ").append(costSum).append("\n");
        }
        // output
        System.out.println(sb);
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
    	
    	if(size[A] > B) {
    		parent[B] = A;
    		size[A] += B;
    	} else {
    		parent[A] = B;
    		size[B] += A;
    	}
    }
}
/*
# Kruscal MST
1. 에지 리스트, 유니온 파인드 배열 초기화
	- Edge[] edges
	- int[] parent
2. 그래프(간선) 가중치 오름차순 정렬
3. 가중치 낮은 간선부터 연결 시도(find로 사이클 확인)
4. 간선 N-1개까지 반복
5. MST 완성 및 총 비용 출력

# 구조
1. Edge[] edges
2. parent[]
3. find()
4. union()
5. sort(edges)
6. 간선 순회
   if(find(u) != find(v))
       union
       cost += w
       count++
7. count == N-1 종료
*/