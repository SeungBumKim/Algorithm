/**************************************************************
    Problem: MATCHFIX (https://algospot.com/judge/problem/read/MATCHFIX)
    User: magicguru
    Language: Java
    Result: Success
    Time: 168ms
    Memory: 3.1KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	private static int n, m, an, zCnt, max;
	private static int win[];
	private static int winTmp[];
	private static ArrayList<Integer> man[];
	private static ArrayList<Integer> game[];
	private static ArrayList<Integer> game2[];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		String input[];
		int l, i, j, t1, t2, st;
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			
			man = new ArrayList[n];
			game = new ArrayList[m];
			game2 = new ArrayList[m];
			
			input = br.readLine().trim().split(" ");
			win = new int[n];
			winTmp = new int[n];
			max = 0;
			for(i=0; i<n; i++){
				win[i] = Integer.parseInt(input[i]);
				man[i] = new ArrayList<Integer>();
				if(i > 0){
					max = Math.max(max, win[i]);
				}
			}
			
			if(m == 0){
				for(i=1; i<n; i++){
					if(win[0] <= win[i]){
						break;
					}
				}
				if(i >= n){
					System.out.println(win[0]);
				}
				else{
					System.out.println(-1);
				}
				continue;
			}
			
			zCnt = 0;
			for(i=0; i<m; i++){
				game[i] = new ArrayList<Integer>();
				game2[i] = new ArrayList<Integer>();
				input = br.readLine().trim().split(" ");
				t1 = Integer.parseInt(input[0]);
				t2 = Integer.parseInt(input[1]);
				
				if(t1 == 0 || t2 == 0){
					zCnt++;
				}
				game[i].add(t1);
				game[i].add(t2);
			}

			an = -1;
			st = 0;
			if(max >= win[0]){
				st = max - win[0] + 1;
			}
			for(l =st; l<=zCnt; l++){
				for(i=0; i<n; i++){
					man[i].clear();
					if(i == 0){
						winTmp[i] = l;
					}
					else{
						winTmp[i] = (win[0] + l) - win[i] - 1;
					}
				}
				
				for(i=0; i<m; i++){
					game2[i].clear();
					for(j=0; j<game[i].size(); j++){
						game2[i].add(game[i].get(j));
					}
				}
				
				for(i=0; i<m; i++){
					if(find(i, 0) == -1){
						break;
					}
				}
				
				if(i >= m){
					an = win[0] + l;
					break;
				}
			}
			
			System.out.println(an);
		}
	}
	
	private static int find(int f, int mask){
		int ret = -1;
		
		int i, j, t, f1;
		boolean b = false, b1 =false;
		for(i=0; i<game2[f].size(); i++){
			t = game2[f].get(i);			
			
			if((mask & (1 << t)) == (1 << t)){
				continue;
			}
			
			if(winTmp[t] > 0){
				winTmp[t]--;
				ret = 1;
				b = true;
				man[t].add(f);
				break;
			}
			else{
				for(j=0; j<man[t].size(); j++){
					if(find(man[t].get(j), (mask | (1 << t))) != -1){
						b1 = true;
						break;
					}
				}
				if(b1){
					ret = 1;
					f1 = man[t].get(j);
					game2[f1].add(t);
					man[t].remove(j);
					b = true;
					man[t].add(f);
					break;
				}
			}
		}
		
		if(b){
			game2[f].remove(i);
		}
		return ret;
	}
}