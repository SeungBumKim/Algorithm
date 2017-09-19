/**************************************************************
    Problem: 11478 (https://www.acmicpc.net/problem/11478)
    User: magicguru
    Language: Java
    Result: Success
    Time: 4156ms
    Memory: 51976KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static char[] input;
	private static HashSet<String> set = new HashSet<String>();
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().trim().toCharArray();
		String tmp;
		for(int i=0 ; i<input.length; i++){
			tmp = String.valueOf(input[i]);
			if(!set.contains(tmp)){
				set.add(tmp);
			}
			for(int j=i+1; j<input.length; j++){
				tmp += input[j];
				if(!set.contains(tmp)){
					set.add(tmp);
				}
			}
		}
		System.out.println(set.size());
	}

}