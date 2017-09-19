/**************************************************************
    Problem: 1424 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=700&sca=4050)
    User: magicguru
    Language: Java
    Result: Success
    Time:147 ms
    Memory:9680 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main{
    private static int list[];
    private static int list2[];
    private static int sortList[];
    private static int loc[] = new int[100001];
    private static int loc2[] = new int[100001];
    private static long sum = 0;
    private static int cnt = 0;
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int n = Integer.parseInt(br.readLine().trim()), i;
        list = new int[n];
        list2 = new int[n];
        sortList = new int[n];
        for(i=0; i<n; i++){
            list[i] = Integer.parseInt(br.readLine().trim());
            list2[i] = list[i];
            sortList[i] = list[i];
            loc[list[i]] = i;
            loc2[list2[i]] = i;
            sum += list[i];
        }
         
        Arrays.sort(sortList);  
        cnt = n;
         
        for(i=0; i<n; i++){
            if(list[i] == sortList[i]){
                cnt--;
                sum -= list[i];
            }
        }
         
        System.out.println(Math.min(find3(0, n, 0), find4(0, n, 0)));
    }
     
    private static long find3(int sortNumIdx, int n, int sortNum){
        long min = Long.MAX_VALUE;
  
        if(sortNumIdx >= n){
            return 0;
        }
          
        int num = sortList[sortNumIdx];
        int locIdx = loc2[num]; 
        long tmpSum;
                      
        tmpSum = 0;
        while(true){
            if(locIdx != sortNumIdx){
                // find here number
                int fr = loc2[sortList[locIdx]];
                list2[fr] = list2[locIdx];
                loc2[list2[fr]] = fr;
                  
                list2[locIdx] = sortList[locIdx];
                loc2[list2[locIdx]] = locIdx;
                   
                tmpSum += list2[locIdx] + list2[fr];
                  
                num = sortList[sortNumIdx];
                locIdx = loc2[num]; 
            }
            else{
                sortNumIdx++;
                sortNum++;
                if(sortNumIdx >= n){
                    break;
                }
                num = sortList[sortNumIdx];
                locIdx = loc2[num]; 
            }
        }
          
        min = tmpSum;
          
        return min;
    }
     
    private static long find4(int sortNumIdx, int n, int sortNum){
        long min = Long.MAX_VALUE;
 
        if(sortNumIdx >= n){
            return 0;
        }
         
        int locIdx = loc[sortList[sortNumIdx]]; 
        long tmpSum =0;
        int fr;
 
        while(true){
            if(locIdx != sortNumIdx){
                // find here number
                fr = loc[sortList[locIdx]];
                list[fr] = list[locIdx];
                loc[list[fr]] = fr;
                 
                list[locIdx] = sortList[locIdx];
                loc[list[locIdx]] = locIdx;
                  
                cnt--;
                sum -= sortList[locIdx];
                 
                if(list[fr] == sortList[fr]){
                    cnt--;
                    sum -= sortList[fr];
                }
                 
                tmpSum += list[locIdx] + list[fr];
            }
            else{
                if(locIdx == 0){
                    int i = 0;
                    for(i=1; i<n; i++){
                        if(sortList[i] != list[i]){
                            // cal
                            long changeVal = ((cnt+1) * sortList[locIdx]) + sum + sortList[i];
                            long goVal = ((cnt-1) * sortList[i])  + sum;
                             
                            if(changeVal < goVal){
                                fr = loc[sortList[i]];
                                 
                                list[fr] = list[locIdx];
                                loc[list[fr]] = fr;
                                 
                                list[locIdx] = sortList[i];
                                loc[list[locIdx]] = locIdx;
                                  
                                tmpSum += list[locIdx] + list[fr];
                                 
                                cnt++;
                                sum += sortList[locIdx];
                            }
                            else{
                                sortNumIdx++;
                                sortNum++;                                  
                            }
                            break;
                        }
                    }
                    if(i >= n){
                        sortNumIdx++;
                        sortNum++;                          
                    }
                }
                else{
                    sortNumIdx++;
                    sortNum++;                  
                }
 
                if(sortNumIdx >= n){
                    break;
                }
            }
            locIdx = loc[sortList[sortNumIdx]]; 
        }
         
        min = tmpSum;
         
        return min;
    }
}