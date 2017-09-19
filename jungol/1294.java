/**************************************************************
    Problem: 1294 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=577&sca=4030)
    User: magicguru
    Language: Java
    Result: Success
    Time:2119 ms
    Memory:13108 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Main {
    private static boolean arr[][];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int N = Integer.parseInt(br.readLine().trim());
        if(N == 1){
            System.out.println('X');
        }
        else if(N >= 8){
            System.out.println((int)Math.pow(5, N-1));
        }
        else{
            int powN = (int)Math.pow(3, N-1);
            arr = new boolean[powN][powN];
            draw(0, 0, N-1);
            int i,j;
            for(i=0; i<powN; i++){
                for(j=0; j<powN; j++){
                    System.out.print(arr[i][j] ? 'X' : ' ');
                }
                System.out.println("");
            }
        }
    }
     
    private static void draw(int x, int y, int n){
        if(n == 1){
            arr[x][y] = true;
            arr[x+2][y] = true;
            arr[x+1][y+1] = true;
            arr[x][y+2] = true;
            arr[x+2][y+2] = true;
        }
        else{
            int term = (int)Math.pow(3, n-1);
            // lt
            draw(x, y, n-1);
            // rt
            draw(x+(term*2), y, n-1);
            // c
            draw(x+term, y+term, n-1);
            // lb
            draw(x, y+(term*2), n-1);
            // rb
            draw(x+(term*2), y+(term*2), n-1);
        }
    }
}