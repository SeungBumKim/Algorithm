/**************************************************************
    Problem: 1415 (http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=691&sca=50&page=5)
    User: magicguru
    Language: C++
    Result: Success
    Time:3 ms
    Memory:8904 kb
****************************************************************/
 
 
#include <stdio.h>

// using cycle number 
// cycle number max value is denominator size.
#define MAX 1000002
int s, e;
int A, B;
int check[MAX];
int history[MAX];
 
int main() {
    scanf("%d %d", &A, &B);
    scanf("%d %d", &s, &e);
 
    int mod = A%B;
    int val;
 
    int loopSt, loopEnd;
    int idx = 1;
    check[mod] = idx;
    while (true) {
        mod *= 10;
        val = mod / B;
        mod = mod % B;
        history[idx] = val;
        idx++;
        if (check[mod] != 0) {
            loopSt = check[mod];
            loopEnd = idx -1;
            break;
        }
        else {
            check[mod] = idx;
        }
    }
 
    long long an = 0;
    long long loopSum = 0;
    int loopTerm = loopEnd - loopSt + 1;
    if (e <= loopEnd) {
        for (int i = s; i <= e; i++) {
            an += history[i];
        }
    }
    else {
        if (s < loopSt) {
            for (int i = s; i < loopSt; i++) {
                an += history[i];
            }
 
            int tmp = e - loopSt + 1;
            int tmpVal = tmp / loopTerm;
            int tmpMod = tmp % loopTerm;
            for (int i = loopSt; i <= loopEnd; i++) {
                loopSum += history[i];
            }
            for (int i = 0; i < tmpMod; i++) {
                an += history[loopSt + i];
            }
            an += loopSum * tmpVal;
        }
        else {
            int tmp = e - s + 1;
            int tmpVal = tmp / loopTerm;
            int tmpMod = tmp % loopTerm;
            for (int i = loopSt; i <= loopEnd; i++) {
                loopSum += history[i];
            }
            an += loopSum * tmpVal;
            tmp = (s - loopSt) % loopTerm;
            for (int i = 0; i < tmpMod; i++) {
                int tIdx = loopSt + tmp + i;
                if (tIdx > loopEnd) {
                    tIdx = loopSt + tIdx - loopEnd - 1;
                }
                an += history[tIdx];
            }
        }
    }
    printf("%lld", an);
 
    return 0;
}
