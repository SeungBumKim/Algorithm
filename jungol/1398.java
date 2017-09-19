/**************************************************************
    Problem: 1398 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=674&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:643 ms
    Memory:9800 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{
    private static ArrayList<Integer> pList;
    private static int arrP[];
    private static long memo[][][];
    private static int N, K, len;
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
         
        String input[] = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
         
        pList = findPrime(N);
        len = pList.size(); 
        arrP = new int[len];
         
        for(int i=0; i<pList.size(); i++){
            arrP[i] = pList.get(i);
        }
        memo = new long[len+1][K+1][N+1];   
         
        int i, j, k;
        for(i=0; i<len+1; i++){
            for(j =0; j<K+1; j++){
                for(k =0; k<N+1; k++){
                    memo[i][j][k] = -1;
                }
            }
        }
         
        System.out.println(make(0,0,0));
    }
     
    private static ArrayList<Integer> findPrime(int n){
        ArrayList<Integer> pList = new ArrayList<Integer>();
         
        int map[] = new int[n+1];
        int i,j;
        for(i=2; i<n+1; i++){
            if(map[i] == 0){
                pList.add(i);
                for(j=i+i; j<n+1; j+=i){
                    map[j] = 1;
                }
            }
        }
         
        return pList;
    }
     
    private static long make(int idx, int cnt, int sum){
        long ret = 0;
        if(cnt > K){
            return 0;
        }
         
        if(((len-idx-1)+cnt) < K){
            return ret;
        }
         
        if(memo[idx][cnt][sum] >= 0){
            return memo[idx][cnt][sum];
        }
         
        int tmpSum;
        for(int i=idx; i<len; i++){
            tmpSum = sum + arrP[i];
            if(tmpSum > N){
                break;
            }
            else if(tmpSum == N){
                if((cnt + 1) == K){
                    ret++;
                }
                break;
            }
            else{
                ret += make(i+1, cnt+1, tmpSum);
            }
        }
         
        memo[idx][cnt][sum] = ret;
        return ret;
    }
}