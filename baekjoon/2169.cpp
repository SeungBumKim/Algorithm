/**************************************************************
    Problem: 2169 (https://www.acmicpc.net/problem/2169)
    User: magicguru
    Language: C++
    Result: Success
    Time: 136ms
    Memory: 1028KB
****************************************************************/

#include <stdio.h>

#define R 0
#define B 1
#define L 2
#define MAX 1000
#define VAL_MIN -1000000
int memo[MAX][MAX][3];
int map[MAX][MAX];
int N, M;

int find(int n, int m, int from) {
	int ret = VAL_MIN;
	int tmpRet;

	if (from == R && memo[n][m][L] > VAL_MIN) {
		return memo[n][m][L];
	}
	else if (from == L && memo[n][m][R] > VAL_MIN) {
		return memo[n][m][R];
	}
	else if (from == -1 && memo[n][m][B] > VAL_MIN) {
		return memo[n][m][B];
	}

	if (n == N - 1 && m == M - 1) {
		return map[n][m];
	}
	
	// right
	if (from != R && m < M - 1) {
		tmpRet = find(n, m + 1, L);
		ret = tmpRet > ret ? tmpRet : ret;
	}

	// bottom
	if (n < N - 1) {
		tmpRet = find(n + 1, m, -1);
		ret = tmpRet > ret ? tmpRet : ret;
	}

	// left 
	if (from != L && m > 0) {
		tmpRet = find(n, m - 1, R);
		ret = tmpRet > ret ? tmpRet : ret;
	}

	ret += map[n][m];
	if (from == R) {
		memo[n][m][L] = ret;
	}
	else if (from == L) {
		memo[n][m][R] = ret;
	}
	else {
		memo[n][m][B] = ret;
	}
	return ret;
}

int main() {
	scanf("%d %d", &N, &M);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
			memo[i][j][L] = VAL_MIN;
			memo[i][j][R] = VAL_MIN;
			memo[i][j][B] = VAL_MIN;
		}
	}

	printf("%d", find(0, 0, -1));

	return 0;
}
