/**************************************************************
    Problem: 1824 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1097&sca=3030)
    User: magicguru
    Language: Java
    Result: Success
    Time:154 ms
    Memory:12780 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main{  
    private static int MAXLINE = 9;
 
    private static int arr[][] = new int[MAXLINE][MAXLINE];
    private static boolean row[][] = new boolean[MAXLINE][MAXLINE];
    private static boolean col[][] = new boolean[MAXLINE][MAXLINE];
    private static boolean box[][] = new boolean[MAXLINE][MAXLINE];
     
    public static void main(String[] args) throws Exception{
 
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String input[];
        for(int i = 0; i<MAXLINE; i++){
            input = br.readLine().trim().split(" ");
            for(int j=0; j<MAXLINE; j++){
                arr[i][j] = Integer.valueOf(input[j]);
                if(arr[i][j] != 0){
                    row[i][arr[i][j]-1] = true;
                    col[j][arr[i][j]-1] = true;
                    box[((i/3)*3) + (j/3)][arr[i][j]-1] = true;
                }
            }
        }
 
        find(0, 0);
         
        for(int i = 0; i<MAXLINE; i++){
            for(int j=0; j<MAXLINE; j++){
                if(j != 0){
                    System.out.printf(" "); 
                }
                System.out.printf("%d", arr[i][j]);
            }
            System.out.println("");
        }
    }
     
    private static boolean find(int x, int y){
        boolean ret = true;
 
        if(y == MAXLINE){
            return ret;
        }
         
        if(arr[y][x] != 0){
            if((x + 1) >= MAXLINE ){
                return find(0, y+1);
            }
            else{
                return find(x+1, y);
            }
        }
         
        int boxIndex = ((y/3)*3) + (x/3);
        boolean isIn = false;
        for(int i=0; i<MAXLINE; i++){
            if(!row[y][i] && !col[x][i] && !box[boxIndex][i]){
                isIn = true;
                arr[y][x] = i+1;
                row[y][i] = true;
                col[x][i] = true;
                box[boxIndex][i] = true;
                if((x + 1) >= MAXLINE ){
                    ret = find(0, y+1);
                }
                else{
                    ret = find(x+1, y);
                }
                if(ret == false){
                    arr[y][x] = 0;
                    row[y][i] = false;
                    col[x][i] = false;
                    box[boxIndex][i] = false;
                }
            }
        }
        if(isIn == false){
            ret = false;
        }
         
        return ret;
    }
}