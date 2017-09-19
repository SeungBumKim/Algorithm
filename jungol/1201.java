/**************************************************************
    Problem: 1201 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=484&sca=4030)
    User: magicguru
    Language: Java
    Result: Success
    Time:289 ms
    Memory:8208 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main {
    private static int an;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        an = Integer.MAX_VALUE;
        find(n, 0);
        System.out.println(an);
    }
     
    private static void find(int n, int cnt){
        if(n == 0){
            if(cnt < an){
                an = cnt;
            }
        }
         
        if(cnt >= an){
            return;
        }
         
        int sq = (int)Math.sqrt(n);
        for(int i=sq; i>=sq/2; i--){
            find(n - (i*i), cnt+1);
        }
         
    }
}