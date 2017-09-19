/**************************************************************
    Problem: 1317 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=596&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:286 ms
    Memory:15628 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
 
public class Main{
    private static int group[];
    private static boolean use[];
    private static ArrayList<Integer> list[];
    private static ArrayList<Integer> inList[];
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim());
        int m = Integer.parseInt(br.readLine().trim());
         
        int i, j, k, f, t;
        String input[];
        use = new boolean[n+1];
        group = new int[n+1];
        list = new ArrayList[n+1];
        inList = new ArrayList[n+1];
        ArrayList<Integer> an = new ArrayList<Integer>();
         
        for(i=0; i<n+1; i++){
            list[i] = new ArrayList<Integer>();
            inList[i] = new ArrayList<Integer>();
        }
         
        int fr, to;
        int tmp, tmp1;
        for(i=0; i<m; i++){
            input = br.readLine().trim().split(" ");
            f = Integer.parseInt(input[0]);
            t = Integer.parseInt(input[1]);
            inList[f].add(t);
            inList[t].add(f);
             
            if(group[f] != 0 && group[t] != 0){
                if(group[f] != group[t]){                   
                    if(list[group[t]].size() > list[group[f]].size()){
                        fr = group[f];
                        to = group[t];
                    }
                    else{
                        fr = group[t];
                        to = group[f];                      
                    }
                     
                    for(j =0; j<list[fr].size(); j++){
                        tmp = list[fr].get(j);
                        group[tmp] = to;
                        list[to].add(tmp);
                    }
                    list[fr].clear();
                }
                else{
                    // do nothing
                }
            }
            else if(group[f] != 0 && group[t] == 0){
                group[t] = group[f];
                list[group[f]].add(t);
            }
            else if(group[f] == 0 && group[t] != 0){
                group[f] = group[t];
                list[group[t]].add(f);              
            }
            else{
                group[f] = f;
                group[t] = f;
                list[f].add(f);
                list[f].add(t);
            }
        }
     
        for(i=1; i<n+1; i++){
            if(use[i]){
                continue;
            }
            if(group[i] == 0){
                an.add(i);
            }
            else{
                int minDepth = Integer.MAX_VALUE, minDepthIdx = -1;
                for(j =0; j<list[group[i]].size(); j++){
                    tmp = list[group[i]].get(j);
                    int st = tmp;
                    boolean isGo[] = new boolean[n+1];
                     
                    LinkedList<Integer> link[] = new LinkedList[2];
                    link[0] = new LinkedList<Integer>();
                    link[1] = new LinkedList<Integer>();
                     
                    int tern = 0, in = 1;
                    link[tern].add(st);
                    int depth = 0;
 
                    while(!link[tern].isEmpty()){
                        if(depth >= minDepth){
                            break;
                        }
                         
                        link[in].clear();
                        while(!link[tern].isEmpty()){
                            tmp1 = link[tern].poll();
                            if(isGo[tmp1]){
                                continue;
                            }
                            isGo[tmp1] = true;
                            for(k=0; k<inList[tmp1].size(); k++){
                                link[in].add(inList[tmp1].get(k));
                            }
                        }
                        depth++;
                        in = tern;
                        tern = tern == 0 ? 1 : 0;
                    }
                     
                    if(depth < minDepth){
                        minDepth = depth;
                        minDepthIdx = st;
                    }
 
                    use[tmp] = true;
                }
                an.add(minDepthIdx);
            }
        }
         
        Collections.sort(an);
        System.out.println(an.size());
        for(i=0; i<an.size(); i++){
            System.out.println(an.get(i));
        }
    }
}