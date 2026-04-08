package swea.trialSW.p2477;

import java.io.*;
import java.util.*;

class WrongSolution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, maxTime, N, M, K, A, B;
    static ArrayList<Integer>[] customerIdByTime;
    static PriorityQueue<Integer> pqA;
    static PriorityQueue<Node> pqB;
    static int[] receiptTime, repairTime, arrivedTime;
    static Customer[] receiptCounter, repairCounter;
    static List<Integer> targetList;
    
    // Customer
    static class Customer {
    	int customerId, remainTime;

		public Customer(int customerId, int remainTime) {
			this.customerId = customerId;
			this.remainTime = remainTime;
		}
    }
    
    // Node
    static class Node implements Comparable<Node> {
    	int customerId, receiptId, arrived;
    	
    	public Node(int customerId, int receiptId, int arrived) {
    		this.customerId = customerId;
    		this.receiptId = receiptId;
    		this.arrived = arrived;
    	}
    	
    	@Override
    	public int compareTo(Node o) {
    		if(this.arrived == o.arrived) return Integer.compare(this.receiptId, o.receiptId);
    		return Integer.compare(this.arrived, o.arrived);
    	}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	init();

        	input();
            
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // init
	private static void init() throws IOException {
        answer = 0;
        maxTime = 0;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        customerIdByTime = new ArrayList[1001];
        for(int tk = 0; tk <= 1000; tk++) customerIdByTime[tk] = new ArrayList<>();
        
        pqA = new PriorityQueue<>(); // 접수 창구 우선순위 (고객번호 asc)
        pqB = new PriorityQueue<>(); // 정비 창구 우선순위 (먼저 기다린 순, 접수 창구 번호 asc)
        
        receiptTime = new int[N + 1];
        repairTime = new int[M + 1];
        arrivedTime = new int[K + 1];
        receiptCounter = new Customer[N + 1];
        repairCounter = new Customer[M + 1];
        
        targetList = new ArrayList<>();
	}

    // input
	private static void input() throws IOException {
    	// receipt time
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	receiptTime[i] = Integer.parseInt(st.nextToken());
        }

    	// repair time
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++) {
        	repairTime[i] = Integer.parseInt(st.nextToken());
        }

    	// customer time
        st = new StringTokenizer(br.readLine());
        for(int customerId = 1; customerId <= K; customerId++) {
        	int arrived = Integer.parseInt(st.nextToken());
        	customerIdByTime[arrived].add(customerId);
        	arrivedTime[customerId] = arrived;
        	maxTime = Math.max(maxTime, arrived);
        }
	}

    // solve
	private static void solve() {
		// 시간 순회 (1 ~ maxTime)
		for(int time = 1; time <= maxTime; time++) {
			int receiptIdx = N + 1;
			int repairIdx = M + 1;
			
			// 정비 창구 순회 -> 감산하며 완료 고객 종료
			for(int i = 1; i <= M; i++) {
				if(repairCounter[i] == null) {
					repairIdx = Math.min(repairIdx, i);
					continue;
				}
				if(--repairCounter[i].remainTime <= 0) {
					repairCounter[i] = null;	// 정비 창구 완료 처리
				}
			}
			
			// 정비 창구 대기열 털어내기
			while(!pqB.isEmpty() && repairIdx < M + 1) {
				if(repairCounter[repairIdx] == null) {
					Node node = pqB.poll();
					repairCounter[repairIdx] = new Customer(node.customerId, repairTime[repairIdx]);
					if(node.receiptId == A && repairIdx == B) targetList.add(node.customerId);
				}
				repairIdx++;
			}
			
			// 접수 창구 순회 -> 감산하며 완료 고객 정비 창구로(창구 번호 A, B이면 targetList에 add)
			for(int i = 1; i <= N; i++) {
				if(receiptCounter[i] == null) {
					receiptIdx = Math.min(receiptIdx, i);
					continue;
				}
				if(--receiptCounter[i].remainTime <= 0) {
					int customerId = receiptCounter[i].customerId;
					
					// 정비 창고가 꽉 찬 경우 -> 대기 큐 삽입
					if(repairIdx == M + 1) {
						pqB.add(new Node(customerId, i, time));
					}
					
					// 자리가 있는 경우 -> 해당 창구에 삽입
					else {
						repairCounter[repairIdx] = new Customer(customerId, repairTime[repairIdx]);
						if(i == A && repairIdx == B) targetList.add(customerId);
					}
					
					receiptCounter[i] = null;	// 접수 창구 완료 처리
				}
			}
			
			// 접수 창구 대기열 털어내기
			while(!pqA.isEmpty() && receiptIdx < N + 1) {
				if(receiptCounter[receiptIdx] == null) {
					int customerId = pqA.poll();
					receiptCounter[receiptIdx] = new Customer(customerId, receiptTime[receiptIdx]);
				}
				receiptIdx++;
			}
			
			// 현재 시각의 고객 접수 창구로 이동
			for(int customerId : customerIdByTime[time]) {
				// 접수 창고가 꽉 찬 경우 -> 대기 큐 삽입
				if(receiptIdx == N + 1) {
					pqA.add(customerId);
				}
				
				// 자리가 있는 경우 -> 해당 창구에 삽입
				else {
					receiptCounter[receiptIdx] = new Customer(customerId, receiptTime[receiptIdx]);
				}
			}
		}
		
		// targetList 순회하며 합산(비어있으면 -1)
		System.out.println(targetList);
		if(targetList.isEmpty()) {
			answer = -1;
		} else {
			for(int customerId : targetList) {
				answer += customerId;
			}
		}
	}
}