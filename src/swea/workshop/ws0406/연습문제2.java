package swea.workshop.ws0406;

import java.io.*;
import java.util.*;

class 연습문제2 {
	// main
    public static void main(String[] args) throws IOException {
        // init
    	int n = 6;
    	int[] func = new int[n + 1];
    	if(n >= 1) func[1] = 2;
    	if(n >= 2) func[2] = 5;
    	
    	// solve
    	for(int i = 3; i <= n; i++) {
    		func[i] = func[i - 1] * 2 + func[i - 2];
    	}
    	int answer = func[n];
    	
        // output
        System.out.println("f(" + n + ") = " + answer);
    }
}