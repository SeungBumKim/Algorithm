/**************************************************************
    Problem: CSBASEBALL (https://algospot.com/judge/problem/read/CSBASEBALL)
    User: magicguru
    Language: Java
    Result: Success
    Time: 317ms
    Memory: 744B
****************************************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T, i, A;
		int our, other;
		String input[];
		
		T = Integer.valueOf(br.readLine().trim());
		for(i =0; i<T; i++){
			input = br.readLine().trim().split(" ");
			our = Integer.valueOf(input[0]);
			other = Integer.valueOf(input[1]);
			
			if(our > other){
				A = 0;
			}
			else if(our == other){
				A = 4;
			}
			else{
				A = other - our + 4;
			}
			
			System.out.println(A);
		}
	}	
}