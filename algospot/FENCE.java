/**************************************************************
    Problem: FENCE (https://algospot.com/judge/problem/read/FENCE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 2144ms
    Memory: 963B
****************************************************************/

import java.util.Scanner;

public class Main{
	static int		Answer;
	
	public static void main(String[] args){
		int T;
		
		Scanner sc = new Scanner(System.in);		
		
		int		i;
		
		/* 테스트 케이스의 수 T */
		T = sc.nextInt();
		sc.nextLine();
				
		/* 각 테스트 케이스에 대한 루프문 */
		for(i = 0; i < T; i++){		
			Answer = 0;
			int N = sc.nextInt();
			int fence[] = new int[N];
			sc.nextLine();
			for(int j =0; j<N; j++){
				fence[j] = sc.nextInt();
			}
			
			for(int j =0; j<N; j++){
				int height = fence[j];
				int width = 1;
				for(int k =j-1; k>=0; k--){
					if(fence[k] >= height){
						width++;
					}
					else{
						break;
					}
				}
				
				for(int k =j+1; k<N; k++){
					if(fence[k] >= height){
						width++;
					}
					else{
						break;
					}
				}
				
				int size = width * height;
				Answer = Answer < size ? size: Answer;
			}
			
			/* 출력부분 */
			System.out.println(Answer);
		}
	}
}