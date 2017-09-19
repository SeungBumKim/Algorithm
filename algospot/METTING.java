/**************************************************************
    Problem: METTING (https://algospot.com/judge/problem/read/MEETING)
    User: magicguru
    Language: Java
    Result: Success
    Time: 802ms
    Memory: 1.4KB
****************************************************************/

import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		int T;

		Scanner sc = new Scanner(System.in);		
		
		int i, j, len, sum, tMen, tWomen, temp;
		String arrMens[], arrWomens[];
		int arrMen[] = new int[10000];
		int arrWomen[] = new int[10000];
		
		T = Integer.parseInt(sc.next());
		sc.nextLine();
		for(i = 0; i < T; i++){		
			len = Integer.parseInt(sc.nextLine());

			arrMens = sc.nextLine().split(" ");
			arrWomens = sc.nextLine().split(" ");
			
			sum = 0;
			
			for(j=0; j<len; j++){
				tMen = Integer.parseInt(arrMens[j]);
				tWomen = Integer.parseInt(arrWomens[j]);

				arrMen[j] = tMen;
				arrWomen[j] = tWomen;
			}
			
			Arrays.sort(arrMen, 0, j);
			Arrays.sort(arrWomen, 0, j);
			
			for(j=0; j<len; j++){
				temp = arrMen[j] - arrWomen[j];
				if(temp < 0)
					temp = -temp;
				sum += temp;
			}
			
			/* 출력부분 */
			System.out.println(sum);
		}
	}

}