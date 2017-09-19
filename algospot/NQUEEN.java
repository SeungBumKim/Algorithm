/**************************************************************
    Problem: NQUEEN (https://algospot.com/judge/problem/read/NQUEEN)
    User: magicguru
    Language: Java
    Result: Success
    Time: 378ms
    Memory: 1.4KB
****************************************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
	private static boolean row[] = new boolean[13];
	private static boolean col[] = new boolean[13];
	private static boolean lLine[] = new boolean[25];
	private static boolean rLine[] = new boolean[25];
	
	public static void main(String[] args) throws Exception{
		int T, n, answer;
		
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.valueOf(br.readLine().trim());
		for(int i=0; i<T; i++){
			n = Integer.valueOf(br.readLine().trim());

			answer = 0;
			for(int j =0; j<(n*2)-1; j++){
				if(j < n){
					row[j] = false;
					col[j] = false;
				}
				lLine[j] = false;
				rLine[j] = false;
			}
			
			answer = findQueen(0, n);
			
			System.out.println(answer);
		}
		
	}
	
	private static int findQueen(int y, int n){
		int ret = 0;
		int rN = 0, lN = 0;
		
		if(y >= n)
			return 1;

		for(int i =0; i<n; i++){
			lN = i+y;
			rN = y-i;
			if(rN < 0){
				rN = n + (-rN);
			}
			
			if(row[y] || col[i] || lLine[lN] || rLine[rN]){
				continue;
			}			
		
			row[y] = true;
			col[i] = true;
			lLine[lN] = true;
			rLine[rN] = true;
			
			ret += findQueen(y+1, n);

			col[i] = false;
			lLine[lN] = false;
			rLine[rN] = false;
			row[y] = false;
		}
		
		return ret;
	}
}