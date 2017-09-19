/**************************************************************
    Problem: 1238 (https://www.acmicpc.net/problem/1238)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1240ms
    Memory: 23656KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{
	private static int map[][];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input[] = br.readLine().trim().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int s = Integer.parseInt(input[2]);
		s--;
		
		map = new int[n][n];
		int fr, to, v;
		for(int mi =0; mi<m; mi++){
			input = br.readLine().trim().split(" ");

			fr = Integer.parseInt(input[0]);
			to = Integer.parseInt(input[1]);
			v = Integer.parseInt(input[2]);
			
			map[fr-1][to-1] = v;
		}
		
		int i,j,k;
		
		for(k=0; k<n; k++){
			for(i=0; i<n; i++){
				if(map[i][k] == 0){
					continue;
				}
				
				for(j=0; j<n; j++){
					if(map[k][j] == 0 || i == j){
						continue;
					}
					
					if(map[i][j] != 0){
						if(map[i][j] >  map[i][k] + map[k][j]){
							map[i][j] = map[i][k] + map[k][j];
						}
					}
					else{
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int an =-1, sum;
		for(i=0; i<n; i++){
			if(map[i][s] != 0 && map[s][i] != 0){
				sum = map[i][s] + map[s][i];
				an = Math.max(sum, an);
			}
		}
		
		System.out.println(an);
	}
}

