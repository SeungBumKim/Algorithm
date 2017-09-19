/**************************************************************
    Problem: OCR (https://algospot.com/judge/problem/read/OCR)
    User: magicguru
    Language: c++
    Result: Success
    Time: 844ms
    Memory: 2.0KB
****************************************************************/

#include <stdio.h>
#include <iostream>
#include <string>
#include <unordered_map>
#include <math.h>
#include <limits>
#include <vector>


using namespace std;

int M, Q, N;
string strArr[501];
double nextVal[501][501];
double validVal[501][501];
int input[501];

vector<int> ans;

double memo[102][502];
int choice[102][502];

const double MIN_VAL = -std::numeric_limits<double>::max();

void setAns(int order, int lastStr) {
	ans.push_back(choice[order][lastStr]);
	if (order < N - 1)
		setAns(order + 1, choice[order][lastStr]);
}

double solve(int order, int lastStr) {
	if (order == N)
		return 0;//log(1) = 0
	if (memo[order][lastStr] != 1)
		return memo[order][lastStr];
	double ret = MIN_VAL, comp;
	int pick = 0;
	for (int i = 1; i <= M; ++i) {
		comp = nextVal[lastStr][i] + validVal[i][input[order]] + solve(order + 1, i);
		if (ret < comp) {
			ret = comp;
			pick = i;
		}
	}
	choice[order][lastStr] = pick;
	memo[order][lastStr] = ret;
	return ret;
}

int main() {
	int m, m2;
	double tmp;
	scanf("%d %d", &M, &Q);

	for (m = 0; m < M; m++) {
		cin >> strArr[m + 1];
	}

	for (m = 0; m < M; m++) {
		cin >> tmp;
		nextVal[0][m + 1] = log(tmp);
	}	

	for (m = 0; m < M; m++) {
		for (m2 = 0; m2 < M; m2++) {
			cin >> tmp;
			nextVal[m + 1][m2 + 1] = log(tmp);
		}		
	}

	for (m = 0; m < M; m++) {
		for (m2 = 0; m2 < M; m2++) {
			cin >> tmp;
			validVal[m + 1][m2 + 1] = log(tmp);
		}
	}

	int i, j, k;
	string tmpStr;
	for (i = 0; i < Q; i++) {
		scanf("%d", &N);
		for (j = 0; j < N; j++) {
			cin >> tmpStr;
			for (k = 0; k <= M; k++) {
				if (strArr[k].compare(tmpStr) == 0) {
					input[j] = k;
					break;
				}
			}
		}

		ans.clear();
		for (j = 0; j < 102; ++j)
			for (int k = 0; k < 502; ++k) {
				memo[j][k] = 1;
				choice[j][k] = 0;
			}

		solve(0, 0);
		setAns(0, 0);
		for (j = 0; j < ans.size(); ++j) {
			if (j != 0)
				cout << " " << strArr[ans[j]];
			else
				cout << strArr[ans[j]];
		}
		printf("\n");
	}

	return 0;
}