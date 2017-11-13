/**************************************************************
    Problem: 1620 (https://www.acmicpc.net/problem/1620)
    User: magicguru
    Language: C++
    Result: Success
    Time: 132ms
    Memory: 18432KB
****************************************************************/

#include <stdio.h>
#include <unordered_map>

using namespace std;

char monster[100001][21];
int N, M;

int main() {
	unordered_map <string , int> mapChar;
	unordered_map <int, char*> mapInt;
	
	scanf("%d %d", &N, &M);
	for (int i = 1; i <= N; i++) {
		scanf("%s", &monster[i]);
		mapInt[i] = monster[i];
		mapChar[monster[i]] = i;
	}
	
	char tmp[20];
	int num;
	for (int i = 0; i < M; i++) {
		scanf("%s", &tmp);
		num = atoi(tmp);
		if (num > 0) {
			printf("%s\n", mapInt[num]);
		}
		else {
			printf("%d\n", mapChar[tmp]);
		}
	}

	return 0;
}
