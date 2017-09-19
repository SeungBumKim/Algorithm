/**************************************************************
    Problem: 9328 (https://www.acmicpc.net/problem/9328)
    User: magicguru
    Language: Java
    Result: Success
    Time: 288ms
    Memory: 31072KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Main {
	private static char map[][] = new char[100][100];
	private static boolean history[][] = new boolean[100][100];
	private static boolean history2[][];
	private static boolean useKey[] = new boolean[26];
	private static Queue<Character> keyList = new LinkedList<Character>();
	private static ArrayList<Lo> arr[] = new ArrayList[26];
	private static int an, w, h;

	private static class Lo{
		int h;
		int w;
		
		public Lo(int h, int w){
			this.h = h;
			this.w = w;
		}
		public int getW(){
			return this.w;
		}
		public int getH(){
			return this.h;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		int i, j;
		char tmpChar;
		String input[], in;
		
		for(i=0; i<26; i++){
			arr[i] = new ArrayList<Lo>();
		}
		
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			h = Integer.parseInt(input[0]);
			w = Integer.parseInt(input[1]);
			
			keyList.clear();
			for(i =0; i<26; i++){
				arr[i].clear();
				useKey[i] = false;
			}
			
			an = 0;
			for(i=0; i<h; i++){				
				in = br.readLine().trim();
				for(j=0; j<w; j++){
					tmpChar = in.charAt(j);
					map[i][j] = tmpChar;
					
					history[i][j] = false;
					if(tmpChar >= 'A' && tmpChar <= 'Z'){
						arr[tmpChar-'A'].add(new Lo(i, j));
					}
				}
			}
			
			// process key
			in = br.readLine().trim();
			
			if(in.length() >= 1 && in.charAt(0) != '0'){
				for(i =0; i<in.length(); i++){
					tmpChar = in.charAt(i);
					open(tmpChar);
				}
			}
			
			// full search
			for(j=0; j<w; j++){
				find(0, j);
				find(h-1, j);
			}
			for(i=1; i<h-1; i++){
				find(i, 0);
				find(i, w-1);
			}
			
			System.out.println(an);
		}
	}
	
	private static void open(char key){	
		if(useKey[key-'a'] == false && arr[key-'a'].size() > 0){
			useKey[key-'a'] = true;
			Lo tmp;
			for(int i=0; i<arr[key-'a'].size(); i++){
				tmp = arr[key-'a'].get(i);
				map[tmp.getH()][tmp.getW()] = '.';
				history2 = new boolean[h][w];
				if(check(tmp.getH(), tmp.getW())){
					find(tmp.getH(), tmp.getW());
				}
			}
		}
	}
	
	private static boolean check(int i, int j){
		boolean isOk = false;
		
		if(history2[i][j]){
			return false;
		}
		
		if((map[i][j] == '*') || (map[i][j] >= 'A' && map[i][j] <= 'Z')){
			return false;
		}
		
		if(history[i][j] == true || i <= 0 || i >= h-1 || j <= 0 || j >= w-1){
			return true;
		}
		
		history2[i][j] =true;
		
		// move left
		if(j > 0){
			isOk = check(i, j-1);
		}
		
		// move right
		if(isOk == false && j < w-1){
			isOk = check(i, j+1);
		}
		
		// move top
		if(isOk == false && i > 0){
			isOk = check(i-1, j); 
		}
		
		// move bottom
		if(isOk == false && i < h-1){
			isOk = check(i+1, j);
		}
		
		return isOk;
	}
	
	private static void find(int i, int j){		
		if(history[i][j] == true){
			return;
		}
		
		if(map[i][j] == '$'){
			an++;
			map[i][j] = '.';
		}
		else if(map[i][j] >= 'a' && map[i][j] <= 'z'){
			open(map[i][j]);
			map[i][j] = '.';
		}
		else if(map[i][j] >= 'A' && map[i][j] <= 'Z'){
			return;
		}
		else if(map[i][j] == '*'){
			return;
		}
		history[i][j] = true;
		
		// move left
		if(j > 0){
			find(i, j-1);
		}
		
		// move right
		if(j < w-1){
			find(i, j+1);
		}
		
		// move top
		if(i > 0){
			find(i-1, j); 
		}
		
		// move bottom
		if(i < h-1){
			find(i+1, j);
		}
	}
}