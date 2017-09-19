/**************************************************************
    Problem: 1444 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=718&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:275 ms
    Memory:13160 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
         
        int n = Integer.parseInt(br.readLine().trim());
        long v=0, m=0, addv =0;
        String input[] = br.readLine().trim().split(" ");
        for(int i=0; i<n; i++){
            v = Long.parseLong(input[i]);
            if(i == 0){
                m = Math.abs(v);
                addv = v;
            }
            else{
                if(addv >= 0 && v >= 0){
                    m += Math.abs(addv);
                    m += Math.abs(v);
                    addv += v;
                }
                else if(addv >= 0 && v < 0){
                    addv += v;
                    m += Math.abs(addv);
                }
                else if(addv < 0 && v >= 0){
                    addv += v;
                    m += Math.abs(addv);
                }
                else{
                    m += Math.abs(addv);
                    m += Math.abs(v);
                    addv += v;
                }
            }
        }
         
        System.out.println(m);
    }
}