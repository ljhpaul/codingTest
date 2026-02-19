package swea.d5.p1267;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int V, E;
    static List<Integer>[] graph;
    static int[] indegree;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = 10;
        for(int tc = 1; tc <= T; tc++) {
            // init
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new ArrayList[V+1];
            indegree = new int[V+1];
            
            for(int v=1; v<=V; v++) {
            	graph[v] = new ArrayList<>();
            }
            
            // input
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<E; i++) {
            	int from = Integer.parseInt(st.nextToken());
            	int to = Integer.parseInt(st.nextToken());
            	graph[from].add(to);
            	indegree[to]++;
            }
            
            // solve
            String answer = topologicalSort();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // topology sort : Kahn(BFS)
	private static String topologicalSort() {
		StringBuilder result = new StringBuilder();
		
		// 진입 차수 0인 노드 큐에 삽입
		Queue<Integer> q = new ArrayDeque<>();
		for(int v=1; v<=V; v++) {
			if(indegree[v] == 0) {
				q.offer(v);
			}
		}
		
		// BFS - 하나씩 꺼내어 인접 노드의 진입차수 감소, 0이 되면 해당 노드를 큐에 삽입
		while(!q.isEmpty()) {
			int curr = q.poll();
			result.append(curr).append(" ");
			for(int next : graph[curr]) {
				if(--indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		return result.toString();
	}
    
}