/**************************************************************
    Problem: 2298 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1559&sca=50)
    User: magicguru
    Language: Java
    Result: Success
    Time:685 ms
    Memory:27292 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
 
 
public class Main{
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        int arr[][] = new int[n][n];
        int mark[][] = new int[n][n];
        String input[];
         
        ArrayList<Integer> arrList = new ArrayList<Integer>();
         
         
        for(int i=0; i<n; i++){
            input = br.readLine().trim().split(" ");
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(input[j]);
                if(!arrList.contains(arr[i][j])){
                    arrList.add(arr[i][j]);
                }
            }
        }
         
        Collections.sort(arrList);
         
        ArrayList<Integer> qList[] = new ArrayList[arrList.size()];
         
        int st = arrList.get(0);
        Queue<Integer> q = new LinkedList<Integer>();
         
        int cntMax = -1;
        int cnt = 0;
        int ij = 0;
        qList[0] = new ArrayList<Integer>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(mark[i][j] > 0){
                    continue;
                }               
                 
                if(arr[i][j] > st){
                    ij = i*1000 + j;
                    q.add(ij);
                    mark[i][j] = 1;
                    qList[0].add(ij);
                     
                    while(!q.isEmpty()){
                        int t = q.poll();
                        int ti = t/1000;
                        int tj = t%1000;
                         
                        // top
                        if(ti > 0 && mark[ti-1][tj] == 0 && arr[ti-1][tj] > st){
                            ij = ((ti-1) * 1000) + tj;
                            q.add(ij);
                            mark[ti-1][tj] = 1;
                            qList[0].add(ij);
                        }
                         
                        // bottom
                        if(ti < n-1 && mark[ti+1][tj] == 0 && arr[ti+1][tj] > st){
                            ij = ((ti+1) * 1000) + tj;
                            q.add(ij);
                            mark[ti+1][tj] = 1;
                            qList[0].add(ij);
                        }
                         
                        // left
                        if(tj > 0 && mark[ti][tj-1] == 0  && arr[ti][tj-1] > st){
                            ij = (ti*1000) + (tj-1);
                            q.add(ij);
                            mark[ti][tj-1] = 1;
                            qList[0].add(ij);
                        }
                         
                        // right
                        if(tj < n-1 && mark[ti][tj+1] == 0  && arr[ti][tj+1] > st){
                            ij = (ti*1000) + (tj+1);
                            q.add(ij);
                            mark[ti][tj+1] = 1;
                            qList[0].add(ij);
                        }
                    }
                    cnt++;
                }
                else{
                    mark[i][j] = 1;
                }
            }
        }
 
//      System.out.println("cnt ; "+ cnt);
//      for(int i=0; i<n; i++){
//          for(int j=0; j<n; j++){
//              System.out.print(mark[i][j] + " " );
//          }
//          System.out.println("");
//      }
         
         
        if(cnt > cntMax){
            cntMax = cnt;
        }
         
        int v, qsize, lsize = arrList.size();
        int qi, qj, qt;
        for(int i=1; i<lsize; i++){
            qList[i] = new ArrayList<Integer>();
            v = arrList.get(i);
            qsize = qList[i-1].size();
            cnt = 0;
            for(int j =0; j<qsize; j++){
                qt = qList[i-1].get(j);
                qi = qt / 1000;
                qj = qt % 1000;
                q.clear();
                 
                if(mark[qi][qj] == i && arr[qi][qj] > v){
                    ij = qi*1000 + qj;
                    q.add(ij);
                    mark[qi][qj] = i+1;
                    qList[i].add(ij);
                     
                    while(!q.isEmpty()){
                        int t = q.poll();
                        int ti = t/1000;
                        int tj = t%1000;
                         
                        // top
                        if(ti > 0 && mark[ti-1][tj] == i && arr[ti-1][tj] > v){
                            ij = ((ti-1) * 1000) + tj;
                            q.add(ij);
                            mark[ti-1][tj] = i+1;
                            qList[i].add(ij);
                        }
                         
                        // bottom
                        if(ti < n-1 && mark[ti+1][tj] == i && arr[ti+1][tj] > v){
                            ij = ((ti+1) * 1000) + tj;
                            q.add(ij);
                            mark[ti+1][tj] = i+1;
                            qList[i].add(ij);
                        }
                         
                        // left
                        if(tj > 0 && mark[ti][tj-1] == i  && arr[ti][tj-1] > v){
                            ij = (ti*1000) + (tj-1);
                            q.add(ij);
                            mark[ti][tj-1] = i+1;
                            qList[i].add(ij);
                        }
                         
                        // right
                        if(tj < n-1 && mark[ti][tj+1] == i  && arr[ti][tj+1] > v){
                            ij = (ti*1000) + (tj+1);
                            q.add(ij);
                            mark[ti][tj+1] = i+1;
                            qList[i].add(ij);
                        }
                    }
                    cnt++;
                }
            }
            if(cnt > cntMax){
                cntMax = cnt;
            }
 
//          System.out.println("cnt ; "+ cnt);
//          for(int ii=0; ii<n; ii++){
//              for(int jj=0; jj<n; jj++){
//                  System.out.print(mark[ii][jj] + " " );
//              }
//              System.out.println("");
//          }
        }
         
        if(cntMax == 0){
            cntMax = 1;
        }
         
        System.out.println(cntMax);
    }   
}