/**************************************************************
    Problem: 1459 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=731&sca=2070)
    User: magicguru
    Language: Java
    Result: Success
    Time:107 ms
    Memory:8592 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
 
 
public class Main {
    private static int arr[][];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        arr = new int[2][n];
        for(int i=0; i<n; i++){
            arr[0][i] = i;
            arr[1][i] = Integer.parseInt(br.readLine().trim())-1;
        }
     
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Integer> an = new ArrayList<Integer>();
        int idx=0;
        for(int i=0; i<n; i++){
            if(an.contains(i)){
                continue;
            }
             
            tmp.clear();
            idx = i;
            tmp.add(idx);
            while(true){
                idx = arr[1][idx];
 
                if(idx == i){
                    for(int j=0; j<tmp.size(); j++){
                        an.add(tmp.get(j));
                    }
                    break;
                }
 
                if(tmp.contains(idx)){
                    break;
                }
                 
                tmp.add(idx);
            }
        }
         
        Collections.sort(an);
        System.out.println(an.size());
        for(int i =0; i<an.size(); i++){
            System.out.println(an.get(i)+1);
        }
    }
}