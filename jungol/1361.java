/**************************************************************
    Problem: 1361 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=636&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:110 ms
    Memory:8460 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
 
public class Main{
    private static int N, an;
    private static int map[][][];
    private static int memo[][][][];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        N = Integer.parseInt(br.readLine().trim());
         
        map = new int[N][N][N];
        memo = new int[N][N][N][30];
        char input[] = br.readLine().trim().toCharArray();
        int i,j,k,l,val,chIdx=0;
        for(i=0; i<N; i++){
            for(j=0; j<N; j++){
                for(k=0; k<N; k++){
                    if(input[chIdx] == '('){
                        val = 1;
                    }
                    else if(input[chIdx] == ')'){
                        val =  -1;
                    }
                    else{
                        val = 0;
                    }
                    chIdx++;
                    map[i][j][k] = val;
                    for(l=0; l<30; l++){
                        memo[i][j][k][l] = -2;
                    }
                }
            }
        }   
        System.out.println(find(0, 0, 0, 0));
    }
     
    private static int find(int iIdx, int jIdx, int kIdx, int val){
        int ret = 0;
        int tmpRet;
        if(val < 0){
            return ret;
        }
         
        int temp = val + map[iIdx][jIdx][kIdx];
        if(iIdx == N-1 && jIdx == N-1 && kIdx == N-1){
            if(temp == 0){
                return 1;
            }
            return 0;
        }
         
        if(memo[iIdx][jIdx][kIdx][val] != -2){
            return memo[iIdx][jIdx][kIdx][val];
        }
                 
        if(iIdx < N-1){
            tmpRet = find(iIdx+1, jIdx, kIdx, temp);
            if(tmpRet == -1){
                memo[iIdx][jIdx][kIdx][val] = tmpRet;
                return tmpRet;
            }
            ret += tmpRet; 
             
        }
        if(jIdx < N-1){
            tmpRet = find(iIdx, jIdx+1, kIdx, temp);    
            if(tmpRet == -1){
                memo[iIdx][jIdx][kIdx][val] = tmpRet;
                return tmpRet;
            }
            ret += tmpRet; 
        }
        if(kIdx < N-1){
            tmpRet = find(iIdx, jIdx, kIdx+1, temp);
            if(tmpRet == -1){
                memo[iIdx][jIdx][kIdx][val] = tmpRet;
                return tmpRet;
            }
            ret += tmpRet; 
        }
         
        ret = ret > 1000000000 ? -1 : ret;
        memo[iIdx][jIdx][kIdx][val] = ret;
        return ret;
    }
}