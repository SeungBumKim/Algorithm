/**************************************************************
    Problem: 1316 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=595&sca=4040)
    User: magicguru
    Language: Java
    Result: Success
    Time:137 ms
    Memory:8196 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
 
public class Main{
    private static int N;
    private static int list[], list2[], list3[], list4[], sort[], list1[], sort1[];
    private static int sortInt, listInt, an=0;
    private static int val[];
    private static HashSet<Integer> q = new HashSet<Integer>();
    private static HashSet<Integer> q1 = new HashSet<Integer>();
    private static HashSet<Integer> q2 = new HashSet<Integer>();
    private static HashSet<Integer> q3 = new HashSet<Integer>();
    private static HashSet<Integer> qLoop, qRet, rqLoop, rqRet;
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        N = Integer.parseInt(br.readLine().trim());     
        String input[] = br.readLine().trim().split(" ");
         
        list = new int[N];
        list1= new int[N];
        list2 = new int[N];
        list3 = new int[N];
        list4 = new int[N];
        sort = new int[N];
        sort1 = new int[N];
        val = new int[N];
        int i,j;
        for(i=0; i<N; i++){
            list[i] = Integer.parseInt(input[i]);
            list1[i] = list[i];
            list2[i] = list[i];
            list3[i] = list[i];
            list4[i] = list[i];
            sort[i] = list[i];
        }
         
        Arrays.sort(sort);
         
        for(i=0; i<N; i++){
            for(j=0; j<N; j++){
                if(list[j] == sort[i]){
                    list1[j] = i;
                    break;
                }
            }
            sort1[i] = i;
            val[i] = (0x07 << (i*3));
        }
         
        int an1 =0, an2 =0;
         
        // left sort
        int s =0, t; // start, target
        while(s < N){
            for(t=s; t<N; t++){
                if(list[t] == sort[s]){
                    break;
                }
            }
            if(t == s){
                s++;
                continue;
            }
            reverse(list, s++, t);
            an1++;
        }
        sortInt = makeBit(sort1);
         
        // left sort to right and reverse
        s =N-1;
        while(s > -1){
            for(t=s; t>-1; t--){
                if(list2[t] == sort[N-s-1]){
                    break;
                }
            }
            if(t == s){
                s--;
                continue;
            }
            reverse(list2, t, s--);
            an2++;
            if(an2+1 >= an1){
                break;
            }
        }
        an1 = Math.min(an1, an2+1);
         
        // right sort
        s =N-1;
        an2 = 0;
        while(s > -1){
            for(t=s; t>-1; t--){
                if(list3[t] == sort[s]){
                    break;
                }
            }
            if(t == s){
                s--;
                continue;
            }
            reverse(list3, t, s--);
            an2++;
            if(an2 >= an1){
                break;
            }
        }
        an1 = Math.min(an1, an2);
         
         
        // right sort to left and reverse
        s =0;
        an2 = 0;
        while(s < N){
            for(t=s; t<N; t++){
                if(list4[t] == sort[s]){
                    break;
                }
            }
            if(t == s){
                s++;
                continue;
            }
            reverse(list4, s++, t);
            an2++;
            if(an2+1 >= an1){
                break;
            }
        }
        an1 = Math.min(an1, an2+1);
        an = an1;       
         
        listInt = makeBit(list1);
        int tmp;
        an1 = 1;
        an2 = 1;
        int find = find(listInt, q, an1);
        int findr = find2(sortInt, q2, an2);
        if(findr >= 0 ){
            an1 = findr;
        }
        Iterator<Integer> it;
        while(find == -1 && findr == -1){
            if(an1 % 2 == 1){
                qLoop = q;
                qRet = q1;
                rqLoop = q2;
                rqRet = q3;
            }
            else{
                qLoop = q1;
                qRet = q;               
                rqLoop = q3;
                rqRet = q2;
 
            }
            qRet.clear();
            an1++;
            it = qLoop.iterator();          
            while(it.hasNext()){
                tmp = it.next();
                find = find(tmp, qRet, an1); 
                if(find != -1){
                    an2 = find;
                    break;
                }
            }
            if(find != -1){
                break;
            }
            if(an1 >= an){
                break;
            }
             
            an2++;
            rqRet.clear();
            it = rqLoop.iterator();         
            while(it.hasNext()){
                tmp = it.next();
                findr = find2(tmp, rqRet, an2); 
                if(findr != -1){
                    an1 = findr;
                    break;
                }
            }
            if(an2 >= an || an1 + an2 >= an){
                break;
            }
        }
 
        an = Math.min(an, an1 + an2);
        System.out.println(an);
    }
     
    private static void reverse(int l[], int s, int e){
        int tmp;
        while(s < e){
            tmp = l[s];
            l[s] = l[e];
            l[e] = tmp;
            s++;
            e--;
        }
    }
     
    private static int reverseInt(int l, int s, int e){
        int tmpS, tmpE;
        int ret = l;
        while(s < e){
            tmpS = (ret >> (3 * s)) & 0x07;
            tmpE = (ret >> (3 * e)) & 0x07;
            ret &= ~val[s];
            ret &= ~val[e];
            ret |= (tmpE << (3 * s));
            ret |= (tmpS << (3 * e));
            s++;
            e--;
        }
         
        return ret;
    }
     
    private static int find(int l, HashSet<Integer> h, int cnt){
        int i, j, tmp;
        for(i=0; i<N; i++){
            for(j=i+1; j<N; j++){
                tmp = reverseInt(l, i, j);
                if(tmp == sortInt){
                    return 0;
                }
                else if(map2.containsKey(tmp)){
                    return map2.get(tmp);
                }
                else{
                    h.add(tmp);
                    if(!map.containsKey(tmp)){
                        map.put(tmp, cnt);
                    }
                }
            }
        }
         
        return -1;
    }
     
    private static int find2(int l, HashSet<Integer> h, int cnt){
        int i, j, tmp;
        for(i=0; i<N; i++){
            for(j=i+1; j<N; j++){
                tmp = reverseInt(l, i, j);
                if(tmp == listInt){
                    return 0;
                }
                else if(map.containsKey(tmp)){
                    return map.get(tmp);
                }
                else{
                    h.add(tmp);
                    if(!map2.containsKey(tmp)){
                        map2.put(tmp, cnt);
                    }
                }
            }
        }
         
        return -1;
    }
     
    private static int makeBit(int l[]){
        int ret = 0;
         
        for(int i=0; i<N; i++){
            ret |= (l[i] << (3 * i));
        }
         
        return ret;
    }
}