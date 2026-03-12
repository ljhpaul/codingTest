package baekjoon.ssafy.boj2887;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] pr, sz;
	static List<Edge> edges;
	static List<Planet> planets;
	
	// Edge
	static class Edge implements Comparable<Edge> {
		int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	// Planet
	static class Planet {
		int id, x, y, z;

		public Planet(int id, int x, int y, int z) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		N = Integer.parseInt(br.readLine());
		pr = new int[N];
		sz = new int[N];
		edges = new ArrayList<>();
		planets = new ArrayList<>();
		for(int i=0; i<N; i++) {
			// 행성 정보 입력
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planets.add(new Planet(i, x, y, z));
			
			// 유니온 파인드 배열 초기화
			pr[i] = i;
			sz[i] = 1;
		}

		// solve
		setEdges();
		long answer = kruskalMST();

		// output
		System.out.println(answer);
		br.close();
	}

	// set edges
	private static void setEdges() {
		// x 방향
		Collections.sort(planets, (a, b) -> Integer.compare(a.x, b.x));
		for(int i=0; i<planets.size()-1; i++) {
			Planet p1 = planets.get(i);
			Planet p2 = planets.get(i+1);
			edges.add(new Edge(p1.id, p2.id, p2.x-p1.x));
		}
		
		// y 방향
		Collections.sort(planets, (a, b) -> Integer.compare(a.y, b.y));
		for(int i=0; i<planets.size()-1; i++) {
			Planet p1 = planets.get(i);
			Planet p2 = planets.get(i+1);
			edges.add(new Edge(p1.id, p2.id, p2.y-p1.y));
		}
		
		// z 방향
		Collections.sort(planets, (a, b) -> Integer.compare(a.z, b.z));
		for(int i=0; i<planets.size()-1; i++) {
			Planet p1 = planets.get(i);
			Planet p2 = planets.get(i+1);
			edges.add(new Edge(p1.id, p2.id, p2.z-p1.z));
		}
		
		// 간선 정렬
		Collections.sort(edges);
	}

	// kruskal MST
	private static long kruskalMST() {
		// init
		int eCnt = 0;
		long wSum = 0;
		
		// 비용 작은 간선부터 연결 시도
		for(Edge e : edges) {
			// 사이클 검사
			if(find(e.u) != find(e.v)) {
				union(e.u, e.v);
				eCnt++;
				wSum += e.w; 
			}
			
			// MST 완성
			if(eCnt == N-1) break;
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
		
		if(sz[A] >= sz[B]) {
			pr[B] = A;
			sz[A] += sz[B];
		} else {
			pr[A] = B;
			sz[B] += sz[A];
		}
	}
}