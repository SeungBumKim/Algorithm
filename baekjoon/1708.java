/**************************************************************
    Problem: 1708 (https://www.acmicpc.net/problem/1708)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1708ms
    Memory: 16876KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
	private static ArrayList<Spot> list = new ArrayList<Spot>();

	private static class Spot {
		int x;
		int y;

		public Spot(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int i, n = Integer.parseInt(br.readLine().trim());
		int x, y, stX = 0, stY = 0, ltIdx = 0, cX = 0, cY = 0, minIdx = 0;
		double ret, minG = Double.MAX_VALUE;
		String input[];
		for (i = 0; i < n; i++) {
			input = br.readLine().trim().split(" ");
			x = Integer.parseInt(input[0]);
			y = Integer.parseInt(input[1]);
			if (i == 0) {
				stX = x;
				stY = y;
				ltIdx = i;
				list.add(new Spot(x, y));
			} else {
				list.add(new Spot(x, y));
				if ((x < stX) || ((x == stX) && (y > stY))) {
					stX = x;
					stY = y;
					ltIdx = i;
				}
			}
		}
		list.remove(ltIdx);
		// System.out.println("st: " + stX + ", " + stY);
		boolean isInfi = false;
		long minLen = -1, tmpLen = -1;
		for (i = 0; i < list.size(); i++) {
			cX = list.get(i).getX();
			cY = list.get(i).getY();
			ret = getGradient(stX, stY, cX, cY);
			if (Double.isInfinite(ret)) {
				if (isInfi == false) {
					isInfi = true;
					minIdx = i;
					minLen = getLen(stX, stY, cX, cY);
				} else {
					tmpLen = getLen(stX, stY, cX, cY);
					if (tmpLen > minLen) {
						minLen = tmpLen;
						minIdx = i;
					}
				}
			} else {
				if (isInfi == false) {
					if (ret < minG) {
						minIdx = i;
						minG = ret;
						minLen = getLen(stX, stY, cX, cY);
					} else if (ret == minG) {
						tmpLen = getLen(stX, stY, cX, cY);
						if (tmpLen > minLen) {
							minLen = tmpLen;
							minIdx = i;
							minG = ret;
						}
					}
				}
			}
		}
		cX = list.get(minIdx).getX();
		cY = list.get(minIdx).getY();
		list.remove(minIdx);
		// System.out.println("sel: " + cX + ", " + cY);

		list.add(new Spot(stX, stY));
		int an = 2;
		double gg;
		long oldX = stX, oldY = stY;
		while (true) {
			long tmpX = oldX - cX, tmpY = oldY - cY;
			int maxIdx = 0;
			double maxGG = Double.MAX_VALUE;
			double maxR = Double.MIN_VALUE;
			long maxLen = 0;

			for (i = 0; i < list.size(); i++) {
				x = list.get(i).getX();
				y = list.get(i).getY();
				gg = getGradient(cX, cY, x, y);
				if(maxGG == gg){
					tmpLen = getLen(cX, cY, x, y);
					if (tmpLen > maxLen) {
						maxLen = tmpLen;
						maxIdx = i;
						maxGG = gg;
					}					
				}
				else{
					ret = getDgree(tmpX, tmpY, x - cX, y - cY);
					if (ret > maxR) {
						maxIdx = i;
						maxR = ret;
						maxLen = getLen(cX, cY, x, y);
						maxGG = gg;
					}
				}
			}
			oldX = cX;
			oldY = cY;
			cX = list.get(maxIdx).getX();
			cY = list.get(maxIdx).getY();
			// System.out.println(cX + ", " + cY);
			if (cX == stX && cY == stY) {
				break;
			}
			an++;
			list.remove(maxIdx);
		}

		System.out.println(an);
	}

	private static long getLen(long x, long y, long x1, long y1) {
		return ((x1 - x) * (x1 - x)) + ((y1 - y) * (y1 - y));
	}

	private static double getGradient(int x, int y, int x1, int y1) {
		return (double) ((double) y1 - (double) y) / (double) ((double) x1 - (double) x);
	}

	private static double getDgree(long x, long y, long x1, long y1) {
		double theta = (x * x1) + (y * y1);
		double i = Math.sqrt(((x * x) + (y * y)) * ((x1 * x1) + (y1 * y1)));
		theta = theta / i;

		//System.out.println(Math.acos(theta) + ", " + Math.acos(theta) * 180/Math.PI);
		return Math.acos(theta);
	}
}