/**************************************************************
    Problem: 1440 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=714&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:345 ms
    Memory:11220 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
 
public class Main{
    private static class Node{
        Node parent;
        int val;
         
        public Node(){
            parent = null;
            val = -1;
        }
         
        public void setParent(Node p){
            this.parent = p;
        }
        public void setVal(int val){
            this.val = val;
        }
        public int getVal(){
            return this.val;
        }
        public Node getParent(){
            return this.parent;
        }
    }
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
        int p, v, f1, f2;
        Node vNode, pNode;
        HashMap<Integer, Node> map = new HashMap<Integer, Node>();
        StringTokenizer tokenizer;
        for(int i=0; i<n-1; i++){
            tokenizer = new StringTokenizer(br.readLine().trim());
            p = Integer.parseInt(tokenizer.nextToken());
            v = Integer.parseInt(tokenizer.nextToken());
             
            if(map.containsKey(p)){
                pNode = map.get(p);
            }
            else{
                pNode = new Node();
                pNode.setVal(p);
            }
             
            if(map.containsKey(v)){
                vNode = map.get(v);
            }
            else{
                vNode = new Node();
                vNode.setVal(v);
            }
            vNode.setParent(pNode);
             
            map.put(p, pNode);
            map.put(v, vNode);
        }
         
        tokenizer = new StringTokenizer(br.readLine());
        f1 = Integer.parseInt(tokenizer.nextToken());
        f2 = Integer.parseInt(tokenizer.nextToken());
         
        ArrayList<Integer> pList = new ArrayList<Integer>();
        while(true){
            vNode = map.get(f1);
            if(vNode == null){
                break;
            }
            pList.add(vNode.getVal());
            if(vNode == null || vNode.getParent() == null){
                break;
            }
            f1 = vNode.getParent().getVal();
        }
 
        int an = f2;
        while(true){
            vNode = map.get(f2);
            if(vNode == null || vNode.getParent() == null){
                break;
            }
            f2 = vNode.getParent().getVal();
            if(pList.contains(f2)){
                an = f2;
                break;
            }
        }       
         
        System.out.println(an);
    }
}