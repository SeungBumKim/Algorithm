/**************************************************************
    Problem: 3693 (https://www.acmicpc.net/problem/3693)
    User: magicguru
    Language: Java
    Result: Success
    Time: 148ms
    Memory: 10040KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		int i, j, loop;
		long t, wN, hN;
		long w, h, minW =0, minH =0;
		long sq, minsq;
		for(i =0; i<N; i++){
			t = Long.parseLong(br.readLine().trim());
			t = (t/5) + (t%5>0?1:0);
			
			wN = 1;
			hN = t;			
			w = 48;
			h = getH(hN);
			minW = w;
			minH = h;
			minsq = w * h;

			loop = (int)Math.sqrt(t);			
			for(j=0; j<loop; j++){
				wN++;
				hN = (t/wN) + (t%wN>0?1:0);				
				w += 44;
				h = getH(hN);
				sq = w * h;
				if(sq < minsq){
					minW = w;
					minH = h;
					minsq = sq;
				}				
			}
			
			if(minW > minH){
				System.out.println(minW + " X " + minH + " = " + minsq);
			}
			else{
				System.out.println(minH + " X " + minW + " = " + minsq);
			}
		}
	}
	private static long getH(long n){
		return (8*n) + ((n+1)*2);
	}
}