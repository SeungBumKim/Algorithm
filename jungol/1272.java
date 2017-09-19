/**************************************************************
    Problem: 1272 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=555&sca=4030)
    User: magicguru
    Language: Java
    Result: Success
    Time:114 ms
    Memory:8836 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    private static long an, st, end;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String input[] = br.readLine().trim().split(" ");
        st = Long.parseLong(input[0]);
        end = Long.parseLong(input[1]);
         
        an = 0;
        find(4);
        find(7);
        System.out.println(an);
    }
     
    private static void find(long num){
        if(num >= st && num <= end){
            an++;
        }
         
        if(num > end){
            return;
        }
         
        find(num*10+4);
        find(num*10+7);
    }
}