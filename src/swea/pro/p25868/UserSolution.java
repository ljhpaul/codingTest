package swea.pro.p25868;

import java.io.*;
import java.util.*;

class UserSolution {
	// Robot class
	static class Robot implements Comparable<Robot> {
		int id;
		int troughput;
		
		public Robot(int id, int troughput) {
			this.id = id;
			this.troughput = troughput;
		}
		
		@Override
		public int compareTo(Robot o) {
			if(this.troughput == o.troughput) {
				return this.id - o.id;	// ID ASC
			}
			return o.troughput - this.troughput;	// 처리량 DESC
		}
	}
	
	// Modify class
	static class Request implements Comparable<Request> {
		int id;
		int grade;
		int time;
		int workload;
		
		public Request(int id, int grade, int time, int workload) {
			this.id = id;
			this.grade = grade;
			this.time = time;
			this.workload = workload;
		}
		
		@Override
		public int compareTo(Request o) {
			if(this.grade == o.grade) {
				return this.time - o.time;	// 접수 시간 ASC
			}
			return o.grade - this.grade;	// 등급 DESC
		}
	}
	
	// Task class
	static class Task implements Comparable<Task> {
		int robotId;
		int requestId;
		int endTime;
		
		public Task(int robotId, int requestId, int endTime) {
			this.robotId = robotId;
			this.requestId = requestId;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Task o) {
			return this.endTime - o.endTime;	// 종료 시간 ASC
		}
	}
	
	// static field
	static int n, m, currTime;
	static PriorityQueue<Robot> runnableRobots;	// 작업 가능 로봇 
	static Queue<Task> runningTasks;	// 수리중
	static Queue<Request> delayed;	// 지연 대기열(우선)
	static PriorityQueue<Request> ready;	// 대기열
	static List<Boolean> isDone;	// 작업 완료 여부
	static List<Integer> TATList;	// 대기시간 합
	
	// 변수 초기화
	void init(int N, int M) {
		// init
		n = N;
		m = M;
		currTime = 0;
		
		runnableRobots = new PriorityQueue<>();
		runningTasks = new ArrayDeque<>();
		delayed = new ArrayDeque<>();
		ready = new PriorityQueue<>();
		isDone = new ArrayList<>();
		TATList = new ArrayList<>();
		
		// 1-인덱싱
		isDone.add(null);
		TATList.add(null);
	}

	// 수리 요청 추가
	void receive(int mTime, int mId, int mWorkload, int mGrade) {
		renewTask();
		Request request = new Request(mId, mGrade, mTime, mWorkload);
		if(runnableRobots.isEmpty()) {
			ready.add(request);
		} else {
			Robot robot = runnableRobots.poll();
			Task task = new Task(robot.id, request.id, currTime + request.time);
			runningTasks.add(task);
		}
	}

	// 로봇 추가
	void add(int mTime, int rId, int mThroughput) {
		runnableRobots.add(new Robot(rId, mThroughput));
	}

	// 로봇 삭제 + 수리 요청 ID 반환
	int remove(int mTime, int rId) {
		renewTask();
		for(Robot robot : runnableRobots) {
			if(robot.id == rId) {
				runnableRobots.remove(robot);
				return -1;
			}
		}
		for(Task task : runningTasks) {
			int robotId = task.robotId;
			int requestId = task.requestId;
			if(robotId == rId) {
				runningTasks.remove(task);
				return requestId;
			}
		}
		return 0;
	}

	// 대기 시간 합 반환
	int evaluate(int mTime, int mGrade) {
		renewTask();
		
		return 0;
	}
	
	// 작업 완료 여부 및 시간 갱신
	void renewTask() {
		
	}
}
