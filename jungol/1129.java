/**************************************************************
    Problem: 1129 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=409&sca=30d0)
    User: magicguru
    Language: Java
    Result: Success
    Time:108 ms
    Memory:8676 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main{  
    private static ArrayList<Line> line= new ArrayList<Line>();
     
    public static class Line{
        Bunsu gradient;
        Bunsu added;
        int sX, sY, eX, eY;
         
        public Line(Bunsu g, Bunsu a, int sX, int sY, int eX, int eY) {
            this.gradient = g;
            this.added = a;
            this.sX = sX;
            this.sY = sY;
            this.eX = eX;
            this.eY = eY;
        }
         
        public boolean checkIn(Bunsu x, Bunsu y){
            boolean isIn = false;
             
            int bgX, smX, bgY, smY;
             
            if(this.sX > this.eX){
                bgX = this.sX;
                smX = this.eX;
            }
            else{
                bgX = this.eX;
                smX = this.sX;
            }
             
            if(this.sY > this.eY){
                bgY = this.sY;
                smY = this.eY;
            }
            else{
                bgY = this.eY;
                smY = this.sY;
            }
             
            int xS = x.getS();
            int xM = x.getM();
            int yS = y.getS();
            int yM = y.getM();
 
            if(bgX == smX){
                if((xS >= (smX * xM)) && xS <= (bgX * xM) && (yS > (smY * yM)) && yS < (bgY * yM)){
                    isIn = true;
                }
            }
            else if(bgY == smY){
                if((xS >= (smX * xM)) && xS <= (bgX * xM) && (yS >= (smY * yM)) && yS <= (bgY * yM)){
                    isIn = true;
                }
            }           
            else{
                if((xS > (smX * xM)) && xS < (bgX * xM) && (yS > (smY * yM)) && yS < (bgY * yM)){
                    isIn = true;
                }
            }
             
            return isIn;            
        }
         
        public Bunsu getGradient() {
            return gradient;
        }
        public Bunsu getAdded() {
            return added;
        }
        public int getsX() {
            return sX;
        }
 
        public int getsY() {
            return sY;
        }
 
        public int geteX() {
            return eX;
        }
 
        public int geteY() {
            return eY;
        }
    }
     
    public static class Bunsu{
        int s;
        int m;
        boolean minus;
         
        public Bunsu(int s, int m) {
            boolean minus = false;
            if(s < 0){
                s = -s;
                minus = true;
            }
            if(m < 0){
                m = -m;
                minus = minus ? false : true;
            }
             
            if(s != 0 && m != 0 && s/m > 0 && s%m == 0){
                s = s/m;
                m = 1;
            }
             
            this.s = s;
            this.m = m;
            this.minus = minus;
        }
         
        public int getS() {
            return this.minus ? -s : s;
        }
 
        public int getM() {
            return m;
        }
    }
     
    public static void main(String[] args) throws Exception{
        int T;
     
        InputStreamReader io = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(io);
 
        ////////////
        int sX, sY, eX, eY;
        Bunsu gradient, added;
        Line l;
        int answer = 0;
        ////////////
         
         
        T = Integer.valueOf(br.readLine().trim());
        String input[];
        for(int i = 0; i<T; i++){
            input = br.readLine().trim().split(" ");
             
            sX = Integer.valueOf(input[0]);
            sY = Integer.valueOf(input[1]);
            eX = Integer.valueOf(input[2]);
            eY = Integer.valueOf(input[3]);         
             
            gradient = new Bunsu((eY - sY), (eX - sX));
            if(gradient.getS() == 0){
                added = new Bunsu(sY, 1);
            }
            else if(gradient.getM() == 0){
                added = new Bunsu(sX, 1);
            }
            else{
                added = minus(sY, mul(gradient, sX));
            }
            l = new Line(gradient, added, sX, sY, eX, eY);
            answer += findCheckPoint(l);
            line.add(l);
        }
        System.out.printf("%d\n", answer);
    }
     
    public static int findCheckPoint(Line a_l){
        int ret = 0, i;
        Line l;
        Bunsu g, a_g, a, a_a;
        Bunsu cross_x, cross_y;
         
        a_g = a_l.getGradient();
        a_a = a_l.getAdded();
         
        int size = line.size();
        for(i =0; i< size; i++){
            l = line.get(i);
            g = l.getGradient();
            a = l.getAdded();
             
            if(((g.getM() == a_g.getM()) && (g.getS() == a_g.getS())) || 
                    (g.getM() == 0 && a_g.getM() == 0) ||
                    (g.getS() == 0 && a_g.getS() == 0)){
                continue;
            }
             
            if(l.getsX() == 0){
                int j = 17;
            }
 
             
            if(g.getS() == 0 || a_g.getS() == 0){
                if(g.getS() == 0){
                    cross_y = a;
                    cross_x = div(minus(cross_y, a_a), a_g);
                }
                else{
                    cross_y = a_a;
                    cross_x = div(minus(cross_y, a), g);
                }
            }
            else if(g.getM() == 0 || a_g.getM() == 0){
                if(g.getM() == 0){
                    cross_x = a;
                    cross_y = plus(mul(a_g, cross_x), a_a);
                }
                else{
                    cross_x = a_a;
                    cross_y = plus(mul(g, cross_x), a);
                }               
            }
            else{           
                cross_x = div(minus(a_a, a), minus(g, a_g));
                cross_y = plus(mul(g, cross_x), a);
            }
             
            if(l.checkIn(cross_x, cross_y) && a_l.checkIn(cross_x, cross_y)){
                ret++;
            }
        }
         
        return ret;
    }
     
    public static Bunsu plus(Bunsu b, int n){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu(s + (n*m), m);
    }
     
    public static Bunsu minus(Bunsu b, int n){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu(s - (n*m), m);
    }
     
    public static Bunsu mul(Bunsu b, int n){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu((s*n), m);
    }
     
    public static Bunsu div(Bunsu b, int n){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu(s, (m*n));
    }
     
    public static Bunsu plus(int n, Bunsu b){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu((n*m) + s, m);
    }
     
    public static Bunsu minus(int n, Bunsu b){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu((n*m) - s, m);
    }
     
    public static Bunsu mul(int n, Bunsu b){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu((s*n), m);
    }
     
    public static Bunsu div(int n, Bunsu b){
        int s = b.getS();
        int m = b.getM();
        return new Bunsu((m*n), s);
    }   
     
    public static Bunsu plus(Bunsu b1, Bunsu b2){
        int s1 = b1.getS();
        int m1 = b1.getM();
        int s2 = b2.getS();
        int m2 = b2.getM();
         
        return new Bunsu((s1*m2) + (s2*m1), (m1*m2));
    }
     
    public static Bunsu minus(Bunsu b1, Bunsu b2){
        int s1 = b1.getS();
        int m1 = b1.getM();
        int s2 = b2.getS();
        int m2 = b2.getM();
         
        return new Bunsu((s1*m2) - (s2*m1), (m1*m2));
    }
     
    public static Bunsu mul(Bunsu b1, Bunsu b2){
        int s1 = b1.getS();
        int m1 = b1.getM();
        int s2 = b2.getS();
        int m2 = b2.getM();
         
        return new Bunsu(s1*s2, m1*m2);
    }
     
    public static Bunsu div(Bunsu b1, Bunsu b2){
        int s1 = b1.getS();
        int m1 = b1.getM();
        int s2 = b2.getS();
        int m2 = b2.getM();
         
        return new Bunsu(s1*m2, (m1*s2));
    }
     
}