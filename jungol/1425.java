/**************************************************************
    Problem: 1425 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=701&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:169 ms
    Memory:10652 kb
****************************************************************/
  
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
  
public class Main{
    private static int n, m, fr, to;
    private static int st[], gap[], parent[], parent2[];
    private static boolean visit[];
  
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          
        String input[] = br.readLine().trim().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
          
        st = new int[m];
        gap = new int[m];
        parent2 = new int[n+1];
        parent = new int[n+1];
        visit = new boolean[n+1];
          
        int i, t, idx, tmp=-1, an;
        ArrayList<Integer> anList = new ArrayList<Integer>();
        boolean find;
        for(i =0; i<m; i++){
            input = br.readLine().trim().split(" ");
            st[i] = Integer.parseInt(input[0]);
            gap[i] = Integer.parseInt(input[1]);
        }
          
        input = br.readLine().trim().split(" ");
        fr = Integer.parseInt(input[0]);
        to = Integer.parseInt(input[1]);
          
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(fr);
         
        visit[fr] = true;
        find = false;
        while(!queue.isEmpty()){
            idx = queue.poll();      
              
            for(i=0; i<m; i++){
                if(isFind(idx, st[i], gap[i])){
                    tmp = st[i];
                    while(tmp <= n){
                        if(tmp == to){
                            parent2[tmp] = i;
                            parent[tmp] = idx;
                            find = true;
                            break;
                        }
                        if(!visit[tmp]){
                            parent2[tmp] = i;
                            parent[tmp] = idx;
                            visit[tmp] = true;
                            queue.add(tmp);
                        }
                        tmp += gap[i];
                    }
                      
                    if(find){
                        break;
                    }
                }
            }
            if(find){
                break;
            }
        }
          
        int be = -1;
        if(find && tmp >= 0){
            anList.clear();
              
            while(true){
                if(tmp == fr){
                    break;
                }
                  
                if(be == -1){
                    anList.add(parent2[tmp]);
                }
                else{
                    if(be != parent2[tmp]){
                        anList.add(parent2[tmp]);
                    }
                }   
                be = parent2[tmp];
                tmp = parent[tmp];
            }
            System.out.println(anList.size());
            for(i =anList.size()-1; i>=0; i--){
                System.out.println(anList.get(i) + 1);
            }
        }
        else{
            System.out.println(-1);
        }
          
    }
      
    private static boolean isFind(int target, int st, int gap){
        int t = target - st;
        if(t < 0){
            return false;
        }
        else{
            if(t % gap == 0){
                return true;
            } 
        }
  
        return false;
    }
}