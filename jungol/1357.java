/**************************************************************
    Problem: 1357 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=632&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:1998 ms
    Memory:30476 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main{
    private static int N, idx;
    private static int map[][];
    private static int l[], r[];
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][4];
        int i, j;
        StringTokenizer st;
        for (i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
            map[i][3] = Integer.parseInt(st.nextToken());
        }
        idx = 0;
        int an = 0;
         
        l = new int[N * N];
        r = new int[N * N];
 
        idx = 0;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                l[idx] = map[i][0] + map[j][1];
                r[idx++] = map[i][2] + map[j][3];
            }
        }
 
        Arrays.sort(l);
        Arrays.sort(r);
     
        an += cal();
         
        System.out.println(an);
    }
     
    private static int cal(){
        int an = 0, lidx = 0, ridx = idx-1 ,lval, rval, lcnt, rcnt, tmp;
        while (lidx < idx && ridx > -1){
            lval = l[lidx];
            rval = r[ridx];
             
            if (lval == -rval) {
                lcnt = 1;
                rcnt = 1;
 
                lidx++;
                tmp = -rval;
                while(lidx < idx && tmp == l[lidx]){
                    lidx++;
                    lcnt++;
                }
                ridx--;
                tmp = -lval;
                while(ridx > -1 && tmp == r[ridx]){
                    ridx--;
                    rcnt++;
                }
                 
                an += lcnt * rcnt;
            }           
            else if(lval < 1 && rval > -1){
                if(-lval < rval){
                    ridx--;
                    tmp = -lval;
                    while(ridx > -1 && tmp < r[ridx]){
                        ridx--;
                    }
                }
                else{
                    lidx++;
                    tmp = -rval;
                    while(lidx < idx && l[lidx] < tmp){
                        lidx++;
                    }
                }
            }
            else if(lval > -1 && rval < 1){
                if(lval < -rval){
                    lidx++;
                    tmp = -rval;
                    while(lidx < idx && l[lidx] < tmp){
                        lidx++;
                    }
                }
                else{
                    ridx--;
                    tmp = -lval;
                    while(ridx > -1 && tmp < r[ridx]){
                        ridx--;
                    }
                }
            }
            else if(lval < 0 && rval < 0){
                lidx++;
                while(lidx < idx && l[lidx] < 0){
                    lidx++;
                }
            }
            else{
                ridx--;
                while(ridx > -1 && r[ridx] > 0){
                    ridx--;
                }
            }
        }
         
        return an;
    }
}