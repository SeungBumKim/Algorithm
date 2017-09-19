/**************************************************************
    Problem: 2588 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1849&sca=30a0)
    User: magicguru
    Language: Java
    Result: Success
    Time:428 ms
    Memory:10552 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
 
public class Main {
    private static int cookNum, term;
    private static int faNum, maNum, meNum;
    private static int faArr[], maArr[], meArr[];
    private static int faMoveArr[], maMoveArr[], meMoveArr[];
    private static int faPos, maPos, mePos;
    private static int minMove = Integer.MAX_VALUE;
    private static int history[][][][];
    //private static HashMap<Long, Integer> hi = new HashMap<Long, Integer>();
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        cookNum = Integer.parseInt(br.readLine().trim());
        String input[];
 
        term = cookNum/3;
         
        input = br.readLine().trim().split(" ");
        faNum = Integer.parseInt(input[0]);
        faArr = new int[faNum];
        faMoveArr = new int[faNum];
        for(int i=0; i<faNum; i++){
            faArr[i] = Integer.parseInt(input[i+1]);
        }
        for(int i=faNum-1; i>=0; i--){
            if(i == faNum-1){
                faMoveArr[i] = 0;
            }
            else{
                faMoveArr[i] += Math.abs(faArr[i] - faArr[i+1]);
            }
        }
        faPos = 1;
         
        input = br.readLine().trim().split(" ");
        maNum = Integer.parseInt(input[0]);
        maArr = new int[maNum];
        maMoveArr = new int[maNum];
        for(int i=0; i<maNum; i++){
            maArr[i] = Integer.parseInt(input[i+1]);
        }
        for(int i=maNum-1; i>=0; i--){
            if(i == maNum-1){
                maMoveArr[i] = 0;
            }
            else{
                maMoveArr[i] += Math.abs(maArr[i] - maArr[i+1]);
            }
        }
        maPos = faPos + term;
         
        input = br.readLine().trim().split(" ");
        meNum = Integer.parseInt(input[0]);
        meArr = new int[meNum];
        meMoveArr = new int[meNum];
        for(int i=0; i<meNum; i++){
            meArr[i] = Integer.parseInt(input[i+1]);
        }
        for(int i=meNum-1; i>=0; i--){
            if(i == meNum-1){
                meMoveArr[i] = 0;
            }
            else{
                meMoveArr[i] += Math.abs(meArr[i] - meArr[i+1]);
            }
        }
        mePos = maPos + term;   
         
        history = new int[3][faNum+1][maNum+1][meNum+1];
         
        System.out.println(make(0, 0, 0, 0, 0, -1));
    }
     
    private static int make(int fa, int ma, int me, int move, int sum, int who){
        if(fa >= faNum && ma >= maNum && me >= meNum){
            if(sum < minMove){
                minMove = sum;
            }
            return move;
        }
 
//      if(sum >= minMove){
//          //System.out.println("Sum: " + sum + ", " + minMove);
//          return sum+1;
//      }
         
        if(who != -1 && history[who][fa][ma][me] > 0){
            return move + history[who][fa][ma][me];
        }
         
//      long hiVal =  (fa * 1000000000000L) + (ma * 1000000000L) + (me * 1000000) + faPos;
//      if(hi.containsKey(hiVal)){
//          //System.out.println("hiVal : " + hiVal + ", val : " + hi.get(hiVal));
//          int tmpSum = sum + hi.get(hiVal);
//          if(tmpSum < minMove){
//              minMove = tmpSum;
//          }       
//          return move + hi.get(hiVal);
//      }
         
        int ret = Integer.MAX_VALUE, minRet = Integer.MAX_VALUE;
        int oldFaPos, oldMaPos, oldMePos;
        int tmpMove;
        int faTmp, maTmp, meTmp;
        if(fa < faNum){
            oldFaPos = faPos;
            oldMaPos = maPos;
            oldMePos = mePos;
             
            tmpMove = Math.min(Math.abs(oldFaPos - faArr[fa]), Math.abs(Math.min(oldFaPos, faArr[fa])+cookNum-Math.max(oldFaPos,faArr[fa])));
            faPos = faArr[fa];
            faTmp = fa+1;
             
            maPos = faPos + term;
            if(maPos > cookNum){
                maPos = maPos - cookNum;
            }
            if(ma < maNum){
                maTmp = maArr[ma] == maPos ? ma + 1 : ma;
            }
            else{
                maTmp = ma;
            }
                         
            mePos = maPos + term;
            if(mePos > cookNum){
                mePos = mePos - cookNum;
            }
            if(me < meNum){
                meTmp = meArr[me] == mePos ? me + 1 : me;
            }
            else{
                meTmp = me;
            }
             
            ret = make(faTmp, maTmp, meTmp, Math.abs(tmpMove), sum + Math.abs(tmpMove), 0);
             
            faPos = oldFaPos;
            maPos = oldMaPos;
            mePos = oldMePos;           
        }
        if(ret < minRet){
            minRet = ret;
        }
         
        if(ma < maNum){
            oldFaPos = faPos;
            oldMaPos = maPos;
            oldMePos = mePos;
             
            tmpMove = Math.min(Math.abs(oldMaPos - maArr[ma]), Math.abs(Math.min(oldMaPos, maArr[ma])+cookNum-Math.max(oldMaPos,maArr[ma])));
            maPos = maArr[ma];
            maTmp = ma+1;
             
 
            mePos = maPos + term;
            if(mePos > cookNum){
                mePos = mePos - cookNum;
            }
            if(me < meNum){
                meTmp = meArr[me] == mePos ? me + 1 : me;
            }
            else{
                meTmp = me;
            }
             
            faPos = mePos + term;
            if(faPos > cookNum){
                faPos = faPos - cookNum;
            }
            if(fa < faNum){
                faTmp = faArr[fa] == faPos ? fa + 1 : fa;
            }
            else{
                faTmp = fa;
            }
                         
            ret = make(faTmp, maTmp, meTmp, Math.abs(tmpMove), sum + Math.abs(tmpMove), 1);
             
            faPos = oldFaPos;
            maPos = oldMaPos;
            mePos = oldMePos;       
        }
        if(ret < minRet){
            minRet = ret;
        }
         
        if(me < meNum){
            oldFaPos = faPos;
            oldMaPos = maPos;
            oldMePos = mePos;
             
            tmpMove = Math.min(Math.abs(oldMePos - meArr[me]), Math.abs(Math.min(oldMePos, meArr[me])+cookNum-Math.max(oldMePos,meArr[me])));
            mePos = meArr[me];
            meTmp = me+1;
             
            faPos = mePos + term;
            if(faPos > cookNum){
                faPos = faPos - cookNum;
            }
            if(fa < faNum){
                faTmp = faArr[fa] == faPos ? fa + 1 : fa;
            }
            else{
                faTmp = fa;
            }
             
            maPos = faPos + term;
            if(maPos > cookNum){
                maPos = maPos - cookNum;
            }
            if(ma < maNum){
                maTmp = maArr[ma] == maPos ? ma + 1 : ma;
            }
            else{
                maTmp = ma;
            }
             
            ret = make(faTmp, maTmp, meTmp, Math.abs(tmpMove), sum + Math.abs(tmpMove), 2);
             
            faPos = oldFaPos;
            maPos = oldMaPos;
            mePos = oldMePos;   
        }       
         
        if(ret < minRet){
            minRet = ret;
        }
         
        if(who != -1){
            history[who][fa][ma][me] = minRet;
        }
        //hi.put(hiVal, minRet);
//      
//      if(hi.containsKey(hiVal)){
//          System.out.println("hi hiVal: " + hiVal + ", val : " + hi.get(hiVal));
//          System.out.println("Af hiVal : " + hiVal + ", minRet : " + minRet);
//      }
//      else{
//          System.out.println("IN hiVal : " + hiVal + ", minRet : " + minRet);
//          hi.put(hiVal, minRet);
//      }
 
        return minRet + move;
    }   
}