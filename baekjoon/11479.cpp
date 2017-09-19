/**************************************************************
    Problem: 11479 (https://www.acmicpc.net/problem/11479)
    User: magicguru
    Language: C++
    Result: Success
    Time: 1324ms
    Memory: 15748KB
****************************************************************/

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string str;
int cnt;

struct Comparator {
	const vector<int>& group;
	int t;

	Comparator(const vector<int>&_group, int _t) :group(_group), t(_t) {}
	bool operator()(int a, int b) {
		if (group[a] != group[b]) return group[a]<group[b];
		return group[a + t]<group[b + t];
	}
};

vector<int> getSuffixArray(const string&s) {
	int n = s.size();
	int t = 1;
	vector<int>group(n + 1);
	for (int i = 0; i<n; ++i) group[i] = s[i];
	group[n] = -1;
	vector<int>perm(n);
	for (int i = 0; i<n; ++i) perm[i] = i;
	while (t<n) {
		Comparator compareUsing2T(group, t);
		sort(perm.begin(), perm.end(), compareUsing2T);
		t *= 2;
		if (t >= n) break;
		vector<int>newGroup(n + 1);
		newGroup[n] = -1;
		newGroup[perm[0]] = 0;
		for (int i = 1; i<n; ++i)
			if (compareUsing2T(perm[i - 1], perm[i])) {
				newGroup[perm[i]] = newGroup[perm[i - 1]] + 1;
			}
			else {
				newGroup[perm[i]] = newGroup[perm[i - 1]];
			}
		group = newGroup;
	}
	return perm;
}

vector<int> getLCP(const vector<int>& pos, const string&s) {
	int n = pos.size();
	vector<int>rank(n), height(n);
	for (int i = 0; i<n; ++i)
		rank[pos[i]] = i;

	for (int i = 0, h = 0; i<n; ++i) {
		if (rank[i]>0) {
			int j = pos[rank[i] - 1];
			while (i + h <n && j + h<n && s[i + h] == s[j + h])
				h++;
			height[rank[i]] = h;
			if (h>0) h--;
		}
	}
	return height;
}

int main() {
	cin >> str;
	
	vector<int> ret = getSuffixArray(str);
	vector<int> lcp = getLCP(ret, str);
	long an = 0;
	int n = str.length();
	for (int i = 0; i < lcp.size(); i++) {
		an += (long)(n - ret[i] - lcp[i]);
	}
	cout << an;

	return 0;
}