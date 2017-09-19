/**************************************************************
    Problem: 1396 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=672&sca=50)
    User: magicguru
    Language: Java
    Result: Success
    Time:146 ms
    Memory:9164 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main{  
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
        int x[] = new int[n];
        int y[] = new int[n];
        int a[] = new int[n];
        int t[] = new int[n];
         
        String input[];
        for(int i=0; i<n; i++){
            input = br.readLine().trim().split(" ");
             
            x[i] = Integer.parseInt(input[0]);
            y[i] = Integer.parseInt(input[1]);
            t[i] = (x[i] * 10000) + y[i];
        }
         
        Arrays.sort(t);
         
        int tX, tX1, tY, tY1;
        for(int i=0; i<n; i++){
            if(i == 0){
                a[i] = 0;
            }
            else{
                tY = t[i] % 10000;
                for(int j=i-1; j>=0; j--){
                    if((t[j] % 10000) >= tY){
                        a[i] = j+1; 
                        break;
                    }
                    j = a[j];                       
                }
            }
        }
         
        int sum =0, j;
        boolean reSet = false;
        for(int i=n-1; i>0; i--){
            tX =  t[i] / 10000;
            tY =  t[i] % 10000;
            if(a[i] == 0){
                for(j=i-1; j>0; j--){
                    if(a[j] == 0){
                        break;
                    }
                }
                if(i == n-1){
                    sum += tY;
                }
                 
                i = j+1;
                 
                tX1 = t[j]/10000;
                tY1 = t[j]%10000;
                sum += (tX-tX1) * tY1;
                if(reSet){
                    sum += tY;
                    reSet = false;
                }
            }
            else{
                tX1 = t[a[i]-1]/10000;
                tY1 = t[a[i]-1]%10000;
                sum += (tX-tX1) * tY;
                i = a[i];
                reSet = true;
                 
                if(i == 1){
                    sum += tY1;
                }
            }
        }
         
        System.out.print(sum);
    }
}