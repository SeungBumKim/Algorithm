/**************************************************************
    Problem: 2581 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1842&sca=50)
    User: magicguru
    Language: Java
    Result: Success
    Time:143 ms
    Memory:9144 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main {
    private static int arr[];
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        String input[] = br.readLine().trim().split(" ");
        int max = Integer.parseInt(br.readLine().trim());
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
            sum += arr[i];
        }
 
        int an = 0;
        Arrays.sort(arr);
        if (max >= sum) {
            an = arr[n - 1];
        } else {
            int tmpMax = max, tmp1;
            an = tmpMax / n;
            for (int i = 0; i < n - 1; i++) {
                tmpMax -= arr[i];
                tmp1 = tmpMax / (n - (i + 1));
 
                if (i > 0 && tmp1 < arr[i - 1]) {
                    break;
                }
 
                if (tmp1 > an) {
                    an = tmp1;
                }
            }
        }
 
        System.out.print(an);
    }
}