/**************************************************************
    Problem: 1676 (https://www.acmicpc.net/problem/1676)
    User: magicguru
    Language: Java
    Result: Success
    Time: 68ms
    Memory: 8204KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long tt =1;
		int an = 0;
		int N = Integer.parseInt(br.readLine().trim());
		for(int i=1; i<=N; i++){
			tt *= i;
			while(true){
				if(tt % 10 == 0){
					an++;
					tt /= 10;
				}
				else{
					break;
				}
			}
			tt %= 1000000000000L;
		}
		System.out.println(an);
	}
}