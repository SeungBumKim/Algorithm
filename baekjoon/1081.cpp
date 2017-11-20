/**************************************************************
    Problem: 1081 (https://www.acmicpc.net/problem/1081)
    User: magicguru
    Language: C++
    Result: Success
    Time: 0ms
    Memory: 1116KB
****************************************************************/

#include <stdio.h>
#include <stdlib.h>

long sum[11];
long sumPow[11];
int LStr[12], UStr[12];
long LSum[12], USum[12];

long L, U;

int main() {
	sum[0] = 0;
	for (int i = 1; i < 10; i++) {
		sum[i] = sum[i - 1] + i;
	}
	sumPow[0] = 0;
	sumPow[1] = sum[9];
	long mul = 10;
	for (int i = 2; i < 11; i++) {
		sumPow[i] = (sumPow[i - 1] * 10) + (mul * sum[9]);
		mul *= 10;
	}

	scanf("%ld %ld", &L, &U);
	long q, r;
	q = L - 1;
	int lidx = 0;
	while (q > 0) {
		r = q % 10;
		q = q / 10;

		LStr[lidx++] = r;
	}
	for (int i = lidx - 1; i > 0; i--) {
		LSum[i-1] = LSum[i] + LStr[i];
	}

	q = U;
	int uidx = 0;
	while (q > 0) {
		r = q % 10;
		q = q / 10;

		UStr[uidx++] = r;
	}
	for (int i = uidx - 1; i > 0; i--) {
		USum[i-1] = USum[i] + UStr[i];
	}

	long lVal = 0;
	mul = 10;
	for (int i = 0; i < lidx; i++) {
		if (i == 0) {
			lVal += sum[LStr[i]] + (LStr[i] * LSum[i]);
		}
		else {
			lVal += LStr[i] * (sumPow[i] + 1);
			lVal += mul * LStr[i] * LSum[i];
			lVal += (LStr[i] > 1 ? sum[(LStr[i] -1)] * mul : 0);
			mul *= 10;
		}
	}

	long uVal = 0;
	mul = 10;
	for (int i = 0; i < uidx; i++) {
		if (i == 0) {
			uVal += sum[UStr[i]] + (UStr[i] * USum[i]);
		}
		else {
			uVal += UStr[i] * (sumPow[i] + 1);
			uVal += mul * UStr[i] * USum[i];
			uVal += (UStr[i] > 1 ? sum[(UStr[i] - 1)] * mul : 0);
			mul *= 10;
		}
	}

	// printf("%ld, %ld\n", lVal, uVal);
	printf("%ld", uVal - lVal);

	return 0;
}
