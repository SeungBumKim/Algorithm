/**************************************************************
    Problem: NEWBUS (https://algospot.com/judge/problem/read/NEWBUS)
    User: magicguru
    Language: Java
    Result: Success
    Time: 970ms
    Memory: 4.5KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main{
	private static ArrayList<Node> arr[] = new ArrayList[101];
	private static int hi[] = new int[101];
	private static int hiCnt[] = new int[101];
	private static PriorityQueue<Node> q = new PriorityQueue<Node>();
	
	private static class Node implements Comparable<Node>{
		int st;
		int end;
		long hi1;
		long hi2;
		int w;
		
		public Node(int st, int end, int w){
			this.st = st;
			this.end = end;
			this.w = w;
			this.hi1 = 0;
			this.hi2 = 0;
		}
		
		public Node(int st, int w, long hi1, long hi2){
			this.st = st;
			this.w = w;
			this.hi1 = hi1;
			this.hi2 = hi2;
		}
		
		public int getSt(){
			return this.st;
		}
		public int getEnd(){
			return this.end;
		}
		public int getW(){
			return this.w;
		}
		public long getHi1(){
			return this.hi1;
		}
		public long getHi2(){
			return this.hi2; 
		}
		public boolean isCircle(int idx){
			boolean ret =false;
			long tmp;
			if(idx < 50){
				tmp = (long)1 << idx;
				if((this.hi1 & tmp) == tmp){
					ret = true;
				}
			}
			else{
				tmp = (long)1 << (idx-50);
				if((this.hi2 & tmp) == tmp){
					ret = true;
				}
			}
			return ret;
		}

		@Override
		public int compareTo(Node arg0) {
			if(this.w > arg0.getW()){
				return 1;
			}
			else if(this.w < arg0.getW()){
				return -1;
			}
			else{
				return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		int crossN, lineM, pairQ;
		String input[];
		int i, j, len;
		int st, end, w;
		int first, last;
		Node node, newNode;
		String an;
		long hi1, hi2;
		long tmphi1, tmphi2;
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			crossN = Integer.parseInt(input[0]);
			lineM = Integer.parseInt(input[1]);
			pairQ = Integer.parseInt(input[2]);
			
			for(i =0; i< crossN+1; i++){
				if(arr[i] == null){
					arr[i] = new ArrayList<Node>();
				}
				else{
					arr[i].clear();
				}
			}
			
			for(i =0; i< lineM; i++){
				input = br.readLine().trim().split(" ");
				st = Integer.parseInt(input[0]);
				end = Integer.parseInt(input[1]);
				w = Integer.parseInt(input[2]);
				
				arr[st].add(new Node(st, end, w));
				arr[end].add(new Node(end, st, w));
			}
			
			for(i =0; i<pairQ; i++){
				input = br.readLine().trim().split(" ");
				first = Integer.parseInt(input[0]);
				last = Integer.parseInt(input[1]);
				
				// init
				for(j =0; j< crossN+1; j++){
					hi[j] = 0;
					hiCnt[j] = 0;
				}				
				q.clear();
				
				// input first values

				tmphi1 = 0;
				tmphi2 = 0;
				if(first < 50){
					tmphi1 |= ((long)1 << first);
				}
				else{
					tmphi2 |= ((long)1 << (first-50));							
				}
				q.add(new Node(first, 0, tmphi1, tmphi2));

				
				// algorithm
				while(!q.isEmpty()){
					node = q.poll();
					st = node.getSt();
					w = node.getW();
					hi1 = node.getHi1();
					hi2 = node.getHi2();
					
					if(st == last){
						if(hiCnt[st] > 0){
							if(w == hi[st]){
								hiCnt[st]++;
							}
							else if(w < hi[st]){
								hi[st] = w;
								hiCnt[st] = 1;
							}
						}
						else{
							hi[st] = w;
							hiCnt[st] = 1;
						}
						continue;
					}
					
					if(hiCnt[st] > 0){
						if(w == hi[st]){
							hiCnt[st]++;
						}
						else if(w < hi[st]){
							hi[st] = w;
							hiCnt[st] = 1;
						}
						else{
							continue;
						}
					}
					else{
						hi[st] = w;
						hiCnt[st] = 1;
					}
					
					len = arr[st].size();
					for(j=0; j<len; j++){
						newNode = arr[st].get(j);
						end = newNode.getEnd();
												
						if(node.isCircle(end)){
							continue;
						}						
						
						if(end < 50){
							tmphi1 = hi1 | ((long)1 << end);
							tmphi2 = hi2;
						}
						else{
							tmphi1 = hi1;
							tmphi2 = hi2  | ((long)1 << (end-50));							
						}
						q.add(new Node(end, w + newNode.getW(), tmphi1, tmphi2));
					}
				}
				
				if(hiCnt[last] == 0){
					an = "no";
				}
				else if(hiCnt[last] == 1){
					an = "only";
				}
				else{
					an = "many";
				}
				System.out.println(an);
			}
		}
	}
}