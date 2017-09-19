/**************************************************************
    Problem: NUMB3RS (https://algospot.com/judge/problem/read/NUMB3RS)
    User: magicguru
    Language: c++
    Result: Success
    Time: 12ms
    Memory: 1.5KB
****************************************************************/

#include <stdio.h>
#include <string.h>

using namespace std;

/* INPUT */
char buf[4096], *ex = buf + 4096, *ch = ex;
inline bool read()
{
	if (++ch >= ex) { ch = buf; fread(buf, 1, 4096, stdin); }
	return true;
}
int scan(int n = 0)
{
	 scanf("%d", &n);
	//	while (read() && (*ch < '0' || *ch > '9'));
	//	do { n = n * 10 + *ch - '0'; } while (read() && *ch >= '0' && *ch <= '9');
	return n;
}

#define MAX_TOWN 51
#define MAX_DAY 101
int T, N, D, P; 
int AN_N;
int linkIdx[MAX_TOWN];
int link[MAX_TOWN][MAX_TOWN];
int anmap[MAX_TOWN];
double memo[MAX_TOWN][MAX_DAY];


double find(int town, int day) {
	if (day == 0 && town == P) {
		return 1;
	}
	if (day <= 0) {
		return 0;
	}

	if (memo[town][day] != 0)
		return memo[town][day];

	double out = 0;
	double ret;
	for (int i = 0; i < linkIdx[town]; i++) {
		ret = find(link[town][i], day - 1);
		if (ret > 0) {
			out += (ret*((double)((double)1 / (double)linkIdx[link[town][i]])));
		}
	}

	memo[town][day] = out;
	return memo[town][day];
}


int main() {
	T = scan();
	for (int t = 0; t < T; t++) {
		N = scan();
		D = scan();
		P = scan();

		memset(linkIdx, 0, sizeof(int)*MAX_TOWN);
		memset(link, 0, sizeof(int)*MAX_TOWN*MAX_TOWN);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int val = scan();
				if (val == 1) {
					link[i][linkIdx[i]++] = j;
				}
			}
		}				

		AN_N = scan();
		memset(memo, 0, sizeof(double)*MAX_TOWN*MAX_DAY);
		for (int i = 0; i < AN_N; i++) {
			printf("%0.08f ", find(scan(), D));
		}
	}
}