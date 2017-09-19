/**************************************************************
    Problem: 1352 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=627&sca=3050)
    User: magicguru
    Language: Java
    Result: Success
    Time:126 ms
    Memory:8516 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
 
public class Main{
    //private static ArrayList<Integer> go[] = new ArrayList[31];
    private static ArrayList<Integer> go3 = new ArrayList<Integer>();
    //private static int go4[];
    private static int go4Cnt = 0;
    private static boolean go1[];
    //private static boolean go2[][] = new boolean[31][20000];
    private static int chu[] = new int[31];
     
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
 
        int arr[] = new int[501];
        int ball[] = new int[8];
        char answer[] = new char[] {'N','N','N','N','N','N','N','N'};
        int chuSum = 0;
         
        int n = Integer.parseInt(br.readLine().trim());
        String input[];
        input = br.readLine().trim().split(" ");
        for(int i = 0; i < n; i++){
            //go[i] = new ArrayList<Integer>();
            chu[i] = Integer.parseInt(input[i]);
            arr[chu[i]]++;
            chuSum += chu[i];
        }
         
        //go4 = new int[chuSum +1];
        go1 = new boolean[chuSum + 1];
     
        make(0, n, 0, 0);       
        //Arrays.sort(go4);
        Collections.sort(go3);
         
        int bn = Integer.parseInt(br.readLine().trim());
        input = br.readLine().trim().split(" ");
         
        int goSum = 0;
        for(int i = 0; i < bn; i++){
            ball[i] = Integer.parseInt(input[i]);
            if(chuSum >= ball[i]){
                if(go1[ball[i]]){
                    answer[i] = 'Y';
                }
                else{
                    for(int j=0; j<go3.size(); j++){
                        goSum = ball[i]+ go3.get(j);
                        if(goSum > chuSum){
                            break;
                        }
                        if(go1[goSum]){
                            answer[i] = 'Y';
                            break;
                        }
                    }
                }
            }
            System.out.print(answer[i] + " ");
        }
    }
     
    private static void make(int st, int len, int n, int sum){
        if(st >= len){
            return;
        }
         
        int tmpSum;
        for(int i=st; i<len ;i++){
            tmpSum = sum + chu[i];
            if(go1[tmpSum] == false){
                //go4[go4Cnt++] = tmpSum;
                go3.add(tmpSum);
                //go[n].add(tmpSum);
                go1[tmpSum] = true;
                //go2[n][tmpSum] = true;    
                make(i+1, len, n+1, tmpSum);
            }           
        }
    }
}