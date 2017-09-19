/**************************************************************
    Problem: 1681 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=3030)
    User: magicguru
    Language: Java
    Result: Success
    Time:128 ms
    Memory:8408 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
 
public class Main{
    private static ArrayList<Node> nodeList[];
    private static int goHistory[];
    private static int min = Integer.MAX_VALUE;
     
    public static class Node implements Comparable<Node>{
        int to;
        int w;
     
        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
         
        public int getTo() {
            return to;
        }
        public int getW() {
            return w;
        }
 
        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            if(o.w > this.w){
                return -1;
            }
            else if(o.w < this.w){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
     
    public static void main(String[] args) throws Exception{
        int spot;
 
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
         
        spot = Integer.parseInt(br.readLine().trim());
        nodeList = new ArrayList[spot];
        goHistory = new int[spot];
        String input[];
        Node node;
        int n;
        int gomax = 0;
        for(int i = 0; i < spot; i++){       
            nodeList[i] = new ArrayList<Node>();
             
            input = br.readLine().trim().split(" ");
            for(int j =0; j<spot; j++){
                n = Integer.parseInt(input[j]);
                if(n != 0){
                    node = new Node(j, n);                  
                    nodeList[i].add(node);
                }
            }
            gomax |= 1 << i;
            goHistory[i] = 0;
            Collections.sort(nodeList[i]);
        }
         
        find(0, 0, 0, gomax, 0);
        System.out.print(min);
    }
     
    private static void find(int st, int end, int go, int gomax, int added){
        if(go == gomax && st == end){
            if(min > added){
                min = added;
            }
            return;
        }
 
        if(added > min){
            return;
        }
 
        if(goHistory[st] > 0){
            return;
        }
         
        goHistory[st]++;
        go |= 1 << st;
        int size = nodeList[st].size();
        Node node;
        int to;
        for(int i =0; i<size ; i++){
            node = nodeList[st].get(i);
            to  = node.getTo();
            if(st == to){
                continue;
            }
             
            find(to, end, go, gomax, added + node.getW());
        }
        goHistory[st]--;
        if(goHistory[st] <= 0){
            go |= 0 << st;
        }       
    }
}