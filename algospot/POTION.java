/**************************************************************
    Problem: POTION (https://algospot.com/judge/problem/read/POTION)
    User: magicguru
    Language: Java
    Result: Success
    Time: 168ms
    Memory: 2.5KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	private static int list[];
	private static int solList[];
	private static int minList[];
	private static int anList[];
	private static int anTmpList[];
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		int n, i, j, t, gcdVal=0, b, s, minSum, tmpSum, mulVal, tmp, tmpMod;
		String input[];
		for(t =0; t< T; t++){
			n = Integer.parseInt(br.readLine().trim());
			list = new int[n];
			solList = new int[n];
			minList = new int[n];
			anList = new int[n];
			anTmpList = new int[n];
			
			input = br.readLine().trim().split(" ");
			
			for(i =0; i<n; i++){				
				solList[i] = Integer.parseInt(input[i]);
				if(i == 0){
					gcdVal = solList[i]; 
				}
				else{
					if(solList[i] > gcdVal){
						gcdVal = makeGcd(solList[i], gcdVal);
					}
					else{
						gcdVal = makeGcd(gcdVal, solList[i]);
					}
				}
			}
			input = br.readLine().trim().split(" ");
			for(i =0; i<n; i++){				
				list[i] = Integer.parseInt(input[i]);
				minList[i] = solList[i]/gcdVal;
			}
			
			minSum = Integer.MAX_VALUE;
			tmpSum = 0;
			for(i =0; i<n; i++){
				if(list[i] <= minList[i]){
					tmpSum = minList[i] - list[i];
					mulVal = 1;
					anTmpList[i] = tmpSum;
				}
				else{
					tmp = list[i] / minList[i]; 
					tmpMod = list[i] % minList[i];
					if(tmpMod == 0){
						mulVal = tmp;
					}
					else{
						mulVal = tmp+1;
						tmpSum = minList[i] - tmpMod;
					}
					anTmpList[i] = (minList[i] * mulVal) - list[i];
				}
				
				for(j =0; j<n; j++){
					if(i == j){
						continue;
					}
					tmp = minList[j] * mulVal;
					if(tmp < list[j]){
						break;
					}
					else{
						anTmpList[j] = tmp - list[j];
						tmpSum += anTmpList[j];
					}
				}
				
				if(j >= n){
					if(minSum > tmpSum){
						minSum = tmpSum;
						for(j =0; j<n; j++){
							anList[j] = anTmpList[j];
						}
					}
				}
			}
			
			StringBuffer an = new StringBuffer();
			for(i =0; i<n; i++){
				an.append(anList[i]).append(" ");
			}
			System.out.println(an.toString());
		}
	}
	
	
	private static int makeGcd(int a, int b){
		int tmp = a % b; 
		if(tmp == 0){
			return b;
		}
		return makeGcd(b, tmp);
	}
}