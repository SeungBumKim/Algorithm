/**************************************************************
    Problem:  (https://www.acmicpc.net/problem/2629)
    User: magicguru
    Language: C++
    Result: Success
    Time: 0ms
    Memory: 12908KB
****************************************************************/

#include <stdio.h>

bool map[15001];
int bitmap[15001][200];
int cnt[15001];
int input[30];
int N, M;

void check(int idx, int sum, int bit) {
	int tmp;
	int tmpBit;
	for (int i = idx; i < N; i++) {
		tmp = input[i] + sum;
		tmpBit = (1 << i) | bit;
		if (map[tmp]) {
			bitmap[tmp][cnt[tmp]++] = tmpBit;
		}
		else {
			map[tmp] = true;
			bitmap[tmp][cnt[tmp]++] = tmpBit;
			check(i + 1, tmp, tmpBit);
		}
	}
}


int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &input[i]);
	}
	check(0, 0, 0);
	scanf("%d", &M);
	int ball;
	bool find = false;
	for (int i = 0; i < M; i++) {
		find = false;
		scanf("%d", &ball);

		if (ball <= 15000) {
			if (map[ball]) {
				find = true;
			}
			else {
				int tmp;
				for (int j = 0; j < 15001; j++) {
					tmp = j + ball;
					if (tmp >= 15001) {
						break;
					}

					if (map[j] && map[tmp]) {
						for (int k = 0; k < cnt[j]; k++) {
							for (int l = 0; l < cnt[tmp]; l++) {
								if ((bitmap[j][k] & bitmap[tmp][l]) == 0) {
									find = true;
									break;
								}
							}
							if (find) {
								break;
							}
						}

						if (find) {
							break;
						}
					}
				}
			}
		}

		if (find) {
			printf("Y ");
		}
		else {
			printf("N ");
		}
	}
	return 0;
}
