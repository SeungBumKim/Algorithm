/**************************************************************
    Problem: BISHOPS (https://algospot.com/judge/problem/read/BISHOPS)
    User: magicguru
    Language: Java
    Result: Success
    Time: 128ms
    Memory: 3.4KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static int n, an;
	private static long table;
	private static long link[];
	private static ArrayList<Node> nodeList = new ArrayList<Node>(); 
	private static char map[][];
	
	private static class Node implements Comparable<Node>{
		int x;
		int y;
		int cnt;
		
		public Node(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		public int getX(){
			return this.x;
		}
		public int getY(){
			return this.y;
		}
		public int getCnt(){
			return this.cnt;
		}

		@Override
		public int compareTo(Node o) {
			if(this.cnt < o.getCnt()){
				return -1;
			}
			else if(this.cnt > o.getCnt()){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int T = Integer.parseInt(br.readLine().trim());
		int i, j, x, y;
		Node node;
		for(int t= 0; t<T; t++){
			n = Integer.parseInt(br.readLine().trim());
			map = new char[n][n];
			link = new long[n*n];
			nodeList.clear();
			for(i =0; i<n; i++){
				map[i] = br.readLine().trim().toCharArray();
			}
			
			an = 0;
			table = 0;
			for(i =0; i<n; i++){
				for(j =0; j<n; j++){
					if(map[i][j] == '*'){
						continue;
					}
					makeBit(i, j);
				}
			}
			Collections.sort(nodeList);
			for(i =0; i<nodeList.size(); i++){
				node = nodeList.get(i);
				y = node.getY();
				x = node.getX();

				if(map[y][x] == '.' && (link[(y*n)+x] & table) == 0){
					map[y][x] = 'b';
					table |= ((long)1 << ((y*n)+x));
					an++;
				}
			}
			
			System.out.println(an);
		}
	}
	
	private static void makeBit(int y, int x){
		long ret = 0;
		
		int gap = 1;
		int cnt = 0;
		int lty, ltx, rty, rtx, lby, lbx, rby, rbx;
		boolean ltskip = false, rtskip = false, lbskip = false, rbskip = false;
		while(!ltskip || !rtskip || !lbskip || !rbskip) {
			// lt check
			lty = y - gap;
			ltx = x - gap;
			if(!ltskip && lty >= 0 && ltx >= 0){
				if(map[lty][ltx] == '*'){
					ltskip =true;
				}
				else{
					ret |= ((long)1 << ((lty * n) + ltx));
					cnt++;
				}
			}
			else{
				ltskip =true;
			}
			
			// rt check
			rty = y - gap;
			rtx = x + gap;
			if(!rtskip && rty >= 0 && rtx < n){
				if(map[rty][rtx] == '*'){
					rtskip =true;
				}
				else{
					ret |= ((long)1 << ((rty * n) + rtx));
					cnt++;
				}
			}
			else{
				rtskip =true;
			}
			
			// lb check
			lby = y + gap;
			lbx = x - gap;
			if(!lbskip && lby < n && lbx >= 0){
				if(map[lby][lbx] == '*'){
					lbskip =true;
				}
				else{
					ret |= ((long)1 << ((lby * n) + lbx));
					cnt++;
				}
			}
			else{
				lbskip =true;
			}
			
			// rb check
			rby = y + gap;
			rbx = x + gap;
			if(!rbskip && rby < n && rbx < n){
				if(map[rby][rbx] == '*'){
					rbskip =true;
				}
				else{
					ret |= ((long)1 << ((rby * n) + rbx));
					cnt++;
				}
			}
			else{
				rbskip =true;
			}			
			gap++;
		}
		
		nodeList.add(new Node(x, y, cnt));
		link[(y*n) + x] = ret;
	}
}