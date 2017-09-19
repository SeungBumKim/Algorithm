/**************************************************************
    Problem: MORSE (https://algospot.com/judge/problem/read/MORSE))
    User: magicguru
    Language: Java
    Result: Success
    Time: 140ms
    Memory: 1.9KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{
	private static long comb[][] = new long[201][101];
	private static char an[];
	private static long k;
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		String input[];
		int n, m;
		
		makeCom(200, 100);
		
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			k = Integer.parseInt(input[2]);
			
			an = new char[n+m];
			find(n, n+m, k, 0);
			System.out.println(an);
		}
	}
	
	private static void find(int n, int r, long num, int idx){
		if(r == 1){
			if(n == 0){
				an[idx] = 'o';
			}
			else{
				an[idx] = '-';
			}
			return;
		}

		long left;

		if(n <= 0){
			// right only
			an[idx] = 'o';
			find(n, r-1, num-1, idx+1);
		}
		else if(r == n){
			// left only
			an[idx] = '-';
			find(n-1, r-1, num, idx+1);
		}
		else{
			// cal size
			left = n-1 == 0 ? 1 : comb[r-1][n-1];
			if(left == -1 || num <= left){
				an[idx] = '-';
				find(n-1, r-1, num, idx+1);
			}
			else{
				an[idx] = 'o';
				find(n, r-1, num-left, idx+1);
			}
		}
	}	
	
	private static long makeCom(int r, int n){
		if(comb[r][n] > 0 || comb[r][n] == -1){
			return comb[r][n];
		}
		
		if(r == n){
			comb[r][n] = (long)1;
		}
		else if(n == 1){
			comb[r][n] = (long)r;
		}
		else{
			long tmp1 = makeCom(r-1, n-1);
			long tmp2 = makeCom(r-1, n);
			if(tmp1 == -1 || tmp2 == -1){
				comb[r][n] = -1;
			}
			else{
				comb[r][n] = tmp1 + tmp2; 	
			}
			if(comb[r][n] > 1000000000){
				comb[r][n] = -1;
			}
		}
		
		return comb[r][n];
	}
}

