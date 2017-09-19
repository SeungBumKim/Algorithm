/**************************************************************
    Problem: PASS486 (https://algospot.com/judge/problem/read/PASS486)
    User: magicguru
    Language: c++
    Result: Success
    Time: 2808ms
    Memory: 1.5KB
****************************************************************/

#include <stdio.h>
#include <string.h>

#define MAX 10000001

char buf[4096], *ex = buf + 4096, *ch = ex;


int check[MAX];
int minPrime[MAX];

inline bool read()
{
	if (++ch >= ex) { ch = buf; fread(buf, 1, 4096, stdin); }
	return true;
}

int scan(int n = 0)
{
	while (read() && (*ch < '0' || *ch > '9'));
	do { n = n * 10 + *ch - '0'; } while (read() && *ch >= '0' && *ch <= '9');
	return n;
}

bool findDivisor(int n, int base) {
	bool isSame = false;
	int cnt = 0;	// add me

	if (base == 1) {
		cnt = 1;
	}
	else if (check[base] == 0) {
		cnt = 2;
	}
	else {
		cnt = 1;
		int tmp;
		int val;
		while (base>1) {
			val = minPrime[base];
			tmp = 0;
			while (base % val == 0) {
				base /= val;
				tmp++;
			}
			cnt *= (tmp + 1);
			if (cnt > n) {
				break;
			}
		}
		cnt = cnt == 1 ? 0 : cnt;
	}

	if (cnt == n) {
		isSame = true;
	}

	return isSame;
}

void makePrime() {
	int i, j, len;
	for (i = 2; i < MAX; i++) {
		if (check[i] == 0) {
			minPrime[i] = i;
			for (j = 2; i*j < MAX; j++) {
				len = i*j;
				check[len] = 1;
				if (minPrime[len] == 0) {
					minPrime[i*j] = i;
				}
			}
		}
	}
}

int main() {
	int T = scan();
	memset(check, 0, sizeof(int) * MAX);
	memset(minPrime, 0, sizeof(int) * MAX);
	makePrime();
	for (int t = 0; t < T; t++) {
		int n = scan();
		int lo = scan();
		int hi = scan();

		int cnt = 0;

		for (int i = lo; i <= hi; i++) {
			if (findDivisor(n, i)) {
				cnt++;
			}
		}

		printf("%d\n", cnt);
	}
	return 0;
}