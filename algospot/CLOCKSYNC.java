/**************************************************************
    Problem: CLOCKSYNC (https://algospot.com/judge/problem/read/CLOCKSYNC)
    User: magicguru
    Language: Java
    Result: Success
    Time: 2982ms
    Memory: 4.5KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
	private static int clock[] = new int[16];
	private static long allOk = 0;
	private static int curClock;
	private static long curVal;
	private static int move[] = { 0x0007, 0x0544, 0xc410, 0x00f1, 0x15c0, 0xc005, 0xc008, 0xc0b0, 0x003e, 0x2238 };
	private static ArrayList<int[]> moveArr = new ArrayList<int[]>();
	private static ArrayList<Long> history = new ArrayList<Long>();
	private static HashMap<Long, Integer> hiMap = new HashMap<Long, Integer>();

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		moveArr.add(0, new int[] { 0, 1, 2 });
		moveArr.add(1, new int[] { 3, 7, 9, 11 });
		moveArr.add(2, new int[] { 4, 10, 14, 15 });
		moveArr.add(3, new int[] { 0, 4, 5, 6, 7 });
		moveArr.add(4, new int[] { 6, 7, 8, 10, 12 });
		moveArr.add(5, new int[] { 0, 2, 14, 15 });
		moveArr.add(6, new int[] { 3, 14, 15 });
		moveArr.add(7, new int[] { 4, 5, 7, 14, 15 });
		moveArr.add(8, new int[] { 1, 2, 3, 4, 5 });
		moveArr.add(9, new int[] { 3, 4, 5, 9, 13 });

		int i, j, v, an;
		String input[];

		int T = Integer.parseInt(br.readLine().trim());
		for (i = 0; i < T; i++) {
			curClock = 0x0;
			curVal = 0;
			history.clear();
			hiMap.clear();
			allOk = 0;
			input = br.readLine().trim().split(" ");
			for (j = 0; j < 16; j++) {
				v = Integer.parseInt(input[j]);
				//curClock |= 1 << ((v == 12 ? 0 : j));
				curVal |= (long) v << (j * 4);
				clock[j] = v;
				allOk |= (long) (0xc) << (j * 4);
			}			
			
			an = find(0, 0, curVal, 0);
			System.out.println(an == Integer.MAX_VALUE ? -1 : an);
		}
	}
	
	private static int find(int st, int sameCont, long curBit, int cnt){
		int ret = Integer.MAX_VALUE;
		long tmpCur;
		int[] tmpArr;
		int[] tmpOld = new int[16];
		int idx;

		if(curBit == allOk){
			return cnt;
		}
		if(hiMap.containsKey(curBit)){
			return hiMap.get(curBit);
		}
		if(sameCont >= 4){
			return Integer.MAX_VALUE;
		}
		
		for(int i=st; i<10; i++){
			tmpCur = curBit;			
			tmpArr = moveArr.get(i);
			
			for(int j=0; j<tmpArr.length; j++){
				idx = tmpArr[j];
				tmpOld[idx] = clock[idx];
				clock[idx] = (clock[idx] + 3) > 12 ? 3 : (clock[idx] + 3);
			
				tmpCur &= ~((long)0xf << (idx * 4));
				tmpCur |= (long)clock[idx] << (idx *4);
			}
			
			ret = Math.min(ret, find(i, st == i ? sameCont+1 : 1, tmpCur, cnt+1));
			
			for(int j=0; j<tmpArr.length; j++){
				idx = tmpArr[j];
				clock[idx] = tmpOld[idx]; 
			}
		}
		
		
		if(ret != Integer.MAX_VALUE){
			hiMap.put(curBit, ret);
		}
		
		return ret;
	}
	
	

	private static int find2(int cnt, long cVal, int cur) {
		Queue<Long> queue[] = new LinkedList[16];
		Queue<Integer> queCur[] = new LinkedList[16];
		int t, n = 0, v, tmpCur;
		boolean isGood = false;
		int tmpArr[];
		long tmpCVal, tmp1;
		
		for (t = 0; t < 16; t++) {
			if(t == 0){
				queue[t] = new LinkedList<Long>();
				queCur[t] = new LinkedList<Integer>();

				queue[t].add(cVal);
				queCur[t].add(cur);
			}
			
			if(t+1 <16){
				queue[t+1] = new LinkedList<Long>();
				queCur[t+1] = new LinkedList<Integer>();
			}
			
			n++;

			// System.out.println(n);
			while (queue[t].size() > 0) {
				cVal = queue[t].poll();
				cur = queCur[t].poll();
				for (int i = 0; i < 10; i++) {
					tmpCur = cur;
					if ((tmpCur & move[i]) > 0) {
						tmpCVal = cVal;
						tmpArr = moveArr.get(i);
						for (int j = 0; j < tmpArr.length; j++) {
							v = (int) ((tmpCVal >> (tmpArr[j] * 4)) & (0xf));
							v = (v == 12) ? 3 : v + 3;

							if (v == 12) {
								tmpCur &= ~(1 << tmpArr[j]);
							} else {
								tmpCur |= (1 << tmpArr[j]);
							}
							
							tmpCVal &= ~((long) (0xf) << (tmpArr[j] * 4));
							tmpCVal |= (long) v << (tmpArr[j] * 4);
						}

						if (tmpCVal == allOk) {
							isGood = true;
							break;
						}
						
						if(history.contains(tmpCVal)){
							continue;
						}						
						
						history.add(tmpCVal);
						if(t+1 < 16){
							queue[t+1].add(tmpCVal);
							queCur[t+1].add(tmpCur);
						}
					}
				}

				if (isGood) {
					break;
				}
			}

			if (isGood) {
				break;
			}
		}
		
		if(n == 0){
			n = -1;
		}

		return n;
	}
}