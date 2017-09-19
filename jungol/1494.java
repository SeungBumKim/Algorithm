/**************************************************************
    Problem: 1494 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=766&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:905 ms
    Memory:20940 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    private static int map[][];
    private static int N, M, T;
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String input[] = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);
         
        map = new int[N+1][N+1];
        //map2 = new int[N+1][N+1];
        int i, f, t, h, j, k;
        for(i =1; i<N+1; i++){
            for(j =1; j<N+1; j++){
                map[i][j] = Integer.MAX_VALUE;
            }
        }
         
        for(i=0; i<M; i++){
            input = br.readLine().trim().split(" ");
            f = Integer.parseInt(input[0]);
            t = Integer.parseInt(input[1]);
            h = Integer.parseInt(input[2]);
             
            if(map[f][t] > 0){
                map[f][t] = Math.min(map[f][t], h);
            }
            else{
                map[f][t] = h;
            }
        }
         
        for(k =1; k<N+1; k++){
            for(i =1; i<N+1; i++){
                if(map[i][k] == Integer.MAX_VALUE) continue;
                for(j =1; j<N+1; j++){
                    if(i == j)  continue;
                    map[i][j] = Math.min(Math.max(map[i][k] , map[k][j]), map[i][j]);
                }
            }
        }
 
        for(i =0; i<T; i++){
            input = br.readLine().trim().split(" ");
            f = Integer.parseInt(input[0]);
            t = Integer.parseInt(input[1]);
             
            System.out.println(map[f][t] == Integer.MAX_VALUE ? -1 : map[f][t]);
        }
    }
}