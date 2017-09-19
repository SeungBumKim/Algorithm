/**************************************************************
    Problem: 1378 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=653&sca=3080)
    User: magicguru
    Language: Java
    Result: Success
    Time:115 ms
    Memory:8384 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Main{
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String input = br.readLine().trim();
         
        char c, t;
        int Answer = 0, n =0, v, i;
        Stack<Character> temp = new Stack<Character>();
        Stack<Integer> temp1 = new Stack<Integer>();
        for(i =0; i<input.length(); i++){
            c = input.charAt(i);
            if(temp.size() == 0){
                temp.push(c);
            }
            else{
                t = temp.peek();
                if((t == '(' && c == ')') || (t == '[' && c == ']')){
                    temp.pop();
                    if(c == ')'){
                        n = 2;
                    }
                    else if(c == ']'){
                        n = 3;
                    }
                     
                    t =0;
                    if(temp.size() > 0){
                        t = temp.peek();
                    }                   
                    if(t == '+'){
                        temp.pop();
                        v = temp1.pop();
                        v += n;
                        n = v;
                    }
 
                    temp1.push(n);
                    temp.push('+');     
                }
                else if(t ==  '+' && (c == ')' || c == ']')){
                    temp.pop();
                    t =0;
                    if(temp.size() > 0){
                        t = temp.peek();
                    }
                     
                    if(c == ')'){
                        if(t != '(')
                            break;
                        n = 2;
                    }
                    else if(c == ']'){
                        if(t != '[')
                            break;
                        n = 3;
                    }
                     
                    temp.pop();
                    v = temp1.pop();
                    v *= n;
                     
                    t =0;
                    if(temp.size() > 0){
                        t = temp.peek();
                    }                   
                    if(t == '+'){
                        temp.pop();
                        n = v;
                        v = temp1.pop();
                        v += n;
                    }
                     
                    temp1.push(v);
                    temp.push('+');
                }
                else{
                    temp.push(c);
                }
            }
        }
         
        if(i >= input.length() && temp1.size() == temp.size()){
            for(int j =0; j<temp.size(); j++){
                Answer += temp1.pop();
            }           
        }
 
        System.out.println(Answer);
    }
}