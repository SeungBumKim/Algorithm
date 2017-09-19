/**************************************************************
    Problem: 1354 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=629&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:940 ms
    Memory:26288 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
         
        ArrayList<Integer> arr[] = new ArrayList[n];
         
        int i, j, cnt, val=0;
        arr[0] = new ArrayList<Integer>();
        arr[0].add(1);
        for(i=1; i<n; i++){
            arr[i] = new ArrayList<Integer>();
            cnt = 0;
            for(j=0; j<arr[i-1].size(); j++){
                if(j == 0){
                    cnt++;
                    val = arr[i-1].get(j);
                }
                else{
                    if(arr[i-1].get(j) == val){
                        cnt++;
                    }
                    else{
                        arr[i].add(val);
                        arr[i].add(cnt);
                        cnt = 1;
                        val = arr[i-1].get(j);
                    }
                }
            }
            arr[i].add(val);
            arr[i].add(cnt);
        }
         
        for(i=arr[n-1].size()-1; i>=0; i--){
            System.out.print(arr[n-1].get(i) + " ");
        }
    }
}