package baekjoon.ssafy.boj4386;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static int n;
	static double[][] pos;
	
	static final double INF = Double.MAX_VALUE;

	// main
	public static void main(String[] args) throws Exception {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		pos = new double[n][2];
		
		// input
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Double.parseDouble(st.nextToken());
			pos[i][1] = Double.parseDouble(st.nextToken());			
		}
		
		// solve
		double answer = prim();
		
		// output
		System.out.printf("%.2f", answer);
	}
	
	// prim MST - matrix
	private static double prim() {
		// init
		double costSum = 0;
		boolean[] visited = new boolean[n];	// 트리 포함 여부
		double[] minEdge = new double[n];	// 현재 트리에서 특정 정점까지의 최소 비용 배열
		Arrays.fill(minEdge, INF);
		
		// first vertex
		minEdge[0] = 0;
		
		// loop
		for(int i=0; i<n; i++) {
			int u = -1;
			double min = INF;
			
			// 트리 비포함 정점 중 최소 비용 간선의 정점 선택
			for(int v=0; v<n; v++) {
				if(!visited[v] && minEdge[v] < min) {
					u = v;
					min = minEdge[v];
				}
			}
			
			// 연결 그래프 확인
			if(u == -1) return -1;
			
			// 해당 정점 MST에 넣기
			costSum += min;
			visited[u] = true;
			
			// minEdge 갱신
			for(int v=0; v<n; v++) {
				double dx = pos[u][0] - pos[v][0]; 
				double dy = pos[u][1] - pos[v][1]; 
				double dist = Math.sqrt(dx*dx + dy*dy);
				if(!visited[v] && dist < minEdge[v]) {
					minEdge[v] = dist;
				}
			}
		}
		
		// return
		return costSum;
	}
}