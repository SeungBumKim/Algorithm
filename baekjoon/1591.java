/**************************************************************
    Problem: 1591 (https://www.acmicpc.net/problem/1591)
    User: magicguru
    Language: Java
    Result: Success
    Time: 384ms
    Memory: 12248KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main{
	private static int N, M, loop;
	private static int list[][];
	private static int subList[][];
	private static int cnt[][];
	private static int incnt[], outcnt[];
	private static ArrayList<Integer> graph[];
	private static LinkedList<Integer> an = new LinkedList<Integer>();
	private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int  i, j;
		loop = N-M+1;
		list = new int[loop][M];
		cnt = new int[N+1][N+1];
		incnt = new int[N+1];
		outcnt = new int[N+1];
		subList = new int[N+1][M-1];
		graph = new ArrayList[N+1];
		
		for(i =0; i<N+1; i++){
			graph[i] = new ArrayList<Integer>();
		}
		
		int tmpfhash[], tmpthash[];
		int tmpfh, tmpth, idx = 0, fidx, tidx;
		for(i =0; i<loop; i++){
			st = new StringTokenizer(br.readLine().trim());
			tmpfhash = new int[M-1];
			tmpthash = new int[M-1];
			for(j =0; j<M; j++){
				list[i][j] = Integer.parseInt(st.nextToken());
				if(j < M-1){
					tmpfhash[j] = list[i][j]; 
				}
				if(j > 0){
					tmpthash[j-1] = list[i][j];
				}				
			}
			tmpfh = Arrays.hashCode(tmpfhash);
			tmpth = Arrays.hashCode(tmpthash);		
			
			if(map.containsKey(tmpfh)){
				fidx = map.get(tmpfh);
			}
			else{
				fidx = idx++;
				map.put(tmpfh, fidx);
				subList[fidx] = tmpfhash;
			}
			if(map.containsKey(tmpth)){
				tidx = map.get(tmpth);
			}
			else{
				tidx = idx++;
				map.put(tmpth, tidx);
				subList[tidx] = tmpthash;
			}
			
			if(cnt[fidx][tidx] == 0){
				graph[fidx].add(tidx);
			}
			cnt[fidx][tidx]++;

			incnt[tidx]++;
			outcnt[fidx]++;
		}
		
		boolean in = false;
		for(j =0; j<N+1; j++){
			if(outcnt[j] > incnt[j]){
				in = true;
				find(j);
				break;
			}
		}
	
		if(in == false){
			// run anything
			find(0);
		}
		
		boolean isFirst = true;
		StringBuffer sb = new StringBuffer();
		for(int anIdx: an){
			if(isFirst){
				isFirst = false;
				for(int k=0; k<M-1; k++){
					sb.append(subList[anIdx][k] + " ");
				}
			}
			else{
				sb.append(subList[anIdx][M-2] + " ");
			}
		}
		System.out.println(sb.toString());
	}
	
	private static void find(int idx){		
		for(int i: graph[idx]){
			while(cnt[idx][i] > 0){
				cnt[idx][i]--;
				find(i);
			}
		}
		an.addFirst(idx);
	}
}