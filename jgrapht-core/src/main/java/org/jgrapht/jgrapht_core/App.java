package org.jgrapht.jgrapht_core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.RandomWalkIterator;

/**
 * Random Walk
 */
public class App {
	
	public static void main(String[] args){
		float D = 0;
		long M = 10000000L;
		Graph<String, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);
		float [] f = new float[317080];
		float [] n = new float[317080];
		RandomWalkIterator RandomWalkIterator = null;
		Scanner s = null;
		Random rand = new Random();
		
		for (int i = 0; i < 317080; i++) {
			String edge = String.valueOf(i);
			g.addVertex(edge);
		}
		
		System.out.println("<Vertices Creation Completed>");
		
		File file = new File("src\\graph\\com-dblp.txt");
		try {
			s = new Scanner(file);

			String FromNodeId;
			String ToNodeId;
			int count = 0;

			while (s.hasNextLine()) {
				FromNodeId = String.valueOf(s.nextInt());
				ToNodeId = String.valueOf(s.nextInt());
				g.addEdge(FromNodeId, ToNodeId);
				count++;
			}
			
			s.close();
			System.out.println("<Edges Creation Completed>");
			
			for (int i = 0; i < 317080; i++) {
				D += g.degreeOf(String.valueOf(i));
			}
			
			for (int i = 0; i < 317080; i++) {
				n[i] = g.degreeOf(String.valueOf(i)) / D;
			}
			
			int rand_int = rand.nextInt(317080);
			System.out.println("The starting point is randomly selected as: " + rand_int);
			f[rand_int] += 1.0 / M;
			RandomWalkIterator = new RandomWalkIterator<>(g, String.valueOf(rand_int));
			int j = 0;
			
			while (RandomWalkIterator.hasNext()) {
				Object vertex = RandomWalkIterator.next();
				f[Integer.parseInt(vertex.toString())] += 1.0 / M;
				j++;
				if (j == M) {
					break;
				}
			}
			
			System.out.println(count);
			System.out.println("D: " + D);
			
			float result = 0;
			for (int i = 0; i < 317080; i++) {
				float tmp = Math.abs(n[i] - f[i]);
				result += tmp;
			}
			
			System.out.printf("ℓ1 -distance (rounded to three decimal places) %.3f %n", result);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
}
