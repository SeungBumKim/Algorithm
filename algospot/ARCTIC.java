/**************************************************************
    Problem: ARCTIC (https://algospot.com/judge/problem/read/ARCTIC)
    User: magicguru
    Language: Java
    Result: Success
    Time: 492ms
    Memory: 3.9KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main{
	private static ArrayList<Node> nodes[] = new ArrayList[101];
	private static PriorityQueue<Node> queue = new PriorityQueue<Node>(101);
	private static Point point[] = new Point[101];
	private static boolean from[] = new boolean[101];
	private static double min;
	
	private static class Node implements Comparable<Node>{
		int from;
		int to;
		double w;
		
		public Node(int from, int to, double w){
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		public int getFrom(){
			return this.from;
		}
		public int getTo(){
			return this.to;
		}
		public double getW(){
			return this.w;
		}

		@Override
		public int compareTo(Node o) {
			if(this.w > o.getW()){
				return 1;
			}
			else if(this.w < o.getW()){
				return -1;
			}
			else{
				return 0;
			}
		}
	}
	
	private static class Point{
		double x;
		double y;
		
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		public double getX(){
			return this.x;
		}
		public double getY(){
			return this.y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		int T;
		
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n;
		
		T = Integer.parseInt(br.readLine().trim());
		String input[];
		double x,y,x1,y1, dist;
		for(int i = 0; i < T; i++){
			n = Integer.parseInt(br.readLine().trim());
			queue.clear();
			min = Double.MAX_VALUE;
			for(int j =0; j<n; j++){
				input = br.readLine().trim().split(" ");
				x = Double.parseDouble(input[0]);
				y = Double.parseDouble(input[1]);
				point[j] = new Point(x, y);
				
				if(nodes[j] == null){
					nodes[j] = new ArrayList<Node>();
				}
				else{
					nodes[j].clear();
				}
				
				for(int k =0; k<j; k++){
					x1 = point[k].getX();
					y1 = point[k].getY();
					dist = ((x1-x)*(x1-x)) + ((y1-y)*(y1-y));
					
					nodes[k].add(new Node(k, j, dist));
					nodes[j].add(new Node(j, k, dist));
				}
				from[j] = false;
			}
			
			isAll2(n);
			//isAll(0, n, 0, 0);
			min = Math.sqrt(min);
			min = Math.round(min * 100.0)/100.0;
			System.out.printf("%.2f", min);
			System.out.println("");
		}
	}
	
	
	private static void isAll2(int len){		
		queue.add(new Node(0, 0, 0));
		
		Node node, node1;
		int size;
		int f,t,t1;
		double w,w1,w2;
		int cnt = 0;
		while(!queue.isEmpty()){
			node = queue.poll();
			f = node.getFrom();
			t = node.getTo();
			w = node.getW();

			if(from[t] == true){
				continue;
			}
			
			if(cnt+1 >= len){
				if(min > w){
					min = w;
				}

				break;
			}
			cnt++;
			from[t] = true;
			size = nodes[t].size();
			for(int i =0; i<size; i++){
				node1 = nodes[t].get(i);
				t1 = node1.getTo();
				w1 = node1.getW();
				
				if(from[t1] == true){
					continue;
				}
				
				w2 = w;
				if(w2 == 0 || w1 > w2){
					w2 = w1;
				}
				if(w2 > min){
					continue;
				}
				
				queue.add(new Node(t, t1, w2));
			}
 		}		
	}
	
	private static boolean isAll(int st, int len, int cnt, double w){
		boolean isAll = false;

		if(len == cnt +1){
			if(min > w){
				min = w;
			}
			return true;
		}

		from[st] = true;
		cnt++;
		
		Node node;
		int t;
		double w1, w2;
		
		Iterator<Node> it = nodes[st].iterator();
		while(it.hasNext()){		
			node = it.next();
			t = node.getTo();
			w1 = node.getW();
			if(from[t] == true){
				continue;
			}
			
			w2 = w;
			if(w2 == 0 || w2 < w1){
				w2 = w1;
			}			
			
			if(w2 >= min){
				continue;
			}
			
			isAll(t, len, cnt, w2);
		}
		
		from[st] = false;
		
		return isAll;
	}
}