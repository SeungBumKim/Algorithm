/**************************************************************
    Problem: 1510 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=782&sca=3050)
    User: magicguru
    Language: Java
    Result: Success
    Time:130 ms
    Memory:8424 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main{
    private static Paper arr[];
    private static int arrMax[];
    private static int max;
     
    private static class Paper implements Comparable<Paper>{
        int b;
        int s;
         
        public Paper(int b, int s){
            this.b = b;
            this.s = s;
        }
         
        public int getB(){
            return this.b;
        }
        public int getS(){
            return this.s;
        }
 
        @Override
        public int compareTo(Paper arg0) {
            if(this.b < arg0.getB()){
                return -1;
            }
            else if(this.b > arg0.getB()){
                return 1;
            }
            else{
                if(this.s < arg0.getS()){
                    return -1;
                }
                else if(this.s > arg0.getS()){
                    return 1;
                }
                return 0;
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        arr = new Paper[n];
        arrMax = new int[n];
        String input[];
        int in1, in2, bg, sm;
        for(int i =0; i<n; i++){
            input = br.readLine().trim().split(" ");
            in1 = Integer.parseInt(input[0]);
            in2 = Integer.parseInt(input[1]);
             
            if(in1 > in2){
                bg = in1;
                sm = in2;
            }
            else{
                bg = in2;
                sm = in1;
            }
             
            arr[i] = new Paper(bg, sm);
        }
         
        Arrays.sort(arr);       
         
        max=0;
        int j;
        for(int i =0; i<n; i++){
            if(i == 0){
                arrMax[i] = 1;
            }
            else{
                for(j=i-1; j>=0; j--){
                    if(arr[j].getB() <= arr[i].getB() && arr[j].getS() <= arr[i].getS()){
                        arrMax[i] = arrMax[i] > arrMax[j] + 1 ? arrMax[i] : arrMax[j] + 1;
                    }
                }
                 
                if(arrMax[i] == 0){
                    arrMax[i] = 1;
                }
            }
             
            if(arrMax[i] > max){
                max = arrMax[i];
            }
        }
         
        System.out.println(max);
    }
}