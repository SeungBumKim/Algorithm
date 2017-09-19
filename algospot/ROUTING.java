/**************************************************************
    Problem: ROUTING (https://algospot.com/judge/problem/read/ROUTING)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1283ms
    Memory: 2.8KB
****************************************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{
	static double min = -1;
	static ArrayList<Spot> com[] = new ArrayList[10001];
	static PriorityQueue<Spot> q = new PriorityQueue<Spot>(10001, new Comp());
	static boolean in[] = new boolean[10001];

	public static class Comp implements Comparator<Spot> {
		@Override
		public int compare(Spot o1, Spot o2) {
			int result = 0;
			
			if((double)o1.getW() < (double)o2.getW()) {
				result = -1;
			} else if((double)o1.getW() > (double)o2.getW()) {
				result = 1;
			}
			
			return result;
		}
		
	}
	
	public static class Spot{
		int com;
		double w;
		
		Spot(int com, double w){
			this.com = com;
			this.w = w;
		}
		
		public int getCom() {
			return com;
		}

		public double getW() {
			return w;
		}
	}
	
	public static void main(String[] args) throws Exception{
		int T;		

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int		i, num, line;
		String input1[], input2[];
		Spot temp, temp1;
		
		/* 테스트 케이스의 수 T */
		T = Integer.parseInt(br.readLine().trim());
		
		/* 각 테스트 케이스에 대한 루프문 */
		for(i = 0; i < T; i++){
			input1 = br.readLine().trim().split(" ");
			num = Integer.parseInt(input1[0]);
			line = Integer.parseInt(input1[1]);

			for(int j =0; j<num; j++){
				if(com[j] != null){
					com[j].clear();
				}
				in[j] = false;
			}
			
			q.clear();
			int st, end;
			min = -1;
			double w;
			for(int j =0; j<line; j++){
				input2 = br.readLine().split(" ");
				st = Integer.parseInt(input2[0]);
				end = Integer.parseInt(input2[1]);
				w = Double.parseDouble(input2[2]);
				
				temp = new Spot(end, w);
				temp1 = new Spot(st, w);
				if(com[st] == null){
					com[st] = new ArrayList<Spot>();
				}
				if(com[end] == null){
					com[end] = new ArrayList<Spot>();
				}

				com[st].add(temp);
				com[end].add(temp1);				
			}
			
			findMinWeight(num,0, num-1);
			System.out.printf("%.10f\n", min);
		}
	}
	
	public static void findMinWeight(int maxLen, int start, int end){		
		
		q.add(new Spot(start, 1));
		
		Spot t, tmp;
		int len;
		while(!q.isEmpty()){
			t = q.poll();
			
			if(t.getCom() == end){
				double tmpMin = t.getW();
				if(min == -1){
					min = tmpMin;
				}
				else{
					if(min > tmpMin){
						min = tmpMin;
					}
				}
			}
			
			in[t.getCom()] = true;
			
			len = com[t.getCom()].size();
			for(int i =0; i< len; i++){
				tmp = com[t.getCom()].get(i);
				if(in[tmp.getCom()])
					continue;
				
				double tmpMin = tmp.getW() * t.getW();
				if(min == -1 || tmpMin < min){
					q.add(new Spot(tmp.getCom(), tmpMin));
				}
			}			
		}
	}
}