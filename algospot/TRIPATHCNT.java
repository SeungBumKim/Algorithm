/**************************************************************
    Problem: TRIPATHCNG (https://algospot.com/judge/problem/read/TRIPATHCNT)
    User: magicguru
    Language: Java
    Result: Success
    Time: 362ms
    Memory: 1.7KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{
	private static int arr[][] = new int[100][100];
	private static int arrCnt[][] = new int[100][100];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine().trim());
		String input[];
		int i, j, k, tmp, max, an;
		for(i=0; i<t; i++){
			max = -1;
			an = 0;
			int n = Integer.parseInt(br.readLine().trim());
			for(j=0; j<n; j++){
				input = br.readLine().trim().split(" ");
				if(j == 0){
					arr[0][0] = Integer.parseInt(input[0]);
					arrCnt[0][0] = 1;
				}
				else{
					for(k=0; k<j+1; k++){
						tmp = Integer.parseInt(input[k]);
						if(k==0){
							arr[j][k] = arr[j-1][k] + tmp;
							arrCnt[j][k] = 1;
						}
						else if(k==j){
							arr[j][k] = arr[j-1][k-1] + tmp;
							arrCnt[j][k] = 1;
						}
						else{
							if(arr[j-1][k] == arr[j-1][k-1]){
								arr[j][k] = arr[j-1][k] + tmp;
								arrCnt[j][k] = arrCnt[j-1][k] + arrCnt[j-1][k-1];
							}
							else{
								if(arr[j-1][k] > arr[j-1][k-1]){
									arr[j][k] = arr[j-1][k] + tmp;
									arrCnt[j][k] = arrCnt[j-1][k];
								}
								else{
									arr[j][k] = arr[j-1][k-1] + tmp;
									arrCnt[j][k] = arrCnt[j-1][k-1];
								}
							}
						}
					}
				}
			}
			
			for(j=0; j<n; j++){
				if(arr[n-1][j] > max){
					max = arr[n-1][j];
					an = arrCnt[n-1][j];
				}
				else if(arr[n-1][j] == max){
					an += arrCnt[n-1][j];
				}
			}
			
			System.out.println(an);
		}
	}
}