/**************************************************************
    Problem: 1400 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=676&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:1284 ms
    Memory:17072 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{
    private static long val[];
    private static ArrayList<Integer> arrList[];
    private static int stIdx, anIdx;
    private static long an; 
    private static long hisVal[][];
    private static int hisIdx[][];  
    private static boolean isGo[];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
        val = new long[n];
        arrList = new ArrayList[n];
         
        hisVal = new long[2][n];
        hisIdx = new int[2][n];
        isGo = new boolean[n];
         
        int i;
        for(i =0; i< n; i++){
            val[i] = Long.parseLong(br.readLine().trim());
            arrList[i] = new ArrayList<Integer>();
            hisIdx[0][i] = -1;
            hisIdx[1][i] = -1;
            hisVal[0][i] = -1;
            hisVal[1][i] = -1;
        }
         
        String input[];
        int fr, to;
        for(i=0; i<n-1; i++){
            input = br.readLine().trim().split(" ");
            fr = Integer.parseInt(input[0]) - 1;
            to = Integer.parseInt(input[1]) - 1;            
            arrList[fr].add(to);
            arrList[to].add(fr);
        }
         
        an = -1;
        for(i=0; i<n; i++){
            stIdx = i;
            searchTree(i, -1, -1);
        }
         
        System.out.println(an + " " + (anIdx+1));
    }
     
    private static long searchTree(int st, int fr, long toss){
        long ret=-1, ret2=-1, tmpRet;
        int retIdx=-1, retIdx2=-1;
        int idx=-1;
        long tmpToss;
         
        if(hisVal[0][st] != -1){
            if(hisIdx[0][st] == fr){
                if(hisVal[1][st] != -1){
                    tmpToss = toss == -1 ? toss : toss + hisVal[1][st]; 
                    if(an < tmpToss){
                        an = tmpToss;
                        anIdx = stIdx;
                    }
                    return hisVal[1][st];
                }
            }
            else{
                tmpToss = toss == -1 ? toss : toss + hisVal[0][st]; 
                if(an < tmpToss){
                    an = tmpToss;
                    anIdx = stIdx;
                } 
                 
                return hisVal[0][st];
            }
        }
         
        if(isGo[st] == true){
            return -1;
        }
         
        isGo[st] = true;
         
        int tmpSt = st;
        int tmpFr = fr;
        long tosst = toss;
        long retRet = 0;
        int firstIdx  = -1;
         
        if(fr != -1 && arrList[st].size() == 2){
            while(arrList[tmpSt].size() == 2){
                for(int j=0; j<arrList[tmpSt].size(); j++){
                    idx = arrList[tmpSt].get(j);
                    if(idx == tmpFr){
                        continue;
                    }
                    if(firstIdx == -1){
                        firstIdx = idx;
                    }
                    else{
                        retRet += val[tmpSt];
                    }
                    tosst = tosst == -1 ? val[tmpSt] : tosst + val[tmpSt];                  
                    tmpFr = tmpSt;
                    tmpSt = idx;
                    break;
                }
            }
             
            int oldSt = st;
            int oldFr = fr;
            long oldToss = toss;
            st = tmpSt;
            fr = tmpFr;
            toss = tosst;           
             
            long Tret=-1;
            for(int i=0; i<arrList[st].size(); i++){
                idx = arrList[st].get(i);
                if(idx == fr){
                    continue;
                }
                tmpRet = searchTree(idx, st, toss == -1 ? val[st] : toss + val[st]);
                if(tmpRet != -1 && Tret <= tmpRet){
                    Tret = tmpRet;
                }
            }
             
            if(Tret != -1){
                Tret += val[st];
            }
            else{
                tmpToss = toss == -1 ? val[st] : toss + val[st];
                if(an < tmpToss){
                    an = tmpToss;
                    anIdx = stIdx;
                }
                Tret = val[st];
            }
             
            ret = Tret + retRet;
            retIdx = firstIdx;
                         
            st = oldSt;
            fr = oldFr;
            toss = oldToss;
        }
        else{
            for(int i=0; i<arrList[st].size(); i++){
                idx = arrList[st].get(i);
                if(idx == fr){
                    continue;
                }
                tmpRet = searchTree(idx, st, toss == -1 ? val[st] : toss + val[st]);
                if(tmpRet != -1 && ret <= tmpRet){
                    ret2 = ret;
                    retIdx2 = retIdx;
                    ret = tmpRet;
                    retIdx = idx;
                }
            }
        }
         
        if(ret != -1){
            ret += val[st];
            if(st == stIdx){
                hisVal[0][st] = ret;
                hisIdx[0][st] = retIdx;
                if(ret2 != -1){
                    hisVal[1][st] = ret2 + val[st];
                    hisIdx[1][st] = retIdx2;
                }
            }
        }
        else{
            tmpToss = toss == -1 ? val[st] : toss + val[st];
            if(an < tmpToss){
                an = tmpToss;
                anIdx = stIdx;
            }
            ret = val[st];
        }
        isGo[st] = false;
         
        return ret;
    }
}