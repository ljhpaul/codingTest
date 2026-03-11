package swea.d4.p1251;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int N;
    static int[][] islands;
    static List<Edge> edges;
    static int[] pr, sz;
    
    // Edge
    static class Edge implements Comparable<Edge> {
    	int u, v;
    	long w;
    	public Edge(int u, int v, long w) {
    		this.u = u;
    		this.v = v;
    		this.w = w;
    	}
    	@Override
    	public int compareTo(Edge o) {
    		return Long.compare(this.w, o.w);
    	}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	long answer = 0;
        	N = Integer.parseInt(br.readLine());
        	islands = new int[N][2];
        	edges = new ArrayList<>();
        	pr = new int[N+1];
        	sz = new int[N+1];
        	for(int i=1; i<=N; i++) {
        		pr[i] = i;
        		sz[i] = i;
        	}
        	
        	// input
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<N; i++) {
        		islands[i][0] = Integer.parseInt(st.nextToken());
        	}
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<N; i++) {
        		islands[i][1] = Integer.parseInt(st.nextToken());
        	}
        	double E = Double.parseDouble(br.readLine());
        	
            // solve
        	setEdges();
        	answer = (long) Math.round(E * kruskalMST());

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // set edges : Combination - N C 2 
    private static void setEdges() {
    	// 조합으로 간선 설정
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				long dx = islands[i][0] - islands[j][0];
				long dy = islands[i][1] - islands[j][1];
				edges.add(new Edge(i, j, dx*dx + dy*dy));
			}
		}
		
		// 간선 정렬
    	Collections.sort(edges);
	}

	// kruskalMST
	private static long kruskalMST() {
    	long wSum = 0L;
		int eCnt = 0;
		
		for(Edge e : edges) {
			if(find(e.u) != find(e.v)) {
				union(e.u, e.v);
				wSum += e.w;
				eCnt++;
			}
			
			if(eCnt == N - 1) break;
		}
		
		return wSum;
	}
	
	// find
	private static int find(int x) {
		if(pr[x] != x) {
			pr[x] = find(pr[x]);
		}
		return pr[x];
	}
	
	// union
	private static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if(A == B) return;
		
		if(sz[A] > sz[B]) {
			pr[B] = A;
			sz[A] += sz[B];
		} else {
			pr[A] = B;
			sz[B] += sz[A];
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