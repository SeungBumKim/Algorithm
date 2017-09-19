/**************************************************************
    Problem: NUMBERGAME (https://algospot.com/judge/problem/read/NUMBERGAME)
    User: magicguru
    Language: Java
    Result: Success
    Time: 432ms
    Memory: 4.3KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	private static int arr[] = new int[50];
	private static HashMap<Integer, Integer> hi = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> hiL = new HashMap<Integer, Integer>();
//	private static int history[][][] = new int[2][51][51];
//	private static int historyL[][][] = new int[2][51][51];
	private static int n;
	private static int last;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());

		String input[];
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine().trim());
			input = br.readLine().trim().split(" ");

			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(input[i]);
			}
			hi.clear();
			hiL.clear();
			//System.out.println("T: " + game(0, 0, 0, 0));
			
			int who = 0, stIdx = 0, endIdx = n - 1;
			int sum[] = new int[2];
			int tmpMeSum = 0, tmpYouSum = 0;
			while (true) {
				last = -1;
				// hi.clear();

				// int ret = game(who, stIdx, endIdx, sum[who]-sum[who==0 ? 1 :
				// 0], sum[who], sum[who==0 ? 1 : 0]);
				// if(who == 0){
				// tmpMeSum = sum[0];
				// tmpYouSum = sum[1];
				// }
				// else{
				// tmpMeSum = sum[1];
				// tmpYouSum = sum[0];
				// }

				int ret = game(0, stIdx, endIdx, 0);
				if (last == -1 || stIdx > endIdx) {
					break;
				} else {
					switch (last) {
					case 1:
						sum[who] += arr[stIdx++];
						break;
					case 2:
						sum[who] += arr[endIdx--];
						break;
					case 3:
						stIdx += 2;
						break;
					case 4:
						endIdx -= 2;
						break;
					}
				}
				who = (who + 1) % 2;
			}

			System.out.println(sum[0] - sum[1]);
		}
	}

	private static int game(int who, int stIdx, int endIdx, int gap) {
		//System.out.println(who + ", " + stIdx + ", " + endIdx);
		if (stIdx > endIdx) {
			return gap;
		}

		if(who == 0 && stIdx == 1 && endIdx == 4)
		{
			stIdx = 1;
		}
		int hiVal = (who * 10000) + (stIdx * 100) + (endIdx);
		if (hi.containsKey(hiVal)) {
			last = hiL.get(hiVal);
			return gap + hi.get(hiVal);
		}

//		 if(history[who][stIdx][endIdx] > 0){
//			 last = historyL[who][stIdx][endIdx];
//			 return gap + history[who][stIdx][endIdx];
//		 }

		int retGood = who == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int tmpMeSum, tmpYouSum;
		int ret1 = who == 0 ? Integer.MIN_VALUE + 1 : Integer.MAX_VALUE - 1;
		int ret2 = who == 0 ? Integer.MIN_VALUE + 1 : Integer.MAX_VALUE - 1;
		int ret3 = who == 0 ? Integer.MIN_VALUE + 1 : Integer.MAX_VALUE - 1;
		int ret4 = who == 0 ? Integer.MIN_VALUE + 1 : Integer.MAX_VALUE - 1;

		// left get
		// tmpMeSum = who == 0 ? meSum + arr[stIdx] : meSum;
		// tmpYouSum = who == 1 ? youSum + arr[stIdx] : youSum;
		ret1 = game((who + 1) % 2, stIdx + 1, endIdx, who == 1 ? -arr[stIdx] : arr[stIdx]);
		retGood = who == 0 ? Math.max(ret1, retGood) : Math.min(ret1, retGood);

		// right get
		// tmpMeSum = who == 0 ? meSum + arr[endIdx] : meSum;
		// tmpYouSum = who == 1 ? youSum + arr[endIdx] : youSum;
		ret2 = game((who + 1) % 2, stIdx, endIdx - 1, who == 1 ? -arr[endIdx] : arr[endIdx]);
		retGood = who == 0 ? Math.max(ret2, retGood) : Math.min(ret2, retGood);

		if (endIdx - stIdx >= 1) {
			// left 2 discard
			ret3 = game((who + 1) % 2, stIdx + 2, endIdx, 0);
			retGood = who == 0 ? Math.max(ret3, retGood) : Math.min(ret3, retGood);

			// right 2 discard
			ret4 = game((who + 1) % 2, stIdx, endIdx - 2, 0);
			retGood = who == 0 ? Math.max(ret4, retGood) : Math.min(ret4, retGood);
		}

		if (ret1 == retGood) {
			last = 1;
		} else if (ret2 == retGood) {
			last = 2;
		} else if (ret3 == retGood) {
			last = 3;
		} else if (ret4 == retGood) {
			last = 4;
		}

		// System.out.println("who: " + who +" st: " + stIdx + ", end: " +
		// endIdx + "| " + ret1 + ", " + ret2 +", " + ret3 +", " +ret4);
		
		hi.put(hiVal, retGood);
		hiL.put(hiVal, last);
//		historyL[who][stIdx][endIdx] = last;
//		history[who][stIdx][endIdx] = retGood;
		
		return gap + retGood;
	}
}