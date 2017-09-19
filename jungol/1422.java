/**************************************************************
    Problem: 1422 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=698&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:548 ms
    Memory:21408 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{
    private static String sample[] = new String[]{"0", "1", "10", "100", "101", "1000", 
        "1001", "1010", "10000", "10001", "10010", "10100", "10101"};
    private static int samNum = 12;
    private static ArrayList<Integer> sum = new ArrayList<Integer>();
    private static ArrayList<Integer> item = new ArrayList<Integer>();
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
         
        int maxNum =-1;
        int n = Integer.parseInt(br.readLine().trim());
        int tab[] = new int[n];
        for(int i=0; i<n; i++){
            tab[i] = Integer.parseInt(br.readLine().trim());
            maxNum = Math.max(maxNum, tab[i]);
        }
 
        sum.add(0, 1);
        sum.add(1, 2);
        sum.add(2, 4);
        item.add(0, 1);     
        item.add(1, 1);
        item.add(2, 2);
        int idx = 2, tmp;
        while(true){
            tmp = item.get(idx) + item.get(idx-1);
            sum.add(idx+1, sum.get(idx) + tmp);
            item.add(idx+1, tmp);
            idx++;
            if(tmp > maxNum){
                break;
            }
        }
 
        for(int i=0; i<n; i++){
            System.out.println(make(tab[i]));
        }       
    }
     
    private static String make(int idx){
        if(idx <= samNum){
            return sample[idx];
        }
         
        int i;
        for(i=0; i<sum.size(); i++){
            if(sum.get(i) >= idx){
                break;
            }
        }
         
        int len = i+1;
        int findIdx = idx - sum.get(i-1) - 1; 
        String subStr = make(findIdx);
         
        int subLen = subStr.length();
        StringBuffer sb = new StringBuffer();
        sb.append("1");
        int loopLen = len - subLen - 1;
        for(i=0; i<loopLen; i++){
            sb.append("0");
        }
        sb.append(subStr);
         
        return sb.toString();
    }
}