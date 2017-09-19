/**************************************************************
    Problem: BOOKSTORE (https://algospot.com/judge/problem/read/BOOKSTORE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1016ms
    Memory: 1.8KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
 
public class Main{
	private static Book books[][];
	
	private static class Book implements Comparable<Book>{
		int val;
		int point;
		
		public Book(int val, int point){
			this.val = val;
			this.point = point;
		}
		public int getVal(){
			return this.val;
		}
		public int getPoint(){
			return this.point;
		}
		@Override
		public int compareTo(Book o) {
			if(this.point > o.getPoint()){
				return -1;
			}
			else if(this.point < o.getPoint()){
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
		int n, m, i, j, tmp, tmpPoint, tmpBuy;
		String input[];
		for(int t= 0; t< T; t++){
			input = br.readLine().trim().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			books = new Book[m][n];
			for(i=0; i<n;i++){
				input = br.readLine().trim().split(" ");
				for(j=0;j<m;j++){
					books[j][i] = new Book(Integer.parseInt(input[j*2]), Integer.parseInt(input[j*2+1]));
				}
			}
			int an = Integer.MAX_VALUE;
			for(i=0;i<m;i++){
				Arrays.sort(books[i]);
				tmp = 0;
				tmpPoint = 0;
				for(j=0; j<n; j++){
					tmpBuy = books[i][j].getVal() - tmpPoint;
					if(tmpBuy < 0){
						tmpPoint = -tmpBuy;
						tmpBuy = 0;
					}
					else{
						tmpPoint = 0;
					}
					tmp += tmpBuy;
					tmpPoint += books[i][j].getPoint();
				}
				
				an = Math.min(an, tmp);
			}
			System.out.println(an);
		}
    }
}