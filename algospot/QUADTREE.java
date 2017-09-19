/**************************************************************
    Problem: QUADTREE (https://algospot.com/judge/problem/read/QUADTREE)
    User: magicguru
    Language: Java
    Result: Success
    Time: 415ms
    Memory: 1.8KB
****************************************************************/

import java.util.Scanner;

public class Main{
	public static class Quad{
		Object quad[] = new Object[4];
		int index = 0;
		
		public boolean input(char c){
			boolean isSuccess = false;
			
			if(this.index > 0 && (quad[this.index-1]).getClass() == Quad.class){
				isSuccess = ((Quad)(quad[this.index-1])).input(c);
			}
			
			if(isSuccess == false && this.index < 4){				
				if(c == 'x'){
					quad[this.index++] = new Quad(); 
				}
				else{
					quad[this.index++] = c;
				}
				
				isSuccess = true;
			}
			
			return isSuccess;
		}
		
		public Object getQuadIdx(int idx){
			return this.quad[idx];
		}
		
		public Object[] getQuad(){
			return this.quad;
		}
		
		public int getIndex(){
			return this.index;
		}
	}
	
	public static void main(String[] args){
		int T;
		Scanner sc = new Scanner(System.in);		
		
		int i;
		char c; 

		T = sc.nextInt();
		sc.nextLine();
		String ret = "";
		for(i = 0; i < T; i++){		
			String t = sc.next();			
			int len = t.length();			

			Quad quad = new Quad();
			for(int j =0; j<len; j++){
				c = t.charAt(j);
				quad.input(c);
			}
			
			ret = getStr(quad, true);
			
			/* 출력부분 */
			System.out.printf("%s\n", ret);
		}
	}
	
	public static String getStr(Quad quad, boolean isFirst){
		String ret = "";
		
		if(!isFirst)
			ret += "x";
		
		int index = quad.getIndex();
			
		for(int j =2; j<index; j++){
			Object o = quad.getQuadIdx(j);
			if(o.getClass() == Quad.class){
				ret += getStr((Quad)o, false);
			}
			else{
				ret += String.valueOf((Character)o);
			}
		}
		
		int max = index >= 2 ? 2 : index;
		for(int j =0; j<max; j++){
			Object o = quad.getQuadIdx(j);
			if(o.getClass() == Quad.class){
				ret += getStr((Quad)o, false);
			}
			else{
				ret += String.valueOf((Character)o);
			}			
		}
		
		return ret;
	}
}