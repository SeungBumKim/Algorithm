/**************************************************************
    Problem: 2316 (https://www.acmicpc.net/problem/2316)
    User: magicguru
    Language: Java
    Result: Success
    Time: 192ms
    Memory: 10156KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
	private static int N,P;
	private static int parent[], c[][], f[][];
	private static boolean go[];	
	private static ArrayList<Integer> list[];
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input[] = br.readLine().trim().split(" ");
		N = Integer.parseInt(input[0]);
		P = Integer.parseInt(input[1]);
		
		c = new int[N+1][N+1];
		f = new int[N+1][N+1];
		go = new boolean[N+1];
		list = new ArrayList[N+1];
		
		int i, s, e;
		for(i=1; i<N+1; i++){
			list[i] = new ArrayList<Integer>();
		}
		
		for(i =0; i<P; i++){
			input = br.readLine().trim().split(" ");
			s = Integer.parseInt(input[0]);
			e = Integer.parseInt(input[1]);
			
			c[s][e]++;
			c[e][s]++;
			list[s].add(e);
			list[e].add(s);
		}
		
		int an = 0;
		while(true){
			if(ntflow()){
				an++;
			}
			else{
				break;
			}
		}
		
		System.out.println(an);
	}
	
	private static boolean ntflow(){
		boolean ret = false;
		
		parent = new int[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		parent[1] = -1;
		
		int idx;
		boolean find = false;
		
		// make dfx - find way
		while(!queue.isEmpty()){
			idx = queue.poll();
			for(int val: list[idx]){
				if(c[idx][val] - f[idx][val] > 0 && parent[val] == 0 && go[val] == false){
					queue.add(val);
					parent[val] = idx;
					
					if(val == 2){
						find = true;
						break;
					}
				}
			}
			if(find){
				break;
			}
		}
		
		
		if(find){
			// find min flow
			int avail, pa, val = 2, minVal = Integer.MAX_VALUE;
			while(parent[val] != -1){
				pa = parent[val];
				avail = c[pa][val] - f[pa][val];
				minVal = Math.min(avail, minVal);
				val = pa;
				if(val != 1 && val != 2){
					go[val] = true;
				}
			}
			
			// flow add
			val = 2;
			while(parent[val] != -1){
				pa = parent[val];
				f[pa][val] += minVal;
				f[val][pa] -= minVal;
				val = pa;
			}			
			
			ret = true;
		}

		return ret;
	}
}