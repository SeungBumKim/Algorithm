/**************************************************************
    Problem: 1230 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=513&sca=4030)
    User: magicguru
    Language: Java
    Result: Success
    Time:843 ms
    Memory:11056 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main {
    private static int arr[][];
    private static boolean pass[][];
    private static int an;
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int N = Integer.parseInt(br.readLine().trim());
        arr = new int[N][N];
        pass = new boolean[N][N];
        int i,j;
        String input[];
        for(i =0; i<N; i++){
            input = br.readLine().trim().split(" ");
            for(j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }
         
        an = -1;
         
        find(0, 0, arr[0][0] == 2 ? 1 : 0, N-1, 0, 0);
        System.out.println(an);
    }
     
    private static void find(int x, int y, int cnt, int n, long hi1, long hi2){
        if(x == n && y == n){
            if(an < cnt){
                an = cnt;
            }
            return;
        }
 
        int key = y*(n+1) + x;
        long keyBit;
        long hi1Tmp = hi1, hi2Tmp = hi2;
        if(key < 50){
            keyBit = (long)1 << key;
            if((hi1 & keyBit) == keyBit){
                return;
            }
            else{
                hi1Tmp |= keyBit;
            }
        }
        else{
            keyBit = (long)1 << (key-50);
            if((hi2 & keyBit) == keyBit){
                return;
            }
            else{
                hi2Tmp |= keyBit;
            }
        }
         
        // top
        if(y>0 && arr[y-1][x] != 1){
            find(x, y-1, arr[y-1][x] == 2 ? cnt+1 : cnt, n, hi1Tmp, hi2Tmp);
        }
         
        // left
        if(x>0 && arr[y][x-1] != 1){
            find(x-1, y, arr[y][x-1] == 2 ? cnt+1 : cnt, n, hi1Tmp, hi2Tmp);
        }
         
        // right
        if(x+1 <= n && arr[y][x+1] != 1){
            find(x+1, y, arr[y][x+1] == 2 ? cnt+1 : cnt, n, hi1Tmp, hi2Tmp);
        }
         
        // bottom
        if(y+1 <= n && arr[y+1][x] != 1){
            find(x, y+1, arr[y+1][x] == 2 ? cnt+1 : cnt, n, hi1Tmp, hi2Tmp);
        }
    }
}