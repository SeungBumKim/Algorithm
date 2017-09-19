/**************************************************************
    Problem: 1446 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=720&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:110 ms
    Memory:8624 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    private static long table[][];
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
         
        String input[];
        input = br.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        input = br.readLine().trim().split(" ");
        int beauty = Integer.parseInt(input[0]);
        int ugly = Integer.parseInt(input[1]);
         
        table = new long[m+1][n+1];
        makeTable(n, m);
        long an = table[m][n] - table[m][n-1];
        if(an < 0){
            an += 1000000000;
        }
         
        System.out.println(an);
    }
     
    private static void makeTable(int n, int m){
        for(int j=1; j<=m;  j++){
            for(int i =1; i<=n; i++){
                if(i == j || j == 1){
                    table[j][i] = 1;
                }
                else if(i > j){
                    table[j][i] = table[j-1][i-1] + j*table[j][i-1];
                    table[j][i] = table[j][i] % 1000000000;
                }
            }
        }
    }
}