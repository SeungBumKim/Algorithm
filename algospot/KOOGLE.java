/**************************************************************
    Problem: KOOGLE (https://algospot.com/judge/problem/read/KOOGLE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 284ms
    Memory: 2.7KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{
	private static int val[] = new int[1001];
	private static int pow[] = new int[1001];
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	

		int i, j;
		
		// make 26^n set
		int tmp, tenPow;
		val[0] = 26;
		pow[0] = 0;
		for(i =1; i<1001; i++){
			tmp = val[i-1] * 26;
			tenPow = 0;
			while(tmp >= 100){
				tmp = tmp / 10;
				tenPow++;
			}
			val[i] = tmp;
			pow[i] = pow[i-1] + tenPow;
		}
		
		int c = Integer.parseInt(br.readLine().trim());
		for(int t =0; t<c; t++){
			int n = Integer.parseInt(br.readLine().trim());
	
			String input;
			String an = null;
			int anCNum = 0, anNNum =0;	
			int t1CNum, t1NNum, t2CNum, t2NNum;;
			int cNum, nNum;
			char cChar, cChar2;
			boolean skip;
			for(i=0; i<n; i++){
				cNum = 0;
				nNum = 0;
				input = br.readLine().trim();
				for(j=0; j<input.length(); j++){
					cChar = input.charAt(j);
					if(input.charAt(j) >= '0' && input.charAt(j) <= '9'){
						nNum++;
					}
					else{
						cNum++;
					}
				}
				
				if(i == 0){
					an = input;
					anCNum = cNum;
					anNNum = nNum;
				}
				else{
					skip = true;
					t1CNum = anCNum;
					t1NNum = anNNum;
					t2CNum = cNum;
					t2NNum = nNum;
					
					if(t1CNum > t2CNum){
						if(t1NNum >= t2NNum){
							// skip
						}
						else{
							t1CNum -= t2CNum;
							t2NNum -= t1NNum;
							
							if(pow[t1CNum-1]+1 < t2NNum){
								skip = false;
							}
							else{
								// skip
							}
						}
					}
					else if(t1CNum == t2CNum){
						if(t1NNum > t2NNum){
							// skip
						}
						else if(t1NNum == t2NNum){
							// calculate dictionary priority
							for(j =0; j<input.length(); j++){
								cChar = input.charAt(j);
								cChar2 = an.charAt(j);
								if(cChar < cChar2){
									skip = false;
									break;
								}
								else if(cChar == cChar2){
									continue;
								}
								else{
									break;
								}
							}
						}
						else{
							skip = false;
						}
					}
					else{
						if(t1NNum <= t2NNum){
							skip = false;
						}
						else{
							t2CNum -= t1CNum;
							t1NNum -= t2NNum;
							
							if(pow[t2CNum-1]+1 >= t1NNum){
								skip = false;
							}
							else{
								// skip
							}
						}
					}
					
					if(skip == false){
						an = input;
						anCNum = cNum;
						anNNum = nNum;
					}
				}
			}
			System.out.println(an);
		}
	}
}