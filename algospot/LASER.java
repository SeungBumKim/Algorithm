/**************************************************************
    Problem: LASER (https://algospot.com/judge/problem/read/LASER)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1848ms
    Memory: 3.1KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main{
	private static Point point[] = new Point[1001];
	private static HashMap<Long, Integer> map = new HashMap<Long, Integer>();
	private static HashMap<Long, Integer> tmpMap = new HashMap<Long, Integer>();

	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int getX(){
			return this.x;
		}
		public int getY(){
			return this.y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String input[];
		long key;
		int i, j, an, cnt;
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=0; t<T; t++){
			an = 0;
			int n = Integer.parseInt(br.readLine().trim());
			map.clear();
			for(i=0; i<n; i++){
				input = br.readLine().trim().split(" ");
				point[i] = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));				
				tmpMap.clear();				
				for(j=0; j<i; j++){
					key = getKey(point[i], point[j]);
					if(map.containsKey(key)){
						if(!tmpMap.containsKey(key)){
							cnt = map.get(key);
							cnt++;						
							if(cnt >= 5){
								an++;
								map.put(key, -10);
							}
							else if(cnt > 0){
								map.put(key, cnt);
							}							
							tmpMap.put(key, 1);
						}
					}
					else{
						map.put(key, 2);
					}
				}
			}
			
			System.out.println(an);
		}
	}
	
	public static long getKey(Point p1, Point p2){
		int a = p2.getY() - p1.getY();
		int b = p1.getX() - p2.getX();
		int c = p2.getX()*p1.getY() - p1.getX()*p2.getY();
		
		if(a < 0){
			a = -a;
			b = -b;
			c = -c;
		}
		else if(a == 0){
			if(b < 0){
				b = -b;
				c = -c;
			}
		}

		int absA = Math.abs(a);
		int absB = Math.abs(b);
		int absC = Math.abs(c);
		
		int gcd;
		if(a == 0){
			if(c == 0){
				gcd = absB;
			}
			else{
				gcd = gcd(absB, absC);
			}
		}
		else if(b == 0){
			if(c == 0){
				gcd = absA;
			}
			else{
				gcd = gcd(absA, absC);
			}
			
		}
		else if(c == 0){
			gcd = gcd(absA, absB);
		}
		else{
			gcd = gcd(gcd(absA, absB), absC);
		}
		
		a /= gcd;
		b /= gcd;
		c /= gcd;

		//long ret = (long)a*(long)Math.pow(20000,2) + (long)b*20000L+(long)c;
		// long ret = (a < 0 ? 10000000000000000L : 0L) + (a * 100000000000L) + (b < 0 ? 10000000000L : 0) + (b * 10000000L) + (c < 0 ? 1000000L : 0) + (long)c;
		String ret = (a < 0 ? "-" : "") + a + (b < 0 ? "-" : "+") + b + (c < 0 ? "-" : "+") + c; 
		return ret.hashCode();
	}
	
	public static int gcd(int in1, int in2){
		int gcd = 0;
		
		int b, s;
		if(in1 >= in2){
			b = in1;
			s = in2;
		}
		else{
			b = in2;
			s = in1;
		}

		if(s == 0){
			return -1;
		}
		
		int tmp;
		while(true){
			tmp = b % s;
			if(tmp == 0){
				gcd = s;
				break;
			}
			b = s;
			s = tmp;
		}
		
		return gcd;
	}
}