/**************************************************************
    Problem: 2794 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2054&sca=50)
    User: magicguru
    Language: Java
    Result: Success
    Time:199 ms
    Memory:10540 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main {
    private static int arr[][] = new int[1001][1001]; 
    private static int an[];
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        an = new int[n];
        int x,y,w,h;
        String input[];
        for(int i=0; i<n; i++){
            input = br.readLine().trim().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);
            h = Integer.parseInt(input[3]);
             
            for(int j=x; j<x+w; j++){
                for(int k=y; k<y+h; k++){
                    arr[j][k] = i+1;
                }
            }
        }
         
         
        for(int i=0; i<1001; i++){
            for(int j=0; j<1001; j++){
                if(arr[i][j] > 0){
                    an[arr[i][j]-1]++;
                }
            }
        }
         
        for(int i=0; i<n; i++){
            System.out.println(an[i]);
        }       
    }
}