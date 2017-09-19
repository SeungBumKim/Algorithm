/**************************************************************
    Problem: NERD2 (https://algospot.com/judge/problem/read/NERD2)
    User: magicguru
    Language: Java
    Result: Success
    Time: 1388ms
    Memory: 2.1KB
****************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
	private static TreeSet<Node> tree = new TreeSet<Node>();
	
	private static class Node implements Comparable<Node>{
		int p;
		int n;
		
		public Node(int p, int n){
			this.p = p;
			this.n = n;
		}
		
		public int getP(){
			return this.p;
		}
		public int getN(){
			return this.n;
		}

		@Override
		public int compareTo(Node arg0) {
			if(this.p > arg0.getP()){
				return 1;
			}
			else if(this.p < arg0.getP()){
				return -1;
			}
			else{
				return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/home/sb0729kim/test/testsw/input_test"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		int n, i;
		int pN, nN, tpN, tnN;
		long an;
		Node tmpNode, rNode;
		String input[];
		Iterator<Node> it;
		SortedSet<Node> bSet;
		NavigableSet<Node> rSet;
		boolean isAdd =false;
		for(int t=0; t<T; t++){
			n = Integer.parseInt(br.readLine().trim());
			tree.clear();
			an = 0;
			
			for(i =0; i<n; i++){
				input = br.readLine().trim().split(" ");
				pN = Integer.parseInt(input[0]);
				nN = Integer.parseInt(input[1]);
				
				tmpNode = new Node(pN, nN);
				isAdd = true;
				if(i == 0){
					an++;
					tree.add(tmpNode);
				}
				else{
					bSet = tree.tailSet(tmpNode);
					it = bSet.iterator();
					while(it.hasNext()){
						if(it.next().getN() > nN){
							isAdd = false;
						}
						break;
					}							
					
					rSet = tree.headSet(tmpNode, true);
					it = rSet.descendingIterator();
					while(it.hasNext()){
						rNode = it.next();
						if(rNode.getN() < nN){
							it.remove();
						}
						else{
							break;
						}
					}
					
					if(isAdd){
						tree.add(tmpNode);
					}

					an += tree.size();
				}
			}
			System.out.println(an);	
		}
	}
}