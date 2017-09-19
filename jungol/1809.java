/**************************************************************
    Problem: 1809 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1082&sca=3010)
    User: magicguru
    Language: Java
    Result: Success
    Time:839 ms
    Memory:20428 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main {
 
    public static void main(String[] args) throws Exception {
        int T;
 
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        T = Integer.valueOf(br.readLine().trim());
        String input[];
        int top[] = new int[T];
        int answer[] = new int[T];
 
        input = br.readLine().trim().split(" ");
        int max = 0;
        for (int i = 0; i < T; i++) {
            top[i] = Integer.valueOf(input[i]);
            if (i == 0) {
                max = top[i];
            } 
            else {
                if (top[i] > max) {
                    max = top[i];
                } 
                else {
                    for (int j = i - 1; j >= 0; j--) {
                        if (top[j] >= top[i]) {
                            answer[i] = j + 1;
                            break;
                        } 
                        else {
                            if (answer[j] != 0) {
                                j = answer[j];
                            } 
                            else {
                                break;
                            }
                        }
                    }
                }
            }
        }
         
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            sb.append(answer[i] + " ");
        }
 
        System.out.print(sb.toString());
    }
}