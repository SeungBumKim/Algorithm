/**************************************************************
    Problem: GAME (https://algospot.com/judge/problem/read/GAME)
    User: magicguru
    Language: Java
    Result: Success
    Time: 4719ms
    Memory: 1.8KB
****************************************************************/

import java.util.Scanner;

public class Main{
	static int Answer[] = null;
	
	public static void main(String[] args){
		int T;		

		Scanner sc = new Scanner(System.in);		
		
		int		i;
		
		/* 테스트 케이스의 수 T */
		T = Integer.parseInt(sc.nextLine());
		String input1[], input2[];
		boolean passmen[] = new boolean[501];
		int temp[] = new int[501];
		int arr[][] = new int[501][501];		
		
		/* 각 테스트 케이스에 대한 루프문 */
		for(i = 0; i < T; i++){		
			Answer = null;
			int answerSize = 0;
			
			input1 = sc.nextLine().split(" ");
			int man = Integer.parseInt(input1[0]);
			int call = Integer.parseInt(input1[1]);

			for(int j =0; j<man ; j++){
				input2 = sc.nextLine().split(" ");
				for(int k =0; k< call ; k++){
					arr[j][k] = Integer.parseInt(input2[k]);
				}
				passmen[j] = false;
			}

			for(int j =0; j<call; j++){
				int min = 9999;
				int tempInx = 0;
				for(int k =0; k< man; k++){
					if(passmen[k] == true){
						continue;
					}
					
					if(min == arr[k][j]){
						temp[tempInx++] = k;
					}
					
					if(min > arr[k][j]){
						tempInx = 0;
						min = arr[k][j];
						temp[tempInx++] = k;						
					}
				}
				
				if(tempInx == 1){
					passmen[temp[0]] = true;
				}
				else if(tempInx > 1){
					Answer = temp;
					answerSize = tempInx;
					break;
				}
			}

			boolean isFisrt = false;
			if(Answer == null){
				for(int k =0; k <man; k++){
					if(passmen[k] == false){
						if(!isFisrt){
							System.out.printf(" ");
						}
						System.out.printf("%d", k+1);
					}
				}
			}
			else{
				for(int k =0; k< answerSize; k++){
					if(!isFisrt){
						System.out.printf(" ");
					}
					System.out.printf("%d", Answer[k]+1);
				}
			}
			System.out.println("");
		}
	}
}