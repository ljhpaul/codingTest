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
		long answer = 0;
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
			sz[i] = i;
		}

		// solve
		setEdges();
		kruskalMST();

		// output
		System.out.println(answer);
		br.close();
	}

	// set edges
	private static void setEdges() {
		// TODO Auto-generated method stub
		
	}

	// kruskal MST
	private static void kruskalMST() {
		// TODO Auto-generated method stub
		
	}
	
	
}
/*
# Kruskal MST
1. 간선 배열(리스트), 유니온 파인드 배열(pr, sz) 초기화
2. 그래프(간선) 가중치 오름차순 정렬
3. 가중치 낮은 연산부터 연결 시도(find로 사이클 확인)
*/