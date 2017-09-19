/**************************************************************
    Problem: 1840 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1113&sca=3030)
    User: magicguru
    Language: Java
    Result: Success
    Time:184 ms
    Memory:12084 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{
    private static int arr[][];
     
    private static class Hole{
        int i, j;
         
        public Hole(int i, int j){
            this.i = i;
            this.j = j;
        }
         
        public int getI(){
            return this.i;
        }
        public int getJ(){
            return this.j;
        }
    }
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int x, y;
        String input[];
         
        input = br.readLine().trim().split(" ");
        y = Integer.parseInt(input[0]);
        x = Integer.parseInt(input[1]);
         
        arr = new int[y+2][x+2];
         
        int cnt = 0;
        for(int i=1; i<y+1; i++){
            input = br.readLine().trim().split(" ");
            for(int j=1; j<x+1; j++){
                arr[i][j] = Integer.parseInt(input[j-1]);
                if(arr[i][j] == 1){
                    cnt++;
                }
            }
        }       
         
        // find hole
        ArrayList<Hole> hole = new ArrayList<Hole>();
        ArrayList<Hole> hole2 = new ArrayList<Hole>();
        boolean isGood;
        for(int i=2; i<y; i++){
            for(int j=2; j<x; j++){
                if(arr[i][j] == 0 && isHole(i, j, x, y)){
                    hole.add(new Hole(i, j));
                    arr[i][j] = 2;
                }
            }
        }
         
        // check re hole
        boolean isFind = true;
        int tmpI, tmpJ;
        Hole tmpHole;
        while(isFind){
            isFind = false;
            hole2.clear();
            for(int i=0; i<hole.size(); i++){
                tmpHole = hole.get(i);
                tmpI = tmpHole.getI();
                tmpJ = tmpHole.getJ();
                if(isReHole(tmpI, tmpJ)){
                    arr[tmpI][tmpJ] = 0;
                    isFind = true;
                    hole2.add(tmpHole);
                }
            }
             
            for(int i=0; i<hole2.size(); i++){
                hole.remove(hole2.get(i));
            }
        }
         
        int tmp[][];
        int tmpCnt = 0;
        int num = 0;
 
        while(true){
            tmpCnt = 0;
            tmp = new int[y+2][x+2];
            for(int i=1; i<y+1; i++){
                for(int j=1; j<x+1; j++){
                    if(arr[i-1][j] == 0 || arr[i+1][j] == 0 || arr[i][j-1] == 0 || arr[i][j+1] == 0){
                        tmp[i][j] = 0;
                    }
                    else{
                        tmp[i][j] = arr[i][j];
                    }
 
                    if(tmp[i][j] == 1){
                        tmpCnt++;
                    }
                }
            }
 
            num++;
            if(tmpCnt == 0){
                break;
            }
            cnt = tmpCnt;
            arr = tmp;
             
            isFind = true;
            while(isFind){
                isFind = false;
                hole2.clear();
                for(int i=0; i<hole.size(); i++){
                    tmpHole = hole.get(i);
                    tmpI = tmpHole.getI();
                    tmpJ = tmpHole.getJ();
                    if(isReHole(tmpI, tmpJ)){
                        arr[tmpI][tmpJ] = 0;
                        isFind = true;
                        hole2.add(tmpHole);
                    }
                }
                 
                for(int i=0; i<hole2.size(); i++){
                    hole.remove(hole2.get(i));
                }
            }
        }
         
        System.out.println(num);
        System.out.println(cnt);
    }
     
    private static boolean isReHole(int i, int j){
        if(arr[i-1][j] == 0 || arr[i+1][j] == 0 || arr[i][j-1] == 0 || arr[i][j+1] == 0){
            return true;
        }
        return false;
    }
     
    private static boolean isHole(int i, int j, int x, int y){
        boolean isGood = false;
        // top
        for(int k=i-1; k>=2; k--){
            if(arr[k][j] == 1){
                isGood = true;
                break;
            }
        }
         
        if(isGood == false){
            return false;
        }
         
        // bottom
        isGood = false;
        for(int k=i+1; k<y; k++){
            if(arr[k][j] == 1){
                isGood = true;
                break;
            }
        }
         
        if(isGood == false){
            return false;
        }
         
        // left
        isGood = false;
        for(int k=j-1; k>=2; k--){
            if(arr[i][k] == 1){
                isGood = true;
                break;
            }
        }
         
        if(isGood == false){
            return false;
        }
         
        // right
        isGood = false;
        for(int k=j+1; k<x; k++){
            if(arr[i][k] == 1){
                isGood = true;
                break;
            }
        }
         
        if(isGood == false){
            return false;
        }
         
        return true;
    }
}