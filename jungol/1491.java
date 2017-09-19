/**************************************************************
    Problem: 1491 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=763&sca=3050)
    User: magicguru
    Language: Java
    Result: Success
    Time:118 ms
    Memory:8944 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
 
public class Main{  
    private static int pointDist1[];
    private static int pairShot[];
    private static int minVal[];
    private static Node an[];
    private static boolean isGo[];
    private static int min;
     
    private static PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
    private static ArrayList<Spot> list[];
    private static ArrayList<Node> answer = new ArrayList<Node>();
     
    private static class Spot{
        int from;
        int to;
        int val;
         
        public Spot(int from, int to, int val){
            this.from = from;
            this.to = to;
            this.val = val;
        }
         
        public int getFrom(){
            return this.from;
        }
        public int getTo(){
            return this.to;
        }
        public int getVal(){
            return this.val;
        }
    }
     
    private static class Node implements Comparable<Node>{
        int idx;
        int parent;
        int weight;
         
        public Node(int idx, int parent, int weight){
            this.idx = idx;
            this.parent = parent;
            this.weight = weight;
        }
 
        public int getIdx(){
            return this.idx;
        }
        public int getParent(){
            return this.parent;
        }
        public int getWeight(){
            return this.weight;
        }
 
        @Override
        public int compareTo(Node o) {
            if(this.weight > o.getWeight()){
                return 1;
            }
            else if(this.weight < o.getWeight()){
                return -1;
            }
            else{
                return 0;
            }
        }
    }
     
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int maxDist = Integer.parseInt(br.readLine().trim());
        int n = Integer.parseInt(br.readLine().trim());
         
        pointDist1 = new int[n+2];
        list = new ArrayList[n+2];
        an = new Node[n+2];
        pairShot = new int[n+2];
        minVal = new int[n+2];
        isGo = new boolean[n+2];
        min = Integer.MAX_VALUE;
         
        int minus ;
        String input1[] = br.readLine().trim().split(" ");
        String input2[] = br.readLine().trim().split(" ");
        for(int i=0; i<n+2; i++){
            minVal[i] = Integer.MAX_VALUE;
            list[i] = new ArrayList<Spot>();
            if(i < n){
                pairShot[i+1] = Integer.parseInt(input2[i]);
            }
             
            if(i>0){
                pointDist1[i] = pointDist1[i-1] + Integer.parseInt(input1[i-1]);
                for(int j = i-1; j>=0; j--){
                    minus = pointDist1[i] - pointDist1[j];
                    if(minus <= maxDist){
                        list[j].add(new Spot(j, i, pairShot[i]));
                    }
                }
            }
        }
         
        pQueue.add(new Node(0, -1, 0));
         
        Node node = null;
        Spot spot;
        int idx, idx1, w, val;
        while(!pQueue.isEmpty()){
            node = pQueue.poll();
            idx = node.getIdx();
            w = node.getWeight();
             
            if(isGo[idx]){
                continue;
            }
            isGo[idx] = true;
            an[idx] = node;
            answer.add(node);
             
            if(idx == n+1)
                break;
             
            for(int i =0; i<list[idx].size(); i++){
                spot = list[idx].get(i);
                idx1 = spot.getTo();
                val = spot.getVal();
                 
                pQueue.add(new Node(idx1, idx, w + val));
            }           
        }
 
        int sum =0;
        node = an[node.getParent()];
        Stack<Integer> stack = new Stack<Integer>();
        while(node != null && node.getParent() != -1){
            idx = node.getIdx();
            sum += pairShot[idx];
            stack.add(idx);
            node = an[node.getParent()];
        }       
         
         
        System.out.println(sum);
        if(sum != 0){
            System.out.println(stack.size());
            while(!stack.isEmpty()){
                System.out.print(stack.pop() + " ");
            }
        }
    }   
     
}