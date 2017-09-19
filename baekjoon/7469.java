/**************************************************************
    Problem: 7469 (https://www.acmicpc.net/problem/7469)
    User: magicguru
    Language: Java
    Result: Success
    Time: 2312ms
    Memory: 29068KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	private static int N,M;
	private static int[] in;
	
	private static class SegTree{
		int data[];
		int size;
		int tree[][];
		
		public SegTree(int[] in, int n){
			int maxSize = (int)Math.pow(2, ((int)logB(100000, 2)+2));
			tree = new int[maxSize][];
			this.data = in;
			this.size = n;
			
			init(1, 1, this.size);
		}
		
		private int[] init(int idx, int left, int right){
			if(left == right){
				tree[idx] = new int[1];
				tree[idx][0] = data[left-1];
			}
			else{
				int mid = (left + right) / 2;
				int[] leftTree = init(idx*2, left, mid);
				int[] rightTree = init(idx*2 +1, mid+1, right);
				
				tree[idx] = new int[right-left+1];
				int tIdx =0, lIdx =0, rIdx =0;
				while(true){
					if(lIdx >= leftTree.length && rIdx >= rightTree.length){
						break;
					}
					else if(lIdx < leftTree.length && rIdx >= rightTree.length){
						while(lIdx < leftTree.length){
							tree[idx][tIdx++] = leftTree[lIdx++];
						}
					}
					else if(lIdx >= leftTree.length && rIdx < rightTree.length){
						while(rIdx < rightTree.length){
							tree[idx][tIdx++] = rightTree[rIdx++];
						}
					}
					else{
						if(leftTree[lIdx] < rightTree[rIdx]){
							tree[idx][tIdx++] = leftTree[lIdx++];
						}
						else if(leftTree[lIdx] > rightTree[rIdx]){
							tree[idx][tIdx++] = rightTree[rIdx++];
						}
					}
				}
			}
			
			return tree[idx];
		}
		
		public void query(ArrayList<int[]> ret, int idx, int st, int end, int left, int right){
			if(st <= left && end >= right){
				ret.add(tree[idx]);
			}
			else if(st > right || end < left){
				// do nothing
			}
			else{
				int mid = (left + right) / 2;
				query(ret, idx*2, st, end, left, mid);
				query(ret, idx*2+1, st, end, mid+1,right);
			}
		}		
	}
	
	public static double logB(double x, double base) {
		return Math.log(x) / Math.log(base);
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input[] = br.readLine().trim().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		int i, j, l, Min = Integer.MAX_VALUE, Max = Integer.MIN_VALUE,  min, max;
		in = new int[N];
		input = br.readLine().trim().split(" ");
		for(i =0; i<N; i++){
			in[i] = Integer.parseInt(input[i]);
			Min = Math.min(in[i], Min);
			Max = Math.max(in[i], Max);
		}
		
//		N = 100000;
//		in = new int[N];
//		j = 0;
//		for(i =100000; i>0 ;i--){
//			in[j] = i;
//			Min = Math.min(in[j], Min);
//			Max = Math.max(in[j++], Max);
//		}
		
		SegTree segTree = new SegTree(in, N);
		
		int st, end, k, mid, cnt, maxVal=0;
		int tmp[];
		ArrayList<int[]> list = new ArrayList<int[]>();
		int stList[], idxList[], lenList[];
		for(i =0; i<M; i++){
			input = br.readLine().trim().split(" ");
			st = Integer.parseInt(input[0]);
			end = Integer.parseInt(input[1]);
			k = Integer.parseInt(input[2]);
			
			list.clear();
			segTree.query(list, 1, st, end, 1, N);
			
			// binary search
			stList = new int[list.size()];
			idxList = new int[list.size()];
			lenList = new int[list.size()];
			for(j=0; j<list.size(); j++){
				lenList[j] = list.get(j).length;
			}
			
			//mid = (Min+Max)/2;
			mid = Min + (Max - Min) / 2;

			min = Min;
			max = Max;
			while(true){
				//System.out.println("mid: " + mid);
				cnt = 0;
				maxVal = Integer.MIN_VALUE;
				for(j=0; j<list.size(); j++){
					tmp = list.get(j);
					while(idxList[j] < lenList[j]){
						if(mid >= tmp[idxList[j]]){
							cnt++;
							maxVal = Math.max(maxVal, tmp[idxList[j]]);
							idxList[j]++;
						}
						else{
							break;
						}
					}
				}
				
				if(cnt > k){
					for(j=0; j<list.size(); j++){
						lenList[j] = idxList[j];
						idxList[j] = stList[j];
					}
					max = mid;
					mid = min + (mid - min) / 2;
					//mid = (min + mid) / 2;					
				}
				else if(cnt == k){
					break;
				}
				else{
					k -= cnt;
					min = mid+1;
					mid = min + (max - min) / 2;
					//mid = (mid+1 + max) / 2;
					for(j=0; j<list.size(); j++){
						stList[j] = idxList[j];
					}
				}
			}
			System.out.println(maxVal);
		}
	}
}