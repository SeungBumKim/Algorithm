/**************************************************************
    Problem: ITES (https://algospot.com/judge/problem/read/ITES)
    User: magicguru
    Language: Java
    Result: Success
    Time: 14540ms
    Memory: 1.4KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int T = Integer.parseInt(br.readLine().trim());
		int K, N, i, an, tmp, sum;
		long be, tmp1, mod = (long)Math.pow(2, 32);
		String input[];
		Queue<Integer> q = new LinkedList<Integer>();
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			K = Integer.parseInt(input[0]);
			N = Integer.parseInt(input[1]);
			
			i =0;
			an = 0;
			sum = 0;
			q.clear();
			q.add(1983);
			be = 1983;
			sum = 1983;
			while(!q.isEmpty()){
				if(i >= N){
					break;
				}
				
				if(sum == K){
					an++;
					sum -= q.poll();
				}
				else if(sum > K){
					sum -= q.poll();
					if(sum == K){
						an++;
					}
					else if(sum > K){
						while(!q.isEmpty()){
							sum -= q.poll();
							if(sum == K){
								an++;
							}
							else if(sum < K){
								break;
							}
						}
					}
				}
				
				tmp1 = (be * 214013 + 2531011) % mod;
				tmp = (int)(tmp1%10000) +1;
				sum += tmp;				
				q.add(tmp);
				be = tmp1;
				
				i++;
			}
			System.out.println(an);
		}		
	}
}