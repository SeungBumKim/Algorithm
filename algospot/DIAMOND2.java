/**************************************************************
    Problem: DIAMOND2 (https://algospot.com/judge/problem/read/DIAMOND2)
    User: magicguru
    Language: Java
    Result: Success
    Time: 716ms
    Memory: 2.0KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main{	
	private static boolean map[][] = new boolean[401][401];
	private static int mapSize[] = new int[401];
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int i, j, k, line, size, cnt;
		String input;
		
		/* 테스트 케이스의 수 T */
		int T = Integer.parseInt(br.readLine().trim());
		
		for(i = 0; i<T; i++){
			cnt = 0;
			line = Integer.parseInt(br.readLine().trim());
			for(j = 0; j<line; j++){
				input = br.readLine().trim();
				size = input.length();
				mapSize[j] = size;
				for(k =0; k<size; k++){
					map[j][k] = input.charAt(k) == '#' ? true : false;
				}
			}
			
			for(j = 0; j<line; j++){
				size = mapSize[j];
				for(k =0; k<size; k++){
					if(map[j][k]){
						cnt += find(k, j, size, line);
					}
				}
			}
			
			System.out.println(cnt);
		}		
	}
	
	private static int find(int x, int y, int maxX, int maxY){
		int cnt = 1;
		
		int w = 1;
		while(true){
			if(x -w >= 0 && x + w < maxX && y - w >= 0 && y +w <maxY){
				if(check(x, y, w)){
					cnt++;
				}
				else{
					break;
				}
			}
			else{
				break;
			}
			w++;
		}
		
		return cnt;
	}
	
	private static boolean check(int x, int y, int num){
		boolean isGood =false;
		int i,j,t=0;
		
		for(i=num; i>=0; i--){
			if(i == num){
				for(j=1; j<num+1; j++){
					if(!map[y][x-j] || !map[y][x+j]){
						break;
					}
				}
				
				if(j < num+1){
					break;
				}
			}
			else{
				if(!map[y-t][x] || !map[y+t][x]){
					break;
				}
				
				for(j=1; j<i+1; j++){
					if(!map[y-t][x-j] || !map[y-t][x+j]){
						break;
					}
					if(!map[y+t][x-j] || !map[y+t][x+j]){
						break;
					}
				}
				
				if(j < i+1){
					break;
				}
			}
			t++;
		}
		
		if(i < 0){
			isGood = true;
		}
		
		return isGood;		
	}
}