/**************************************************************
    Problem: 1155 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=435&sca=4020)
    User: magicguru
    Language: Java
    Result: Success
    Time:218 ms
    Memory:10204 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
 
public class Main{
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
        String str = br.readLine().trim();
         
        HashMap<Long, Integer> cnt = new HashMap<Long, Integer>();
         
        int stIdx= 0, endIdx =0, max =-1, val;
        int aCnt =0, cCnt =0, gCnt=0, tCnt=0;
        long key;
        char tmp, tmp1;
        int strCnt = str.length();
        for(int i=0; i<strCnt; i++){
            if(endIdx >= strCnt){
                break;
            }
             
            if(endIdx - stIdx + 1 > n){
                tmp1 = str.charAt(stIdx++);
                tmp = str.charAt(endIdx++);
                 
                switch(tmp){
                    case 'A':
                        aCnt++;
                        break;
                    case 'C':
                        cCnt++;
                        break; 
                    case 'G':
                        gCnt++;
                        break; 
                    case 'T':
                        tCnt++;
                        break; 
                }
                 
                switch(tmp1){
                    case 'A':
                        aCnt--;
                        break;
                    case 'C':
                        cCnt--;
                        break; 
                    case 'G':
                        gCnt--;
                        break; 
                    case 'T':
                        tCnt--;
                        break; 
                }
                 
                key = (aCnt * 1000000000000L) + (cCnt * 100000000) + (gCnt * 10000) + tCnt;
                if(!cnt.containsKey(key)){
                    val = 1;
                    cnt.put(key, val);
                }
                else{
                    val = cnt.get(key);
                    cnt.put(key, ++val);
                }
                if(val > max){
                    max = val;
                }
            }
            else{
                tmp = str.charAt(endIdx++); 
                 
                switch(tmp){
                    case 'A':
                        aCnt++;
                        break;
                    case 'C':
                        cCnt++;
                        break; 
                    case 'G':
                        gCnt++;
                        break; 
                    case 'T':
                        tCnt++;
                        break; 
                }
            }
             
            if(endIdx == n){
                key = (aCnt * 1000000000000L) + (cCnt * 100000000) + (gCnt * 10000) + tCnt;
                if(!cnt.containsKey(key)){
                    val = 1;
                    cnt.put(key, val);
                }
                else{
                    val = cnt.get(key);
                    cnt.put(key, ++val);
                }
                if(val > max){
                    max = val;
                }
            }
        }
         
        System.out.println(max);
    }
}