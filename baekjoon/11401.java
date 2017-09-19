/**************************************************************
    Problem: 11401 (https://www.acmicpc.net/problem/11401)
    User: magicguru
    Language: Java
    Result: Success
    Time: 288ms
    Memory: 31072KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	private static long MOD = (long)1000000007;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input[] = br.readLine().trim().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		
		int i;
		long N = 1, TK = 1;
		for(i =n; i>k; i--){
			N *= (long)i;
			if(N >= MOD){
				N %= MOD;
			}
		}
				
		for(i =0; i<n-k; i++){
			TK *= (long)(i+1);
			if(TK >= MOD){
				TK %= MOD;
			}
		}
		
		long K = modInv(TK, MOD-2)%MOD;
		System.out.println((N*K)%MOD);
	}

	private static long modInv(long a, long M) {
        if (M == 1)
            return a;
        if (M == 0)
            return 1;
     
        long  tmp = modInv(a, M / 2);
        if ((M % 2) == 1)
            return (long)((long)((tmp*tmp) % MOD)*a) % MOD;
        else
            return (long)(tmp*tmp) % MOD;
    }
}