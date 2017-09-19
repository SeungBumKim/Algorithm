/**************************************************************
    Problem: 1395 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=671&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:2159 ms
    Memory:8776 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int st, end, v;
        String input[] = br.readLine().trim().split(" ");
        st = Integer.parseInt(input[0]);
        end = Integer.parseInt(input[1]);
        v = Integer.parseInt(input[2]);
         
         
        long an = 0, tmp = 1;
        for(int i=1; i<end+1; i++){
            tmp *= v;
            if(tmp >=  1000000007){
                tmp = tmp % 1000000007;
            }
 
            if(i >= st){
                an += tmp; 
                if(an >=  1000000007){
                    an = an % 1000000007;
                }
            }
        }
        System.out.println(an);
    }
}