package baekjoon.roadmap.etc.boj2578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// init
		int[][] board = new int[5][5];
		boolean[][] checked = new boolean[5][5];
		int[] nums = new int[25];
		int bingo = 0;
		int cnt = 0;

		// input
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// get cnt
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				nums[5*i + j] = Integer.parseInt(st.nextToken());
			}
		}

		// check bingo
		outer:
		for(int num : nums) {
			cnt++;
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(board[i][j] == num) {
						checked[i][j] = true;

						// '-'
						int tmp = 0;
						for(int d=0; d<5; d++) {
							if(checked[i][d]) tmp++;
						}
						if(tmp == 5) bingo++;

						// '|'
						tmp = 0;
						for(int d=0; d<5; d++) {
							if(checked[d][j]) tmp++;
						}
						if(tmp == 5) bingo++;

						// '/'
						if(i+j == 4) {
							tmp = 0;
							for (int d = 0; d < 5; d++) {
								if (checked[d][4 - d]) tmp++;
							}
							if (tmp == 5) bingo++;
						}

						// '\'
						if(i == j) {
							tmp = 0;
							for(int d=0; d<5; d++) {
								if(checked[d][d]) tmp++;
							}
							if(tmp == 5) bingo++;
						}

//						System.out.println("현재 수 : " + num + ", 빙고 수 : " + bingo);
						// 3 bingo
						if(bingo >= 3) break outer;
					}
				}
			}
		}


		// output
		System.out.println(cnt);
	}

}