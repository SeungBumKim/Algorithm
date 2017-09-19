/**************************************************************
    Problem: CHRISTMAS (https://algospot.com/judge/problem/read/CHRISTMAS)
    User: magicguru
    Language: Java
    Result: Success
    Time: 845ms
    Memory: 2.0KB
****************************************************************/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Main{
	private static int arr[] = new int[100001];
	private static long arrSum[] = new long[100001];
	private static int pSum[] = new int[100001];
	private static long pCnt[];
	private static int hi[];
	private static long an1, an2;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedInputStream bf = new BufferedInputStream(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int T = Integer.parseInt(br.readLine().trim());
		int boxN, childK, i, j;
		long sum, tmp;
		String input[];
		StringTokenizer st;
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			boxN = Integer.parseInt(input[0]);
			childK = Integer.parseInt(input[1]);
			
			//input = br.readLine().trim().split(" ");
			st = new StringTokenizer(br.readLine());
			
			sum = 0;
			an1 =0;
			an2 =0;
			int lastIdx = -2;
			
			pCnt = new long[childK];
			hi = new int[childK];
			pCnt[0] = 1;
			for(i =0; i<boxN; i++){
				arr[i] = Integer.parseInt(st.nextToken());//Integer.parseInt(input[i]);
				
				if(i == 0){
					arrSum[i] = arr[i];
				}
				else{
					arrSum[i] = arrSum[i-1] + arr[i];
				}
				pSum[i] = (int)(arrSum[i] % childK);
				
				if(pCnt[pSum[i]] > 0){
					if((lastIdx == -2) || ((hi[pSum[i]] ) >= lastIdx)){
						an2++;						
						lastIdx = i;
					}					
				}

				pCnt[pSum[i]]++;
				hi[pSum[i]] = i;
			}

			for(i=0; i<childK; i++){
				if(pCnt[i] > 1){
					an1 += ((pCnt[i] * (pCnt[i] -1 ))/2);
				}
			}

			System.out.println(an1%20091101 +" " +an2);
		}
	}
}