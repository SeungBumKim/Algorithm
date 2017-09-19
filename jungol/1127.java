/**************************************************************
    Problem: 1127 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=407&sca=4020)
    User: magicguru
    Language: Java
    Result: Success
    Time:102 ms
    Memory:8460 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    private static int arr[][] = new int[10][2];
    private static int ann = Integer.MAX_VALUE;
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
         
        int N = Integer.parseInt(br.readLine().trim());
        String input[];
        int i;
        for(i=0; i<N; i++){
            input = br.readLine().trim().split(" ");
            arr[i][0] = Integer.parseInt(input[0]);
            arr[i][1] = Integer.parseInt(input[1]);
        }
         
        find(0, N, 1, 0, 0);
        System.out.println(ann);
    }
     
    private static void find(int st, int n, int sSum, int bSum, int sub){
        if(st != 0 && ann > sub){
            ann = sub;
        }
         
        int tmpsSum, tmpbSum, tmpSub;
        for(int i=st; i<n; i++){
            tmpsSum = sSum*arr[i][0];
            tmpbSum = bSum+arr[i][1];
            tmpSub = Math.abs(tmpsSum - tmpbSum);
            find(i+1, n, tmpsSum, tmpbSum, tmpSub);
        }       
    }
}