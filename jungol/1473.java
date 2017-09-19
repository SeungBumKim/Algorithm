/**************************************************************
    Problem: 1473 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=745&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:99 ms
    Memory:8528 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
 
public class Main{
    private static char w[];
    private static char cmp[];
    private static ArrayList<String> anList = new ArrayList<String>();
    private static int memo[][];
    private static int wLen, wLen2, cLen;
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        w = br.readLine().trim().toCharArray();
        int n = Integer.parseInt(br.readLine().trim());
         
        int i;
        wLen2 = 0;
        for(i =0; i<w.length; i++){
            if(w[i] == '*'){
                continue;
            }
            wLen2++;
        }
         
        wLen = w.length;
        for(i =0; i<n; i++){
            cmp = br.readLine().trim().toCharArray();
            cLen = cmp.length;
             
            memo = new int[wLen][cLen];         
            if(cLen < wLen2){
                continue;
            }
             
            if(find(0, 0) > 0){
                anList.add(String.valueOf(cmp));
            }
        }
         
        Collections.sort(anList);
        for(i =0; i<anList.size(); i++){
            System.out.println(anList.get(i));
        }
    }
     
    private static int find(int wIdx, int cIdx){
        int ret = -1;
         
        if(wIdx >= wLen && cIdx >= cLen){
            return 1;
        }
        else if(wIdx >= wLen || cIdx >= cLen){
            return -1;
        }
         
        if(memo[wIdx][cIdx] != 0){
            return memo[wIdx][cIdx];
        }
         
        if(w[wIdx] == '?'){
            ret = find(wIdx+1, cIdx+1);
        }
        else if(w[wIdx] == '*'){
            int i;
            if(wIdx == (wLen - 1)){
                ret = 1;
            }
            else{
                for(i = cIdx; i<cLen; i++){
                    if(find(wIdx + 1, i) == 1){
                        ret = 1;
                        break;
                    }
                }
            }
        }
        else{
            if(w[wIdx] == cmp[cIdx]){
                ret = find(wIdx+1, cIdx+1);
            }
        }
         
        memo[wIdx][cIdx] = ret;
        return ret;
    }
}