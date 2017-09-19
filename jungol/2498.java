/**************************************************************
    Problem: 2498 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1759&sca=2020)
    User: magicguru
    Language: Java
    Result: Success
    Time:98 ms
    Memory:8420 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
 
public class Main {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String input[] = br.readLine().trim().split(" ");
         
        int bg = Integer.parseInt(input[0]);
        int sm = Integer.parseInt(input[1]);
         
        int an[] = new int[2];
         
        int tmp = sm/bg, before=-1, mok, tmpSum;
        int min=Integer.MAX_VALUE, minVal =-1;
        int j= 0;
        for(int i=1; i<=tmp; i++){
            if(tmp%i != 0){
                continue;
            }
             
            if(before == i){
                break;
            }
             
            mok = tmp/i;
            tmpSum = mok + i;
     
            for(j=2; j<=i; j++){
                if(mok%j == 0 && i%j ==0){
                    break;
                }
            }
             
            if(j <= i){
                continue;
            }
 
            if(tmpSum < min){
                min = tmpSum;
                minVal = i;
            }
            before = mok;
        }
         
        an[0] = minVal * bg;
        an[1] = (tmp/minVal) * bg;
         
        System.out.print(an[0] + " " + an[1]);
    }
}