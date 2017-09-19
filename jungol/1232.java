/**************************************************************
    Problem: 1232 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=515&sca=4030)
    User: magicguru
    Language: Java
    Result: Success
    Time:95 ms
    Memory:8388 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int N, K;
        String input[] = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
         
        int i;
        boolean arr[] = new boolean[N+1];
        int an =0;
        for(i =2; i<N+1; i++){
            arr[i] = false;
        }
         
        int st = 2;
        boolean find = false;
        while(find == false){
            if(arr[st] == false){
                for(i = st; i<N+1; i+=st){
                    if(arr[i] == false){
                        arr[i] = true;
                        an++;
                        if(an == K){
                            find = true;
                            break;
                        }
                    }
                }
            }
            st++;
        }
         
        System.out.println(i);
    }
}