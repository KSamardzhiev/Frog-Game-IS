package com.ksamardzhiev.is.task.from;

import java.util.Scanner;
import java.util.Stack;

public class App {

	public static void main(String[] args) {
		System.out.println("Application start...");
		Utils utils = new Utils();
		
		//Take user input
		Scanner scanner = new Scanner (System.in);
		System.out.print("Enter numbre of frogs: ");  
		int input = scanner.nextInt();
		
		//Generate the searching pattern
		String pattern = "";
		for(int i = 0; i <= input * 2; i++){
			if(i < input){
				pattern += ">";
			} else if (i > input){
				pattern += "<";
			} else {
				pattern += "_";
			}
		}

		String targetPattern = utils.generateSearchingPattern(pattern);
		System.out.println("Start with pattern: " + pattern);
		System.out.println("Search for pattern: " + targetPattern);
		
		//Generate the graph with the root element
		Node root = new Node(pattern);
		root.setParentNode(null);
		Graph graph = new Graph(root);

		//Generate the whole graph
		Graph target = utils.generateGraph(graph);

		//Find the searching pattern
		Node result = utils.dfs(target, targetPattern);

		//Reverse the result Node to its parents and fill some Stack
		Node currentNode = result;
		Stack<Node> q = new Stack<Node>();
		while (true) {
			if (currentNode.getParentNode() == null) {
				q.add(currentNode);
				break;
			}
			q.add(currentNode);
			currentNode = currentNode.getParentNode();
		}
		
		//Print the whole path of the result node
		System.out.println("==================");
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			System.out.println(count + " " + q.pop().getValue());
		}
		System.out.println("==================");

	}
}
