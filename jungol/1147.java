/**************************************************************
    Problem: 1147 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=427&sca=2070)
    User: magicguru
    Language: Java
    Result: Success
    Time:311 ms
    Memory:11028 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
 
public class Main {
    private static int sq[][];
    private static int loc[][];
    private static int skip[] = new int[] {5, 3, 4, 1, 2, 0};
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        sq = new int[n][6];
        loc = new int[n][6];
        String input[];
        for(int i=0; i<n; i++){
            input = br.readLine().trim().split(" ");
            for(int j=0; j<6; j++){
                sq[i][j] = Integer.parseInt(input[j]);
                loc[i][sq[i][j]-1] = j;
            }
        }
         
        int st;
        int max = -1, tmp = -1;
        for(int i=0; i<6; i++){
            st = i;
            tmp = 0;
            for(int j=0; j<n; j++){
                tmp += find(st, j);
                if(j+1 < n){
                    st = skip[loc[j+1][sq[j][st]-1]];
                }
            }
             
            if(tmp > max){
                max = tmp;
            }
        }
         
        System.out.println(max);
    }
     
    private static int find(int idx, int l){
        int retVal = -1;
         
        for(int i=0; i<6; i++){
            if(i == idx || i == skip[idx]){
                continue;
            }
             
            if(sq[l][i] > retVal){
                retVal = sq[l][i];
            }
        }       
         
        return retVal;
    }
}