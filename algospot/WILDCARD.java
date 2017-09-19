/**************************************************************
    Problem: WILDCARD (https://algospot.com/judge/problem/read/WILDCARD)
    User: magicguru
    Language: Java
    Result: Success
    Time: 136ms
    Memory: 1.5KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static char f[], cmp[];
	private static int fLen, cmpLen;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());		
		int t, i, n;
		ArrayList<String> anList = new ArrayList<String>();
		for(t=0; t<T; t++){
			f = br.readLine().trim().toCharArray();
			fLen = f.length;
			anList.clear();
			n = Integer.parseInt(br.readLine().trim());
			for(i =0; i<n; i++){
				cmp = br.readLine().trim().toCharArray();
				cmpLen = cmp.length;
				
				if(compare(0, 0)){
					anList.add(String.valueOf(cmp));
				}
			}
			Collections.sort(anList);
			for(String an: anList){
				System.out.println(an);
			}
		}
	}
	
	private static boolean compare(int oIdx, int cIdx){

		if(oIdx >= fLen && cIdx >= cmpLen){
			return true;
		}
		else if(oIdx >= fLen){			
			return false;
		}
		else if(cIdx >= cmpLen){
			if(f[oIdx] == '*'){
				return compare(oIdx + 1, cIdx);
			}
			return false;
		}
		
		
		if(f[oIdx] == cmp[cIdx] || f[oIdx] == '?'){
			return compare(oIdx+1, cIdx+1);
		}
		else if(f[oIdx] == '*'){
			for(int i=cIdx; i<=cmpLen; i++){
				if(compare(oIdx+1, i)){
					return true;
				}
			}
		}
		
		return false;
	}
}