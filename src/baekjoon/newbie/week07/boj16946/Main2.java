package baekjoon.newbie.week07.boj16946;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder sb;
	static int N, M, globalId;
	static int[][] map, groupId, moveable;
	static List<Integer> groupSize;
	static List<int[]> walls;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// Node
	static class Node {
		int id, size;
		
		public Node(int id, int size) {
			this.id = id;
			this.size = size;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		init();
		
		// solve
		solve();
		getAnswer();
		
		for(int[] line : groupId) {
			System.out.println(Arrays.toString(line));
		}

		// output
		System.out.println(sb);
		br.close();
	}

	// init
	private static void init() throws IOException  {
		// init
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		groupId = new int[N][M];
		groupSize = new ArrayList<>();
		moveable = new int[N][M];
		walls = new ArrayList<>();
		visited = new boolean[N][M];
        globalId = 0;

		// input
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c) - '0';
				if(map[r][c] == 1) walls.add(new int[] {r, c});	// 벽 위치들만 따로 저장
			}
		}
	}

	// solve
	private static void solve() {
		// i) BFS로 sizes 영역 채우기
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 1) continue;
				if(visited[r][c]) continue;
				bfs(r, c);
			}
		}
		
		// ii) 벽 부분만 4방 체크
		for(int[] w : walls) {
			int r = w[0];
			int c = w[1];
			int cnt = 1;
			
			// check adjacent(delta 4)
			Set<Integer> checkedId = new HashSet<>();
			for(int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				// check valid area
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				int id = groupId[nr][nc];
				int size = groupSize.get(id);
				if(checkedId.contains(id)) continue;
				
				// 인접 영역의 크기 반영
				cnt += size;
				checkedId.add(id);
			}
			
			// moveable에 cnt 반영
			moveable[r][c] = cnt;
		}
	}

	// BFS
	private static void bfs(int sr, int sc) {
		// init
		int size = 0;
		List<int[]> path = new ArrayList<>();
		Queue<int[]> q = new ArrayDeque<>();
		
		// start
		int[] start = new int[] {sr, sc};
		path.add(start);
		q.offer(start);
		visited[sr][sc] = true;
		size++;
		
		// loop
		while(!q.isEmpty()) {
			// cur
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			// next
			for(int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				// check valid area
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] != 0) continue;
				
				int[] next = new int[] {nr, nc};
				path.add(next);
				q.offer(next);
				visited[nr][nc] = true;
				groupId[nr][nc] = globalId;
				size++;
			}
		}
		
		// 해당 영역의 모든 경로에 size 확정
		groupSize.add(size);
		globalId++;
	}

	// get answer
	private static void getAnswer() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(moveable[r][c] % 10);
			}
			sb.append("\n");
		}
	}
}