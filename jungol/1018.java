/**************************************************************
    Problem: 1018 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=297&sca=50)
    User: magicguru
    Language: Java
    Result: Success
    Time:118 ms
    Memory:9204 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String input[] = br.readLine().trim().split(" ");
        int in[] = new int[4];
        for (int i = 0; i < 4; i++) {
            in[i] = Integer.parseInt(input[i]);
        }
         
        int tmp, minIdx= 0, minVal = Integer.MAX_VALUE, idx;
        int mul;
        for (int i = 0; i < 4; i++) {
            mul = 1000;
            tmp = 0;
            for(int j=0; j<4; j++){
                idx = i + j >= 4 ? i + j - 4 : i +j;
                tmp += in[idx] * mul;
                mul /= 10;
            }
             
            if(tmp < minVal){
                minVal = tmp;
                minIdx = i;
            }
        }
         
        int go[] = new int[4];
        int idxx =0;
        for (int i = 0; i < 4; i++) {
            go[idxx++] = in[minIdx++];
            if(minIdx >= 4){
                minIdx = 0;
            }
        }
         
        int st[] = new int[] {1,1,1,1};
        int a = 1, num = 0;
        int cmp = minVal;
        minVal = Integer.MAX_VALUE;
        while(true){
            if(go[0] == st[0] && go[1] == st[1] && go[2] == st[2] && go[3] == st[3]){
                break;
            }
             
            st[3]++;
            if (st[3] >= 10) {
                st[3] = 0;
                st[2]++;
            }
            if (st[2] >= 10) {
                st[2] = 0;
                st[1]++;
            }
            if (st[1] >= 10) {
                st[1] = 0;
                st[0]++;
            }
            if (st[0] >= 10) {
                break;
            }
            num = (st[0] * 1000) + (st[1] * 100) + (st[2] * 10) + st[3];
             
             
            minVal = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                mul = 1000;
                tmp = 0;
                for(int j=0; j<4; j++){
                    idx = i + j >= 4 ? i + j - 4 : i +j;
                    tmp += st[idx] * mul;
                    mul /= 10;
                }
                 
                if(tmp < minVal){
                    minVal = tmp;
                }
            }
             
            if(minVal == num){
                a++;
            }
        }
         
        System.out.println(a);
    }
}