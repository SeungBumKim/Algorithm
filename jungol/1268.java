/**************************************************************
    Problem: 1268 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=551&sca=4030)
    User: magicguru
    Language: Java
    Result: Success
    Time:89 ms
    Memory:8408 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
 
public class Main { 
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String input[] = br.readLine().trim().split(" ");
        long b = Long.parseLong(input[0]);
        String pS = input[1];
        long m = Long.parseLong(input[2]);
        m = makeDec(b, m);
        long an = 0;
        for(int i=0; i<pS.length(); i++){
            an = (an * b) + makeDec(b, pS.charAt(i)-'0');
            if(an >= 1000000000L){
                an = an % m;
            }
        }
        an = an % m;
        an = makeBack(b, an);
        System.out.println(an);
    }
     
    private static long makeDec(long b, long m){
        if(b == 10){
            return m;
        }
        else{
            long ret = 0;
            long k, i=1;
            while(m > 0){
                k = m % 10;
                m = m / 10;
                 
                ret += k * i;
                i *= b;
            }
            return ret;
        }
    }
     
    private static long makeBack(long b, long m){
        if(b == 10){
            return m;
        }
        else{
            long ret = 0;
            long k, i=1;
            while(m > 0){
                k = m % b;
                m = m / b;
                 
                ret += k * i;
                i *= 10;
            }
            return ret;
        }
    }
}