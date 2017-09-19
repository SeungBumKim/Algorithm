/**************************************************************
    Problem: ALLERGY (https://algospot.com/judge/problem/read/ALLERGY)
    User: magicguru
    Language: Java
    Result: Success
    Time: 462ms
    Memory: 1.9KB
****************************************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	private static long alergy[] = new long[51];
	private static int manNum, cockNum, alergyNum;
	
	public static void main(String[] args) throws Exception{		
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T, i;		
		long fullVal;
		String input[];
		ArrayList<String> man = new ArrayList<String>();
		
		T = Integer.valueOf(br.readLine().trim());
		for(i =0; i<T; i++){
			input = br.readLine().trim().split(" ");
			manNum = Integer.valueOf(input[0]);
			cockNum = Integer.valueOf(input[1]);
			
			// man input
			man.clear();
			fullVal = 0;
			input = br.readLine().trim().split(" ");
			for(int j=0; j<input.length; j++){
				man.add(j, input[j]);
				fullVal |= (1 << j);
			}

			// alergy input
			for(int j =0; j<cockNum; j++){
				input = br.readLine().trim().split(" ");
				alergy[j] = 0;
				
				alergyNum = Integer.valueOf(input[0]);
				for(int k=0; k<alergyNum; k++){
					alergy[j] |= (1 << man.indexOf(input[k+1]));
				}
			}
			
			int j;
			for(j=0; j<manNum; j++){
				if(findAll(0, 0, j+1, 0, fullVal)){
					break;
				}
			}
			
			System.out.println(j+1);
			//System.gc();
		}
	}
	
	private static boolean findAll(int stNum, int curNum, int allowNum, long curVal, long fullVal){
		boolean isGood = false;
		
		long tempVal;
		for(int i=stNum; i<cockNum; i++){
			tempVal = curVal;
			tempVal |= alergy[i];
			if(tempVal == fullVal){
				isGood = true;
				break;
			}
			else{
				if(((curNum + 1) < allowNum) && ((i + 1) < cockNum)){
					if(findAll(i+1, curNum +1, allowNum, tempVal, fullVal)){
						isGood = true;
						break;
					}
				}
			}
		}
		
		return isGood;
	}
}