/**************************************************************
    Problem: 2499 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1760&sca=3020)
    User: magicguru
    Language: Java
    Result: Success
    Time:128 ms
    Memory:8856 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
 
public class Main {
    private static ArrayList<Integer> arr = new ArrayList<Integer>();
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int n = Integer.parseInt(br.readLine().trim());
         
        String input[] = br.readLine().trim().split(" ");
        int tmp = 0;
        for(int i=0; i<n; i++){
            tmp = Integer.parseInt(input[i]);
            arr.add(tmp);
        }
 
        Collections.sort(arr);
        int i=0, min = 1;
        if(arr.get(0) != 1){
            System.out.println(1);
        }
        else{
            for(i=1; i<arr.size(); i++){
                if(arr.get(i) > min + 1){
                    System.out.println(min+1);
                    break;
                }
                else{
                    min += arr.get(i); 
                }
            }
        }
         
        if(i >= arr.size()){
            System.out.println(min+1);
        }
    }
}