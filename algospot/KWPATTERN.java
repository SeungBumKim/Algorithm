/**************************************************************
    Problem: KWPATTERN (https://algospot.com/judge/problem/read/KWPATTERN)
    User: magicguru
    Language: Java
    Result: Success
    Time: 864ms
    Memory: 1.2KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Main{	

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		int n;

		
		// make an;
		String spec[] = new String[]{"-01-11 10:", "-01-22 10:", "-02-11 20:", "-02-22 20:", "-10-11 01:", "-10-22 01:", "-11-11 11:", "-11-22 11:", "-12-11 21:", "-12-22 21:"};
		String an[] = new String[10000];
		int st = 1970;
		String tmp;
		int m, s, i, anLen;
		int len = spec.length;
		
		anLen = 0;
		while(true){
			tmp = String.valueOf(st);
			s = ((tmp.charAt(1)-'0') * 10) + (tmp.charAt(0)-'0');
			m = ((tmp.charAt(3)-'0') * 10) + (tmp.charAt(2)-'0');
			
			if(s < 60 && m < 60){				
				for(i =0; i<len; i++){					
					an[anLen++] = tmp + spec[i] + String.format("%02d", m) + ":"+ String.format("%02d", s); 
				}
			}
			if(anLen >= 10000){
				break;
			}
			st++;
		}
		
		
		for(int t =0; t<T; t++){
			n = Integer.parseInt(br.readLine().trim());
			System.out.println(an[n-1]);
		}
	}
	
}