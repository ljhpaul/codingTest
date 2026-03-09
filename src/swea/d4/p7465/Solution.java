package swea.d4.p7465;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int[] parent;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	int answer = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            parent = new int[n + 1];
            for(int i=1; i<=n; i++) parent[i] = i;
            Set<Integer> relationship = new HashSet<>();

            // solve
            for(int i=0; i<m; i++) {
            	st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                // union
                union(a, b);
            }
            for(int x=1; x<=n; x++) {
            	relationship.add(find(x));
            }
            answer = relationship.size();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // find
    private static int find(int x) {
    	// 현재 요소가 루트가 아니면 부모 갱신
    	if(parent[x] != x) {
    		parent[x] = find(parent[x]);
    	}
    	// 대표 노드 반환
    	return parent[x];
    }
    
    // union
    private static void union(int a, int b) {
    	// 각 요소의 대표 노드 반환
    	int A = find(a);
    	int B = find(b);
    	
    	// 루트가 같으면 중단
    	if(A == B) return;
    	
    	// A < B이면 B를 A에 합침
    	if(A < B) parent[B] = A;
    	// B < A이면 A를 B에 합침
    	else parent[A] = B;
    }
}