package baekjoon.roadmap.etc.boj3015;

import java.io.*;
import java.util.*;

public class Main {
	// custom class
    static class Node {
		long value;
		long count;

		Node(long value) {
			this.value = value;
			this.count = 1;
		}

		Node(long value, long count) {
			this.value = value;
			this.count = count;
		}

		@Override
		public String toString() {
			return "" + value;
		}
	}

	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// main
	public static void main(String[] args) throws IOException {
		// init
		long answer = 0;
		int n = Integer.parseInt(br.readLine());
		ArrayDeque<Node> stk = new ArrayDeque<>();

		stk.add(new Node(Long.parseLong(br.readLine())));

		// get answer
		for(int i=0; i<n-1; i++) {
			long now = Long.parseLong(br.readLine());
			long debug = 0;
			boolean flag = false;

			while(!stk.isEmpty()) {
				Node tmp = stk.pollLast();
				if(tmp.value > now) {
					stk.add(new Node(tmp.value, tmp.count));
					answer++;
					debug++;
					break;
				} else if(tmp.value == now) {
					stk.add(new Node(tmp.value, tmp.count));
					if(stk.size() == tmp.count) {
						answer += tmp.count;
						debug += tmp.count;
					} else {
						answer += tmp.count + 1;
						debug += tmp.count + 1;
					}
					flag = true;
					break;
				} else {
					answer++;
					debug++;
				}
			}

			if(!stk.isEmpty() && stk.peekLast().value == now) {
				stk.add(new Node(now, stk.peekLast().count + 1));
			} else {
				stk.add(new Node(now));
			}

			// System.out.println("[debug] " + stk + " (" + debug + ")");
		}

		// output
		System.out.println(answer);
	}

}
/*
2 4 1 2 2 5 1
4 (1) -> 2를 pop 후 4와 비교한 뒤 작기 때문에 +1 후 버림
4 1 (1) -> pop(4) 크기 때문에 +1 후 넣고 1 넣기
4 2 (2) -> pop
4 2 2 (2)
5 (3)
5 1 (1)

(2, 4)
(4, 1)
(4, 2) (1, 2)
(4, 2) (2, 2)
(4, 5) (2, 5) (2, 5)
(5, 1)

4 1 2 1 5
4 1 (1)
4 2 (2)
4 2 1 (1)
5 (3)

(4, 1)
(4, 2) (1, 2)



5
4
1
2
1
5

6
1
1
1
1
1
1

4
1
2
3
4

4
4
3
2
1

10
10
9
1
3
8
6
7
8
5
8

[9] (10, 9)
[1] (9, 1)
[3] (9, 3) (1, 3)
[8] (9, 8) (3, 8)
[6] (
[7]
[8]
[5]
[8]

 */