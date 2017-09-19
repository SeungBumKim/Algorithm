/**************************************************************
    Problem: PREFIX (https://algospot.com/judge/problem/read/PREFIX)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1574ms
    Memory: 2.4KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{
	private static String arr[] = new String[3000];
	private static int arrLen[] = new int[3000];
	//private static String history[] = new String[3000];
	private static int hiCnt[][] = new int[3000][200];
	private static String an[] = new String[200];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		int i, j, n, mLen;
		char curCh =0, tmpCh;
		String input[];
		String curStr = "";
		String maxStr = "";
		boolean isSame;
		int curCnt=0, maxCnt = 0;
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			n = Integer.parseInt(input[0]);
			mLen = Integer.parseInt(input[1]);
			
			for(i =0; i<n; i++){
				arr[i] = br.readLine().trim();
				arrLen[i] = arr[i].length();
			//	history[i] = "";
				hiCnt[i][0] = 0;
			}

			StringBuffer sb = new StringBuffer();
			for(i =0; i<mLen; i++){
				curCh = 0;
				curStr = "";
				maxStr = "";
				curCnt = 0;
				maxCnt = 0;
				
				
				for(j =0; j<n; j++){
					isSame = false;
					if(arrLen[j] > i){
						tmpCh = arr[j].charAt(i);						
						//history[j] += tmpCh;
						if(curStr.length() == 0){
							curStr = arr[j].substring(0, i+1);
							curCnt = 1;
							curCh = tmpCh;
						}
						else{
							if(i == 0){
								isSame = (curCh == tmpCh) ? true : false; 
							}
							else{
								if((hiCnt[j-1][i-1] < hiCnt[j][i-1]) && (curCh == tmpCh)){
									isSame = true;
								}
								else{
									isSame = false;
								}
							}
							
							if(isSame){
								curCnt++;
							}
							else{
								if(curCnt > maxCnt){
									maxStr = curStr;
									maxCnt = curCnt;
								}
								curStr = arr[j].substring(0, i+1);
								curCnt = 1;
								curCh = tmpCh;
							}
						}
					}
					else{
						if(curStr.length() > 0 && curCnt > maxCnt){
							maxStr = curStr;
							maxCnt = curCnt;
						}
						curCh = 0;
						curCnt = 1;
						curStr = "";
					}
					
					hiCnt[j][i] = curCnt;
				}
				if(curCnt > maxCnt){
					maxStr = curStr;
					maxCnt = curCnt;
				}
				System.out.println(maxStr);				
			}
		}
	}
}