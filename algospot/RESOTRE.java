/**************************************************************
    Problem: RESTORE (https://algospot.com/judge/problem/read/RESTORE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 176ms
    Memory: 4.3KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	private static char arr[][];
	private static String val[];
	private static String valTmp[];
	private static boolean skip[];
	private static String memo[][];
	private static ArrayList<Node> list[]; 
	
	private static class Node implements Comparable<Node>{
		int st;
		int end;
		int duCnt;
		
		public Node(int st, int end, int duCnt){
			this.st = st;
			this.end = end;
			this.duCnt = duCnt;
		}

		public int getSt(){
			return this.st;
		}
		public int getEnd(){
			return this.end;
		}
		public int getCnt(){
			return this.duCnt;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.duCnt > o.getCnt()){
				return -1;
			}
			else if(this.duCnt < o.getCnt()){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		int n, i, j, cnt, skipCnt;
		String tmp;
		for(int t=0; t<T; t++){
			n = Integer.parseInt(br.readLine().trim());
			valTmp = new String[n];
			skip = new boolean[n];
			skipCnt = 0;
			for(i=0; i<n; i++){
				tmp = br.readLine().trim();
				for(j=0; j<i; j++){
					if(skip[i] == false && valTmp[j].contains(tmp)){
						skipCnt++;
						skip[i] = true;
					}
					else if(skip[j] == false && tmp.contains(valTmp[j])){
						skipCnt++;
						skip[j] = true;
					}
				}
				valTmp[i] = tmp;
			}

			
			arr = new char[n-skipCnt][];
			val = new String[n-skipCnt];
			list = new ArrayList[n-skipCnt];
			j =0;
			for(i=0; i<n; i++){
				if(skip[i]){
					continue;
				}
				val[j] = valTmp[i];
				arr[j] = new char[val[j].length()];
				arr[j] = val[j].toCharArray();
				list[j] = new ArrayList<Node>();
				j++;
			}
			n = n - skipCnt;
			memo = new String[n][(int)Math.pow(2, n)];
			for(i=0; i<n; i++){
				for(j=i+1; j<n; j++){
					cnt = getDuCount(i, j);
					if(cnt > 0){
						list[i].add(new Node(i, j, cnt));
					}
					
					cnt = getDuCount(j, i);
					if(cnt > 0){
						list[j].add(new Node(j, i, cnt));
					}
				}
			}
			
			String an = "", ret;
			for(i =0; i<n; i++){
				ret = find(i, n, (1 << i));

				if(an.length() == 0){
					an = ret;
				}
				else{
					if(ret.length() > 0 && (ret.length() < an.length())){
						an= ret;
					}
				}
			}
			
			System.out.println(an);
		}
	}
	
	private static String find(int idx, int n, int using){
		String min = "";
		
		if(memo[idx][using] != null && memo[idx][using].length() > 0){
			return memo[idx][using];
		}
		
		String tmp;
		String ret;
		Node node;
		int to;
		if(list[idx].size() > 0){
			for(int i =0 ; i<list[idx].size(); i++){
				node = list[idx].get(i);
				to = node.getEnd();
				if((using & (1 << to))  == (1 << to)){
					continue;
				}
				
				ret = find(to, n, (using | (1 << to)));
				tmp = val[idx] + ret.substring(node.getCnt(), ret.length());
				if(min.length() == 0){
					min = tmp;
				}
				else{
					if(tmp.length() > 0 && (tmp.length() < min.length())){
						min = tmp;
					}
				}
			}	
		}
		
		if(min.length() == 0){
			for(int i=0; i<n; i++){
				if((using & (1 << i))  == (1 << i)){
					continue;
				}
				
				ret = find(i, n, (using | (1 << i)));
				tmp = val[idx] + ret;
				if(min.length() == 0){
					min = tmp;
				}
				else{
					if(tmp.length() > 0 && (tmp.length() < min.length())){
						min = tmp;
					}
				}
			}
		}
		
		if(min.length() == 0){
			min = val[idx];
		}
		
		
		memo[idx][using] = min;
		return memo[idx][using];
	}
	
	private static int getDuCount(int fr, int end){
		int fLen = val[fr].length();
		int eLen = val[end].length();
		
		int i,j, eIdx =0, cnt =0;
		for(i=0; i<fLen; i++){
			eIdx = 0;
			cnt = 0;
			if(arr[fr][i] == arr[end][eIdx]){
				for(j=i; j<fLen; j++){
					if(eIdx >= eLen){
						j = fLen;
						break;
					}
					if(arr[fr][j] != arr[end][eIdx]){
						break;
					}
					cnt++;
					eIdx++;
				}
				
				if(j == fLen){
					break;
				}
			}
		}
		return cnt;
	}
}