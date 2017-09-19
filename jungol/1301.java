/**************************************************************
    Problem: 1301 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=584&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:203 ms
    Memory:10236 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{
    private static int cows[] = new int[1001];
    private static ArrayList<Integer> cowList = new ArrayList<Integer>();
    private static ArrayList<Integer> lines[] = new ArrayList[1001];
    private static ArrayList<Integer> lines1[] = new ArrayList[1001];
    private static boolean isGo[] = new boolean[1001];
    private static boolean isHistory[] = new boolean[1001];
    private static int history[] = new int[1001];
    private static int an;
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int cowK, farmN, lineM;
        String input[];
        input = br.readLine().trim().split(" ");
        cowK = Integer.parseInt(input[0]);
        farmN = Integer.parseInt(input[1]);
        lineM = Integer.parseInt(input[2]);
         
        int i,j, tmp;
        for(i=0; i<cowK; i++){
            tmp = Integer.parseInt(br.readLine().trim()) - 1;
            cows[tmp]++;
            cowList.add(tmp);
        }
         
        for(i=0; i<farmN; i++){
            history[i] = -1;
            if(lines[i] == null){
                lines[i] = new ArrayList<Integer>();
                lines1[i] = new ArrayList<Integer>();
            }
            else{
                lines[i].clear();
                lines1[i].clear();
            }
        }
         
        int st, end;
        for(i=0; i<lineM; i++){
            input = br.readLine().trim().split(" ");
            st = Integer.parseInt(input[0]) - 1;
            end = Integer.parseInt(input[1]) - 1;
             
            if(!lines[end].contains(st)){
                lines[end].add(st);
                lines1[st].add(end);
            }
        }
         
        int ttt;
        an = 0;             
 
        for(i=0; i<cowK; i++){
            ttt = go(cowList.get(i));
//          System.out.println(ttt);
//          System.out.println("==================");
            if(ttt >= cowK){
                count(cowList.get(i));
                break;
            }
             
            for(j =0; j<farmN; j++){
                isHistory[j] = false;
            }
        }
         
        System.out.println(an);
    }
     
    private static void count(int st){
        if(isGo[st] == true){
            return;
        }
         
        isGo[st] = true;
        an++;
        int n= lines1[st].size();
        for(int i=0; i<n; i++){
            count(lines1[st].get(i));
        }
    }
     
    private static int go(int end){
        if(isHistory[end] == true){
            return 0;
        }
        if(isGo[end] == true){
            return 0;
        }
        if(history[end] >= 0){
            return history[end];
        }
         
        isGo[end] = true;
        int n= lines[end].size();
        int ret = 0;
        for(int i=0; i<n; i++){
            ret += go(lines[end].get(i));
        }
         
        ret += cows[end];
         
        isGo[end] = false;
        history[end] = ret;
        isHistory[end] = true;
         
        //System.out.println(ret);
        return ret;     
    }
}