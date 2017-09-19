/**************************************************************
    Problem: 2913 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2171&sca=3050)
    User: magicguru
    Language: Java
    Result: Success
    Time:1015 ms
    Memory:36792 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
 
public class Main {
    private static int maxScore = -1;
    private static int lArr[];
    private static int rArr[];
    private static int rSumArr[];
    private static int lMaxArr[];
    private static int rMaxArr[];
    private static int lMinArr[];
    private static int rMinArr[];
    private static ArrayList<Integer> history = new ArrayList<Integer>();
    private static ArrayList<Integer> historysc = new ArrayList<Integer>();
    private static HashMap<Integer, Integer> hi = new HashMap<Integer, Integer>();
 
    private static class Card implements Comparable<Card> {
        int lIdx;
        int rIdx;
        int score;
 
        Card(int lIdx, int rIdx, int score) {
            this.lIdx = lIdx;
            this.rIdx = rIdx;
            this.score = score;
        }
 
        public int getLIdx() {
            return this.lIdx;
        }
 
        public int getRIdx() {
            return this.rIdx;
        }
 
        public int getScore() {
            return this.score;
        }
 
        @Override
        public int compareTo(Card o) {
            if (this.score > o.getScore()) {
                return -1;
            } else if (this.score < o.getScore()) {
                return 1;
            } else {
                if (this.rIdx < o.getRIdx()) {
                    return -1;
                } else if (this.lIdx < o.getLIdx()) {
                    return 1;
                }
                return 0;
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine().trim());
        String in1[] = br.readLine().trim().split(" ");
        String in2[] = br.readLine().trim().split(" ");
 
        lArr = new int[n];
        rArr = new int[n];
        rSumArr = new int[n];
        lMaxArr = new int[n];
        rMaxArr = new int[n];
        lMinArr = new int[n];
        rMinArr = new int[n];
 
        for (int i = 0; i < n; i++) {
            lArr[i] = Integer.parseInt(in1[i]);
            rArr[i] = Integer.parseInt(in2[i]);
            // rSumArr[i] = i == 0 ? rArr[i] : rArr[i] + rSumArr[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                lMaxArr[i] = lArr[i];
                rMaxArr[i] = rArr[i];
                lMinArr[i] = lArr[i];
                rMinArr[i] = rArr[i];
                rSumArr[i] = rArr[i];
            } else {
                lMaxArr[i] = lMaxArr[i + 1] > lArr[i] ? lMaxArr[i + 1]
                        : lArr[i];
                rMaxArr[i] = rMaxArr[i + 1] > rArr[i] ? rMaxArr[i + 1]
                        : rArr[i];
 
                lMinArr[i] = lMinArr[i + 1] < lArr[i] ? lMinArr[i + 1]
                        : lArr[i];
                rMinArr[i] = rMinArr[i + 1] < rArr[i] ? rMinArr[i + 1]
                        : rArr[i];
 
                rSumArr[i] = rSumArr[i + 1] + rArr[i];
            }
 
        }
 
//      int lIdx = 0, rIdx = 0;
//      maxScore = 0;
//      while (true) {
//          if (lIdx == n || rIdx == n) {
//              break;
//          }
//
//          if (lArr[lIdx] > rArr[rIdx]) {
//              maxScore += rArr[rIdx];
//              rIdx++;
//          } else if (lArr[lIdx] < rArr[rIdx]) {
//              if (lMaxArr[lIdx] > rArr[rIdx]) {
//                  lIdx++;
//              } else {
//                  lIdx++;
//                  rIdx++;
//              }
//          } else {
//              if (lMaxArr[lIdx] > rArr[rIdx]) {
//                  lIdx++;
//              } else {
//                  lIdx++;
//                  rIdx++;
//              }
//          }
//      }
//
//      PriorityQueue<Card> qu = new PriorityQueue<Card>();
//      qu.add(new Card(0, 0, 0));
//      int score;
//      Card card;
//      while (!qu.isEmpty()) {
//          card = qu.poll();
//          lIdx = card.getLIdx();
//          rIdx = card.getRIdx();
//          score = card.getScore();
//
//          // System.out.println(lIdx + " , " + rIdx + ", s: " + score);
//
//          if (lIdx == n || rIdx == n) {
//              if (score > maxScore) {
//                  maxScore = score;
//              }
//              continue;
//          }
//
//          if (rMinArr[rIdx] >= lMaxArr[lIdx]) {
//              if (score > maxScore) {
//                  maxScore = score;
//              }
//              continue;
//          }
//
//          if (lMinArr[lIdx] > rMaxArr[rIdx]) {
//              score += rSumArr[rIdx];
//              if (score > maxScore) {
//                  maxScore = score;
//              }
//              continue;
//          }
//
//          if ((rSumArr[rIdx] + score) <= maxScore) {
//              continue;
//          }
//
//          int historyVal = lIdx * 10000 + rIdx;
//          if (history.contains(historyVal)) {
//              if ((historysc.get(history.indexOf(historyVal)) >= score)) {
//                  continue;
//              } else {
//                  historysc.add(history.indexOf(historyVal), score);
//              }
//          } else {
//              history.add(historyVal);
//              historysc.add(history.indexOf(historyVal), score);
//          }
//
//          // check
//          if (lArr[lIdx] > rArr[rIdx]) {
//              qu.add(new Card(lIdx, rIdx + 1, score + rArr[rIdx]));
//          } else {
//              // discard left
//              if (rArr[rIdx] < lMaxArr[lIdx]) {
//                  qu.add(new Card(lIdx + 1, rIdx, score));
//              }
//
//              // discard all
//              if (lArr[lIdx] <= rMaxArr[rIdx]) {
//                  qu.add(new Card(lIdx + 1, rIdx + 1, score));
//              }
//          }
//      }
 
        //game(0, 0, 0, n, 0);
        System.out.println(game(0, 0, 0, n, 0));
        // System.out.println(game2(0,0,0,n));
    }
 
    private static int game(int lIdx, int rIdx, int score, int n, int sum) {
        if (lIdx == n || rIdx == n) {
            if (sum > maxScore) {
                maxScore = sum;
            }
            return score;
        }
 
        if (rMinArr[rIdx] >= lMaxArr[lIdx]) {
            if (sum > maxScore) {
                maxScore = sum;
            }
            return score;
        }
 
        if (lMinArr[lIdx] > rMaxArr[rIdx]) {
            score += rSumArr[rIdx];
            sum += rSumArr[rIdx];
            if (sum > maxScore) {
                maxScore = sum;
            }
            return score;
        }
 
        if ((rSumArr[rIdx] + sum) <= maxScore) {
            return score;
        }
 
 
        int historyVal = lIdx * 10000 + rIdx;
        if(hi.containsKey(historyVal)){
            return score + hi.get(historyVal);
        }
         
//      if (history.contains(historyVal)) {
//          // System.out.println(lIdx + " , " + rIdx + ", s: " + score);
//          return score + historysc.get(history.indexOf(historyVal));
//      }
         
//      int historyVal = lIdx * 10000 + rIdx;
//      if (history.contains(historyVal)) {
//          if ((historysc.get(history.indexOf(historyVal)) >= score)) {
//              return;
//          } else {
//              historysc.add(history.indexOf(historyVal), score);
//          }
//      } else {
//          history.add(historyVal);
//          historysc.add(history.indexOf(historyVal), score);
//      }
 
        // System.out.println(lIdx + " , " + rIdx + ", s: " + score);
 
        int ret = -1, maxRet = -1;
 
        // check
        if (lArr[lIdx] > rArr[rIdx]) {
            ret = game(lIdx, rIdx + 1, rArr[rIdx], n, sum + rArr[rIdx]);
            if (maxRet < ret) {
                maxRet = ret;
            }
        }
        else{
            // discard left
            if (rArr[rIdx] <= lMaxArr[lIdx]) {
                ret = game(lIdx + 1, rIdx, 0, n, sum);
            }
 
            if (maxRet < ret) {
                maxRet = ret;
            }
            // discard all
            if (lArr[lIdx] <= rMaxArr[rIdx]) {
                ret = game(lIdx + 1, rIdx + 1, 0, n, sum);
            }
            if (maxRet < ret) {
                maxRet = ret;
            }
        }
         
        hi.put(historyVal, maxRet);
        //history.add(historyVal);
        //historysc.add(history.indexOf(historyVal), maxRet);
 
        return score + maxRet;
    }
 
    private static int game2(int lIdx, int rIdx, int score, int n) {
        if (lIdx == n || rIdx == n) {
            return score;
        }
 
        if (rMinArr[rIdx] > lMaxArr[lIdx]) {
            return score;
        }
 
        if (lMinArr[lIdx] > rMaxArr[rIdx]) {
            score += rSumArr[rIdx];
            return score;
        }
 
        if ((rSumArr[rIdx] + score) <= maxScore) {
            return score;
        }
 
        int historyVal = lIdx * 10000 + rIdx;
        if (history.contains(historyVal)) {
            // System.out.println(lIdx + " , " + rIdx + ", s: " + score);
            return score + historysc.get(history.indexOf(historyVal));
        }
 
        // System.out.println(lIdx + " , " + rIdx + ", s: " + score);
 
        int ret = -1, maxRet = -1;
        // check
        if (lArr[lIdx] > rArr[rIdx]) {
            ret = game2(lIdx, rIdx + 1, rArr[rIdx], n);
            if (maxRet < ret) {
                maxRet = ret;
            }
        } else {
            // discard left
            if (rArr[rIdx] <= lMaxArr[lIdx]) {
                ret = game2(lIdx + 1, rIdx, 0, n);
            }
            if (maxRet < ret) {
                maxRet = ret;
            }
 
            // discard all
            if (lArr[lIdx] <= rMaxArr[rIdx]) {
                ret = game2(lIdx + 1, rIdx + 1, 0, n);
            }
            if (maxRet < ret) {
                maxRet = ret;
            }
        }
 
        history.add(historyVal);
        historysc.add(history.indexOf(historyVal), maxRet);
 
        return score + maxRet;
    }
}