/**************************************************************
    Problem: 1370 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=645&sca=3020)
    User: magicguru
    Language: Java
    Result: Success
    Time:171 ms
    Memory:10004 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
 
public class Main{
    private static ArrayList<Node> all = new ArrayList<Node>();
    private static ArrayList<Node> good = new ArrayList<Node>();
    private static boolean isGo[] = new boolean[501];   
     
    private static class Node implements Comparable<Node>{
        int idx;
        int st;
        int end;
        int term;
        ArrayList<Node> list;
         
        public Node(int idx, int st, int end, int term){
            this.idx = idx;
            this.st = st;
            this.end = end;
            this.term = term;
 
            list = new ArrayList<Node>();
        }
                 
        public int getIdx(){
            return this.idx;
        }
        public int getSt(){
            return this.st;
        }
        public int getEnd(){
            return this.end;
        }
        public int getTerm(){
            return this.term;
        }
        public ArrayList<Node> getList(){
            return this.list;
        }
        public void addNode(Node node){
            this.list.add(node);
        }       
         
        @Override
        public int compareTo(Node o) {
            if(this.list.size() > o.getList().size()){
                return 1;
            }
            else if(this.list.size() < o.getList().size()){
                return -1;
            }
            else{
                return 0;
            }
        }       
    }
     
    public static void main(String[] args) throws Exception{
        int n;
         
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        n = Integer.parseInt(br.readLine().trim());
        String input[];
        int idx, st, end, term, st1, end1;
        Node node, n1;
        for(int i=0; i<n; i++){
            input = br.readLine().trim().split(" ");
            idx = Integer.parseInt(input[0]);
            st = Integer.parseInt(input[1]);
            end = Integer.parseInt(input[2]);
            term = end - st;
     
            node = new Node(idx, st, end, term); 
             
            for(int j=0; j<i; j++){
                n1 = all.get(j);
                 
                st1 = n1.getSt();
                end1 = n1.getEnd();
                 
                if(!(end <= st1 || st >= end1)){
                    node.addNode(n1);
                    n1.addNode(node);
                }
            }
             
            all.add(node);
             
        }
        Collections.sort(all);
         
        int cnt = 0, size, idx1, idx2;
        ArrayList<Node> l;
        for(int i=0; i<n; i++){
            if(cnt >= n)
                break;
             
            node = all.get(i);
            idx1 = node.getIdx();
             
            if(isGo[idx1] == false){
                good.add(node);
                isGo[idx1] = true;
                cnt++;
 
                l = node.getList();
                size=l.size();          
                for(int j=0; j<size; j++){
                    idx2 = l.get(j).getIdx();
                    if(isGo[idx2] == false){
                        isGo[idx2] = true;
                        cnt++;
                    }
                }
            }
        }
 
        Collections.sort(good, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.getSt() > o2.getSt()){
                    return 1;
                }
                else if(o1.getSt() < o2.getSt()){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        });
         
        size = good.size();
        System.out.println(size);
        for(int i =0; i<size; i++){
            System.out.print(good.get(i).getIdx() + " ");
        }
    }
}