/**************************************************************
    Problem: 1019 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=298&sca=3050)
    User: magicguru
    Language: Java
    Result: Success
    Time:743 ms
    Memory:12424 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
  
public class Main{
    private static int train[];
    private static int trainSet[]; 
    private static boolean go[]; 
    private static int max = Integer.MIN_VALUE;
    private static ArrayList<Box> box = new ArrayList<Box>();
      
    private static class Box implements Comparable<Box>{
        int idx;
        int n;
         
        public Box(int idx, int n){
            this.idx = idx;
            this.n = n;
        }
         
        public int getIdx(){
            return this.idx;
        }
         
        public int getN(){
            return this.n;
        }
 
        @Override
        public int compareTo(Box o) {
            if(this.n > o.getN()){
                return -1;
            }
            else if(this.n < o.getN()){
                return 1;
            }
            else{
                return 0;
            }
        }       
         
    }
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
          
          
        int n = Integer.parseInt(br.readLine().trim());
          
        train = new int[n];
        trainSet = new int[n];
        go = new boolean[n];
         
        String input[] = br.readLine().trim().split(" ");
        int sn = Integer.parseInt(br.readLine().trim());
        int i, j, k, stIdx, sum;
        sum =0;
        for(i = 0; i < n; i++){
            train[i] = Integer.parseInt(input[i]);  
            if(i >= (sn-1)){
                stIdx = i-sn+1;
                if(stIdx == 0){
                    for(j =stIdx; j<=i; j++){
                        sum += train[j];
                    }
                }
                else{
                    sum -= train[stIdx-1];
                    sum += train[i];
                }
                 
                box.add(new Box(stIdx, sum));
                trainSet[stIdx] = sum;
            }
        }
         
        Collections.sort(box);
         
        int cnt, size=box.size(), idx, idx1;
        sum = 0;
        Box b, b1;
        int hIdx[] = new int[3];
        for(i =0; i< size-2; i++){
            if((box.get(i).getN() + box.get(i+1).getN() + box.get(i+2).getN()) < max){
                break;
            }
            sum = 0;
            b = box.get(i);
            sum+=b.getN();
            cnt=0;
            hIdx[cnt++] = b.getIdx();
            for(j=i+1; j<size; j++){
                b1 = box.get(j);
                idx1 = b1.getIdx();
                 
                for(k=0 ;k<cnt; k++){
                    idx = hIdx[k];
                    if((idx1 + sn <= idx) || (idx1 >= idx + sn)){
                        continue;
                    }
                    else{
                        break;
                    }
                }
                 
                if(k >= cnt){
                    sum += b1.getN();
                    hIdx[cnt++] = idx1;
                }
                if(cnt == 3){
                    break;
                }
            }
             
            if(cnt ==3 && sum > max){
                max = sum;
            }
        }     
         
        System.out.print(max);   
    }
}