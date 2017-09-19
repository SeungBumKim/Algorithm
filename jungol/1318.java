/**************************************************************
    Problem: 1318 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=597&sca=3080)
    User: magicguru
    Language: Java
    Result: Success
    Time:144 ms
    Memory:11384 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
 
public class Main{
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n;
        int cnt = 5;
        int arr[] = new int[1500];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        int i=7,j;
         
        TreeSet<Integer> list = new TreeSet<Integer>();
        for(j=1; j<cnt; j++){
            if(j != 1){
                list.add(arr[j]*2);
            }
            list.add(arr[j]*3);
            list.add(arr[j]*5);
        }
         
        int tmp, q,w,r;
        while(true){
            tmp = list.pollFirst();
            arr[cnt++] = tmp;
            if(cnt >= 1500){
                break;
            }
             
            q = tmp*2;
            if(q > 0 && q < 1000000000){
                list.add(q);
            }
            w = tmp*3;
            if(w > 0 && w < 1000000000){
                list.add(w);
            }
             
            r = tmp*5;
            if(r > 0 && r < 1000000000){
                list.add(r);
            }
        }
         
        while(true){
            n = Integer.parseInt(br.readLine().trim());
            if(n == 0){
                break;
            }
             
            System.out.println(arr[n-1]);
        }
    }
 
}