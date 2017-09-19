/**************************************************************
    Problem: JAEHASAFE (https://algospot.com/judge/problem/read/JAEHASAFE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 419ms
    Memory: 2.7KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	private static long alpha[] = new long[256];
	private static long val[] = new long[10000];
	private static long pos[] = new long[10000];
	private static long pos2[] = new long[10000];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int a=0; a<26; a++){
			alpha['a'+a] = a+1;
			alpha['A'+a] = a+1+26;
		}
		
		int T = Integer.parseInt(br.readLine().trim());
		int i,j,k, len, st1, n, st2, st;
		long an, lenSum, tmpVal;
		long tmpSum;
		String tmp;
		for(int t =0; t<T; t++){
			n = Integer.parseInt(br.readLine().trim());			
			
			tmp = br.readLine().trim();
			len = tmp.length();
			tmpSum =0;
			lenSum = 0;
			for(i=0; i<len; i++){
				tmpVal = alpha[tmp.charAt(i)];
				pos[i] = tmpVal;
				tmpSum += (tmpVal * ((long)i+1));
				lenSum += tmpVal;
			}

			j =0;
			for(i =len-1; i>=0; i--){
				val[j++] = tmpSum;
				tmpVal = pos[i];
				tmpSum -= (tmpVal * ((long)len-1));
				tmpSum += (lenSum-tmpVal);
			}
			
			an = 0;
			st = 0;
			for(i =1; i<n+1; i++){
				tmp = br.readLine().trim();
				tmpSum = 0;
				for(j=0; j<len; j++){
					tmpVal = alpha[tmp.charAt(j)];
					pos2[j] = tmpVal;
					tmpSum += (tmpVal * ((long)j+1));
				}

				st2 = st;				
				if(i%2 == 1){
					for(j =0; j<len; j++){
						if(tmpSum == val[st2]){
							st1 = ((st2 == 0) ? 0 : len - st2);
							for(k =0; k<len; k++){
								if(pos[st1++] != pos2[k]){
									break;
								}
								if(st1 >= len){
									st1 = 0;
								}							
							}
							if(k >= len){
								break;
							}
						}
						st2++;
						if(st2 >= len){
							st2 = 0;
						}
					}
					
					if(st2 < st){
						an += (len-1)-st+(st2+1);
					}
					else if(st2 == st){
						an += len;
					}
					else{
						an += (st2 - st);
					}
					
					st = st2;
				}
				else{
					for(j =0; j<len; j++){
						if(tmpSum == val[st2]){
							st1 = ((st2 == 0) ? 0 : len - st2);
							for(k =0; k<len; k++){
								if(pos[st1++] != pos2[k]){
									break;
								}
								if(st1 >= len){
									st1 = 0;
								}							
							}
							if(k >= len){
								break;
							}
						}
						st2--;
						if(st2 < 0){
							st2 = len -1;
						}
					}
					
					if(st2 < st){
						an += (st - st2);
					}
					else if(st2 == st){
						an += len;
					}					
					else{
						an += (len-1)-st2+(st+1);
					}
					st = st2;
				}
			}
			
			System.out.println(an);
		}
	}
}