package swea.pro.p26281;

import java.util.*;

class UserSolution {
	// field
	int n, m, runnerIdx, cooldown, runnerInterval, turn, start, end;
	int[][] map;
	List<int[]> road;
	List<Tower> towers;
	Runner[] runners;
	int[] retTs;
	int[] retHP;
	
	int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	// Tower(r, c, target, reload, Interval)
	class Tower {
		int r, c, reload, Interval;
		Runner target;
		
		public Tower(int r, int c, int interval) {
			this.r = r;
			this.c = c;
			this.reload = 0;
			Interval = interval;
			this.target = null;
		}
		
		boolean inRange(int roadIdx) {
			if(roadIdx < 0) return false;
			int[] runnerPos = road.get(roadIdx);
			int runnerRow = runnerPos[0];
			int runnerCol = runnerPos[1];
			return Math.abs(runnerRow - r) + Math.abs(runnerCol - c) <= 3;
		}
	}
	
	// Runner(runnerIdx, roadIdx, hp, inMap)
	class Runner implements Comparable<Runner> {
		int runnerIdx, roadIdx, hp;
		boolean inMap;
		
		public Runner(int runnerIdx, int hp) {
			this.runnerIdx = runnerIdx;
			this.roadIdx = -1;
			this.hp = hp;
			this.inMap = false;
		}

		@Override
		public int compareTo(Runner o) {
			if(this.hp == o.hp) {
				return Integer.compare(this.runnerIdx, o.runnerIdx);
			}
			return Integer.compare(this.hp, o.hp);
		}
	}
	
	// init
	void init(int N, int mMap[][]) {
		n = N;
		map = mMap;
		setRoad();
		
		towers = new ArrayList<>();
	}

	// set road
	private void setRoad() {
		road = new ArrayList<>();
		
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(map[r][c] == 2) {
					bfs(r, c);
					start = 0;
					end = road.size() - 1;
					return;
				}
			}
		}
	}
	
	// bfs
	private void bfs(int sr, int sc) {
		// init
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		
		// start
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		// loop
		while(!q.isEmpty()) {
			// curr
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// set road pos
			road.add(new int[] {r, c});
			if(map[r][c] == 3) return;
			
			// next
			for(int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if(visited[nr][nc]) continue;
				
				if(map[nr][nc] == 1 || map[nr][nc] == 3) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}

	// add tower
	void addTower(int mRow, int mCol, int mInterval) {
		Tower tower = new Tower(mRow, mCol, mInterval);
		towers.add(tower);
	}

	// run simulation
	void runSimulation(int M, int mInterval, int mHP, int mRetTs[], int mRetHP[]) {
		// init
		m = M;
		runnerIdx = 0;
		runners = new Runner[m];
		cooldown = mInterval;
		runnerInterval = mInterval;
		
		for(int i = 0; i < m; i++) {
			runners[i] = new Runner(i, mHP);
		}
		
		retTs = mRetTs;
		retHP = mRetHP;
		
		// reset towers
		for(Tower tower : towers) {
			tower.reload = 0;
			tower.target = null;
		}
		
		// simulate
		simulate();
	}
	
	// simulate
	private void simulate() {
		turn = 1;
		while(true) {
			// towers' turn
			towerTime();
			
			// runners' turn
			if(runnerTime()) return;
			
			// next
			turn++;
		}
	}

	// towers' turn
	private void towerTime() {
		int[] damage = new int[m];
		
		// loop
		for(Tower tower : towers) {
			// 1. reload 여부 확인, 아니라면 reload-- 후 스킵
			if(--tower.reload > 0) continue;
			
			// 2. target이 없거나 공격 범위 밖이면 대상 재탐색
			if(tower.target == null || !tower.target.inMap || tower.target.hp <= 0 || !tower.inRange(tower.target.roadIdx)) {
				findTarget(tower);
			}
			
			// 3. 그래도 대상이 없으면 다음 턴으로 스킵
			if(tower.target == null) continue;
			
			// 4. 공격 -> target 체력 감소, reload = interval 갱신
			damage[tower.target.runnerIdx]++;
			tower.reload = tower.Interval;
		}
		
		// damage
		for(int i=0; i<m; i++) {
			if(!runners[i].inMap) continue;
			runners[i].hp -= damage[i];
		}
	}

	// find target
	private void findTarget(Tower tower) {
		// init
		PriorityQueue<Runner> pq = new PriorityQueue<>();
		
		// loop
		for(Runner runner : runners) {
			// 범위 내 도망자 추가
			if(runner.inMap && tower.inRange(runner.roadIdx)) {
				pq.offer(runner);
			}
		}
		
		// set target
		tower.target = pq.isEmpty() ? null : pq.poll();
	}

	// runners' turn
	private boolean runnerTime() {
		int inMapCount = 0;
		if(cooldown > 0) cooldown--;
		
		// loop
		for(Runner runner : runners) {
			// 1. 맵에 존재하지 않으면 패스
			if(runner.runnerIdx >= runnerIdx) break;
			if(!runner.inMap) continue;
			
			inMapCount++;
			
			// 2. 맵에 존재하는 도망자 중 체력 <= 0 이면 사망(inMapCount--), retTs에 턴 기입, retHP에 피 0
			if(runner.hp <= 0) {
				runner.inMap = false;
				inMapCount--;
				retTs[runner.runnerIdx] = turn;
				retHP[runner.runnerIdx] = 0;
				continue;
			}
			
			// 3. 행동 주기 도래 여부 확인, cooldown == 0이면 이동
			if(cooldown <= 0) {
				// 도착 여부 확인
				if(++runner.roadIdx >= end) {
					runner.inMap = false;
					inMapCount--;
					retTs[runner.runnerIdx] = turn;
					retHP[runner.runnerIdx] = runner.hp;
				}
			}
		}
		
		// 행동 주기 도래 확인
		if(cooldown <= 0) {
			cooldown = runnerInterval;
			
			// 남아있는 도망자 존재할 때, 출발지에 새 도망자 등장
			if(runnerIdx < m) {
				Runner newRunner = runners[runnerIdx++];
				newRunner.roadIdx = start;
				newRunner.inMap = true;
				inMapCount++;
			}
		}
		
		// 4. 게임 종료 여부 반환
		return runnerIdx == m && inMapCount <= 0;
	}
}
