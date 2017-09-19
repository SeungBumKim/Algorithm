/**************************************************************
    Problem: TIMETRIP (https://algospot.com/judge/problem/read/TIMETRIP)
    User: magicguru
    Language: c++
    Result: Success
    Time: 64ms
    Memory: 3.0KB
****************************************************************/

#include <stdio.h>
#include <queue>
#include <list>
#include <string>
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

class Node {
private:
	int to;
	int val;
	int isLoop;
public:
	Node(int to, int val, int loop) {
		this->to = to;
		this->val = val;
		this->isLoop = loop;
	}
	Node(int to, int loop) {
		this->to = to;
		this->isLoop = loop;
	}
	int getVal() { return this->val; }
	int getTo() { return this->to; }
	int getIsLoop() { return this->isLoop; }
};

#define INFVAL 1000000000
#define INFVAL2 -1000000000
#define MAX 100

list<Node> map[MAX];
int minVal[MAX];
int minLoop[MAX];
int maxVal[MAX];
int maxLoop[MAX];
int reach[MAX][MAX];

int main() {
	int T = scan();
	queue<Node> q;

	int f, t, v, idx, tmpMinVal, tmpMaxVal, isLoop;
	for (int tLoop = 0; tLoop < T; tLoop++) {
		int V = scan();
		int W = scan();


		memset(reach, -1, sizeof(int)*MAX*MAX);

		for (int i = 0; i < V; i++) {
			map[i].clear();
			maxVal[i] = INFVAL2;
			maxLoop[i] = 0;
			minVal[i] = INFVAL;
			minLoop[i] = 0;
		}

		for (int w = 0; w < W; w++) {
			f = scan();
			t = scan();
			v = scan();

			map[f].push_back(Node(t, v, 0));
			reach[f][t] = 1;
		}

		// 플로이드
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (reach[i][j] == -1 && reach[i][k] != -1 && reach[k][j] != -1) {
						reach[i][j] = 1;
					}
				}
			}
		}

		// 최소값  찾기
		minVal[0] = 0;
		maxVal[0] = 0;
		for (int i = 0; i < V - 1; i++) {
			for (int j = 0; j < V; j++) {
				list<Node>::iterator it;
				for (it = map[j].begin(); it != map[j].end(); ++it) {
					t = it->getTo();
					v = it->getVal();

					if (minVal[j] != INFVAL && minVal[t] > (minVal[j] + v)) {
						minVal[t] = minVal[j] + v;
					}

					if (maxVal[j] != INFVAL2 && maxVal[t] < (maxVal[j] + v)) {
						maxVal[t] = maxVal[j] + v;
					}
				}
			}
		}
		for (int j = 0; j < V; j++) {
			list<Node>::iterator it;
			for (it = map[j].begin(); it != map[j].end(); ++it) {
				t = it->getTo();
				v = it->getVal();

				// 최소값 Loop 계산
				if (minVal[j] != INFVAL && minVal[t] > (minVal[j] + v)) {
					if (reach[0][t] != -1 && reach[t][1] != -1) {
						minLoop[1] = 1;
					}
				}

				// 최대값 Loop 계산
				if (maxVal[j] != INFVAL2 && maxVal[t] < (maxVal[j] + v)) {
					if (reach[0][t] != -1 && reach[t][1] != -1) {
						maxLoop[1] = 1;
					}
				}
			}
		}

		if (minVal[1] == INFVAL) {
			printf("%s\n", "UNREACHABLE");
		}
		else {
			string anMin = (minLoop[1] == 1) ? "INFINITY" : to_string(minVal[1]);
			string anMax = (maxLoop[1] == 1) ? "INFINITY" : to_string(maxVal[1]);

			printf("%s %s\n", anMin.c_str(), anMax.c_str());
		}
	}

	return 0;
}