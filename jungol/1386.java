/**************************************************************
    Problem: 1386 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=662&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:130 ms
    Memory:9944 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main{
    private static int n;
    private static boolean map[][];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        n = Integer.parseInt(br.readLine().trim());
        map = new boolean[n][n];
        char tmp[] = new char[n];
        int i, j, an;
        for(i =0; i<n; i++){
            tmp = br.readLine().trim().toCharArray();
            for(j =0; j<n; j++){
                map[i][j] = (tmp[j] == '#') ? true : false;
            }
        }
         
        an = 0;
        for(i=0; i<n; i++){
            for(j =0; j<n; j++){
                if(map[i][j]){
                    an = Math.max(findPlus(j, i, 1), an);
                }
            }
        }
        System.out.println(an);
    }
     
    private static int findPlus(int x, int y, int cnt){
        int num = 0;
 
        if(y >= n){
            return -1;
        }
         
        int i, loop = cnt/2+1;
        if(map[y][x]){
            if(cnt > 1){
                for(i =0; i<loop; i++){
                    if(x-i < 0 || x +i >= n){
                        break;
                    }
                    if(!map[y][x-i] || !map[y][x+i]){
                        break;
                    }
                }
                 
                if(i >= loop){
                    num = findPlus(x, y+1, cnt+2);
                    if(num == -1){
                        num = findMinus(x, y+1, cnt-2);
                        if(num == cnt-2){
                            num = cnt;
                        }
                    }
                }
                else{
                    num = -1;
                }
            }
            else{
                num = findPlus(x, y+1, cnt+2);
                if(num == -1){
                    num = cnt;
                }
            }
        }
        else{
            num = -1;
        }
         
        return num;
    }
     
    private static int findMinus(int x, int y, int cnt){
        int num = 0;
 
        if(y >= n){
            return -1;
        }
        if(cnt == 1){
            if(map[y][x]){
                return 1;
            }
            else{
                return -1;
            }
        }
         
        int i, loop = cnt/2+1;
        if(map[y][x]){
            for(i =0; i<loop; i++){
                if(x-i < 0 || x +i >= n){
                    break;
                }
                if(!map[y][x-i] || !map[y][x+i]){
                    break;
                }
            }
             
            if(i >= loop){
                num = findMinus(x, y+1, cnt-2);
                if(num == cnt-2){
                    num = cnt;
                }
            }
            else{
                num = -1;
            }
        }
        else{
            num = -1;
        }
         
         
        return num;
    }
}