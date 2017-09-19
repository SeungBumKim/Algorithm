/**************************************************************
    Problem: ZIMBABWE (https://algospot.com/judge/problem/read/ZIMBABWE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1203ms
    Memory: 2.3KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	private static int in[];
	private static int loop[];
	private static int cache[][][];
	private static int m, len;
	
	public static void main(String[] args) throws Exception{
//		long stTime = System.currentTimeMillis();
//		System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int T = Integer.parseInt(br.readLine().trim());
		int i;
		String input[];
		for(int t=0; t<T; t++){
			input = br.readLine().trim().split(" ");
			len = input[0].length();

			in = new int[len];
			loop = new int[len];
			for(i=0; i<len; i++){
				in[i] = input[0].charAt(i)-'0';
				loop[i] = in[i];
			}
			m = Integer.parseInt(input[1]);
			int n1 = 1<<len;
			cache = new int[n1][m][2];
			for(int k =0; k<n1; k++){
				for(int l=0; l<m; l++){
					for(int y=0; y<2; y++){
						cache[k][l][y] = -1;
					}
				}
			}
			
			Arrays.sort(loop);
			System.out.print(makeCase(0, 0, 0, 0) +"\n");
		}
//		System.out.println(System.currentTimeMillis() - stTime + "ms");
	}
	
	private static int makeCase(int idx, int bit, int mod, int less){		
		//System.out.println(idx + ", " + mod + ", " + less);
		if(idx >= len){
			return ((less == 1) && (mod == 0)) ? 1 : 0;
		}		

		if(cache[bit][mod][less] != -1){
			return cache[bit][mod][less];
		}
		
		int tmp, ret=0, tmpBit, tmpMod, tmpLess;
		//int prevVal = -1;
		
		for(int i=0; i<len; i++){
			tmp = loop[i];
			if((bit & (1<<i)) == 0){				
				// check bigger val
				if(less == 0 && in[idx] < tmp){
					continue;
				}
			
				// chekc same val - avoid duplicated val			
				if(i > 0 && loop[i-1] == loop[i] && (bit & (1<<(i-1))) == 0){
				//if(prevVal != -1 && tmp == prevVal){
					continue;
				}
				
				tmpBit = bit | (1<<i);
				tmpMod = ((mod*10) + tmp)%m;
				tmpLess = (less == 1 || in[idx] > tmp) ? 1 : 0;
				
				// loop				
				//loop[i] = -1;
				ret += makeCase(idx+1, tmpBit, tmpMod, tmpLess);
				ret %= 1000000007;
				//loop[i] = tmp;

				//prevVal = tmp;
			}
		}
		cache[bit][mod][less] = ret;
		return ret;
	}
}