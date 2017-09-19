/**************************************************************
    Problem: 1841 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1114&sca=3090)
    User: magicguru
    Language: Java
    Result: Success
    Time:133 ms
    Memory:9804 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main {
   private static ArrayList<String> all = new ArrayList<String>();
   private static Team t[];
   private static Team cmpTeam[];
    
   private static class Team{
      int w;
      int l;
      int s;
       
      public Team(){
         this.w = 0;
         this.l = 0;
         this.s = 0;
      }
      public Team(int w, int l, int s){
         this.w = w;
         this.l = l;
         this.s = s;
      }
       
      public int getW(){
         return this.w;
      }
      public int getL(){
         return this.l;
      }
      public int getS(){
         return this.s;
      }   
      public void win(){
         this.w++;
      }
      public void lose(){
         this.l++;
      }
      public void same(){
         this.s++;
      }
      public void mWin(){
         this.w--;
      }
      public void mLose(){
         this.l--;
      }
      public void mSame(){
         this.s--;
      }
   }
    
   public static void main(String[] args) throws Exception {
       //System.setIn(new FileInputStream("C:\\Users\\sbkim\\workspace\\Test\\src\\input"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
      String input;
      String inputSplit[];
 
      t = new Team[6];
      cmpTeam = new Team[6];
       
      int w, l, s;
      boolean isGood = false;
      for(int i =0; i<4; i++){
 
         isGood =true;
         input = br.readLine().trim();
         inputSplit = input.split(" ");
         all.clear();         
                   
         for(int j =0; j<6; j++){
            w = Integer.parseInt(inputSplit[(j*3)]);
            l = Integer.parseInt(inputSplit[(j*3)+2]);
            s = Integer.parseInt(inputSplit[(j*3)+1]);
             
            if(w + l + s != 5){
               isGood = false;
               break;
            }
             
            t[j] = new Team();
            cmpTeam[j] = new Team(w, l, s);
         }
                   
         if(isGood == true){
            makeCase(1, 2, 6);
            isGood = false;
            if(all.contains(input)){
               isGood = true;
            }
         }
         System.out.print((isGood ? 1 : 0) + " ");
      }
   }
    
   private static void makeCase(int t1, int t2, int n){
       if(t1 >= n){
           StringBuffer strBuf = new StringBuffer();
           for(int i=0; i<n; i++){
               strBuf.append(t[i].getW()).append(" ").append(t[i].getS()).append(" ").append(t[i].getL());
               if(i != n-1){
                   strBuf.append(" ");
               }
           }
             
           all.add(strBuf.toString());
           return;
       }
               
       for(int i=0; i<6; i++){
           if(t[i].getW() > cmpTeam[i].getW() || t[i].getL() > cmpTeam[i].getL() || t[i].getS() > cmpTeam[i].getS()){
              return;
           }
        }
         
       t[t1-1].win();
       t[t2-1].lose();
       if(t2 + 1 < n+1){
           makeCase(t1, t2+1, n);
       }
       else{
           makeCase(t1+1, t1+2, n);
       }
       t[t1-1].mWin();
       t[t2-1].mLose();
         
       t[t1-1].lose();
       t[t2-1].win();
       if(t2 + 1 < n+1){
           makeCase(t1, t2+1, n);
       }
       else{
           makeCase(t1+1, t1+2, n);
       }
       t[t1-1].mLose();
       t[t2-1].mWin();
 
       t[t1-1].same();
       t[t2-1].same();
       if(t2 + 1 < n+1){
           makeCase(t1, t2+1, n);
       }
       else{
           makeCase(t1+1, t1+2, n);
       }
       t[t1-1].mSame();
       t[t2-1].mSame();
   }
}