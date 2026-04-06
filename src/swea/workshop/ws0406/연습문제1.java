package swea.workshop.ws0406;

import java.io.*;
import java.util.*;

class 연습문제1 {
	// main
    public static void main(String[] args) throws IOException {
        // init
    	int n = 8;
    	int[] yellow = new int[n + 1];
    	int[] blue = new int[n + 1];
    	if(n >= 1) {
    		yellow[1] = 1;
        	blue[1] = 1;
    	}
    	
    	// solve
    	for(int i = 2; i <= n; i++) {
    		yellow[i] = yellow[i - 1] + blue[i - 1];
    		blue[i] = yellow[i - 1];
    	}
    	int answer = yellow[n] + blue[n];

        // output
    	System.out.println("f(" + n + ") = " + answer);
    }
}