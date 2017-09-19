/**************************************************************
    Problem: 2261 (https://www.acmicpc.net/problem/2261)
    User: magicguru
    Language: C++
    Result: Success
    Time: 52ms
    Memory: 2684KB
****************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <algorithm>

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

typedef struct {
	int x;
	int y;
}point_t;


bool cmp(point_t &a, point_t &b) {
	return a.x < b.x;
}

bool cmp1(point_t &a, point_t &b) {
	return a.y < b.y;
}

int partition(point_t a[], int l, int r) {
	int i, j;
	point_t t, pivot = a[l];
	i = l; j = r + 1;

	while (1) {
		do ++i; while (a[i].x <= pivot.x && i <= r);
		do --j; while (a[j].x > pivot.x);
		if (i >= j) break;
		t = a[i]; a[i] = a[j]; a[j] = t;
	}
	t = a[l]; a[l] = a[j]; a[j] = t;
	return j;
}


void quickSort(point_t a[], int l, int r) {
	int j;
	if (l < r) {
		// divide and conquer
		j = partition(a, l, r);
		quickSort(a, l, j - 1);
		quickSort(a, j + 1, r);
	}
}

int partitionY(point_t a[], int l, int r) {
	int i, j;
	point_t t, pivot = a[l];
	i = l; j = r + 1;

	while (1) {
		do ++i; while (a[i].y <= pivot.y && i <= r);
		do --j; while (a[j].y > pivot.y);
		if (i >= j) break;
		t = a[i]; a[i] = a[j]; a[j] = t;
	}
	t = a[l]; a[l] = a[j]; a[j] = t;
	return j;
}


void quickSortY(point_t a[], int l, int r) {
	int j;
	if (l < r) {
		// divide and conquer
		j = partitionY(a, l, r);
		quickSortY(a, l, j - 1);
		quickSortY(a, j + 1, r);
	}
}

point_t point[100001];
point_t point2[100001];
int N;

long find(int l, int r) {
	long ret = 2000000001;
	long tmpX, tmpY, tmpCal;

	if (l >= r) {
		return ret;
	}
	if ((r - l + 1) <= 3) {
		for (int i = l; i < r; i++) {
			for (int j = i + 1; j <= r; j++) {
				tmpX = point[i].x - point[j].x;
				tmpY = point[i].y - point[j].y;
				tmpCal = tmpX * tmpX + tmpY * tmpY;
				if (tmpCal < ret) ret = tmpCal;
			}
		}
		return ret;
	}

	long tmp1, tmp2;
	int mid = (l + r) / 2;
	tmp1 = find(l, mid - 1);
	tmp2 = find(mid + 1, r);
	ret = tmp1 < tmp2 ? tmp1 : tmp2;

	// check from mid
	int midx = point[mid].x;
	int midy = point[mid].y;
	int len = 0;
	for (int i = l; i < r; i++) {
		tmpX = midx - point[i].x;
		tmpX = tmpX * tmpX;
		if (tmpX < ret) {
			point2[len].x = point[i].x;
			point2[len++].y = point[i].y;
		}
	}

	//quickSortY(point2, 0, len - 1);
	sort(point2, point2 + len, cmp1);
	for (int i = 0; i < len - 1; i++) {
		for (int j = i + 1; j < len; j++) {
			tmpX = point2[i].x - point2[j].x;
			tmpY = point2[i].y - point2[j].y;
			tmpCal = tmpX * tmpX + tmpY * tmpY;
			if (tmpCal < ret) {
				ret = tmpCal;
			}
			else {
				break;
			}
		}
	}

	return ret;
}


int main() {
	N = scan();
	for (int i = 0; i < N; i++) {
		scanf("%d %d", &point[i].x, &point[i].y);
	}
	sort(point, point + N, cmp);
	//quickSort(point, 0, N - 1);
	printf("%ld", find(0, N - 1));
	return 0;
}