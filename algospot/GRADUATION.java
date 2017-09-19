/**************************************************************
    Problem: GRADUATION (https://algospot.com/judge/problem/read/GRADUATION)
    User: magicguru
    Language: Java
    Result: Success
    Time: 404ms
    Memory: 3.8KB
****************************************************************/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	private static int subjectN, targetN, hakiN, subMaxN;
	private static int subject[] = new int[12];
	private static ArrayList<Integer> subjectArr[] = new ArrayList[12];
	private static int haki[] = new int[10];
	private static ArrayList<Integer> hakiArr[] = new ArrayList[10];
	private static int an;
	private static ArrayList<Integer> avail = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int T = scanner.nextInt();
		
		//int T = Integer.parseInt(br.readLine().trim());
		
		int i,j,n, tmp;

		String input[];
		for(int t=0; t<T; t++){
			//input =  scanner.nextInt();//br.readLine().trim().split(" ");
			subjectN = scanner.nextInt();//Integer.parseInt(input[0]);
			targetN = scanner.nextInt();//Integer.parseInt(input[1]);
			hakiN = scanner.nextInt();//Integer.parseInt(input[2]);
			subMaxN = scanner.nextInt();//Integer.parseInt(input[3]);
			
			an = Integer.MAX_VALUE;
			for(i =0; i<subjectN; i++){
				if(subjectArr[i] == null){
					subjectArr[i] = new ArrayList<Integer>();
				}
				else{
					subjectArr[i].clear();
				}
				
				subject[i] = 0;
				//input = br.readLine().trim().split(" ");
				n = scanner.nextInt();//Integer.parseInt(input[0]);
				for(j =0; j<n; j++){
					tmp = scanner.nextInt();//Integer.parseInt(input[j+1]);
					subject[i] |= 1 << tmp;
					subjectArr[i].add(tmp);
				}
			}
			
			for(i =0; i<hakiN; i++){
				if(hakiArr[i] == null){
					hakiArr[i] = new ArrayList<Integer>();
				}
				else{
					hakiArr[i].clear();
				}
				
				haki[i] = 0;
				//input = br.readLine().trim().split(" ");
				n = scanner.nextInt();//Integer.parseInt(input[0]);
				for(j =0; j<n; j++){
					tmp = scanner.nextInt();//Integer.parseInt(input[j+1]);
					haki[i] |= 1 << tmp;
					hakiArr[i].add(tmp);
				}
			}

			find(0, 0, 0, 0);
			System.out.println(an == Integer.MAX_VALUE ? "IMPOSSIBLE" : an);
		}
	}
	
	private static void find(int idx, int pass, int passLen, int hakiCnt){
		if(passLen >= targetN){
			if(an > hakiCnt){
				an = hakiCnt;
			}
			return;
		}
		if(idx >= hakiN){
			return;
		}
		
		int tmpPassLen = passLen;
		int tmpIsPass;
		
		avail.clear();
		int i, j, n, hakiI, tmpBit;
		// find avail subject
		n = hakiArr[idx].size();
		for(i =0; i<n; i++){
			hakiI = hakiArr[idx].get(i);
			tmpBit = (1 << hakiI);
			if((pass & tmpBit) == tmpBit){
				continue;
			} 
			if((hakiI < subjectN) && ((pass & subject[hakiI]) == subject[hakiI])){
				avail.add(hakiI);
			}
		}
		
		// find make subject group
		n = avail.size();
		if(n == 0){
			find(idx+1, pass, tmpPassLen, hakiCnt);			
		}
		else if(n <= subMaxN){
			tmpIsPass = pass;
			for(i=0; i<n; i++){
				tmpPassLen++;
				tmpIsPass |= 1 << avail.get(i);
			}

			find(idx+1, pass, passLen, hakiCnt);
			find(idx +1, tmpIsPass, tmpPassLen, hakiCnt+1);
		}
		else{
			tmpPassLen += subMaxN; 
			ArrayList<Integer>  ttmp= new ArrayList<Integer>();
			makeBit(pass, n, 0, 0, ttmp);
			
			for(j=0; j<ttmp.size(); j++ ){
				find(idx +1, ttmp.get(j), tmpPassLen, hakiCnt+1);				
			}
		}
	}
	
	private static void makeBit(int bit, int n, int idx, int curN, ArrayList<Integer> arr){
		if(curN == subMaxN){
			arr.add(bit);
			return;
		}
		
		for(int i=idx; i<n; i++){
			makeBit((bit | (1<<avail.get(i))), n, i+1, curN+1, arr);
		}
	}
}