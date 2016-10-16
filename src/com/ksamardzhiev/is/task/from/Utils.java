package com.ksamardzhiev.is.task.from;

import java.util.ArrayList;
import java.util.Stack;

public class Utils {

	public Utils(){
		
	}
	
	public Graph generateGraph(Graph graph){
		ArrayList<Node> nodes = generateNodes(graph.getRoot());
		graph.getRoot().addChildren(nodes);
		
		generateChildren(graph.getRoot());
		return graph;
	}
	
	public void generateChildren(Node node){
		if(node.getChildren().size() == 0){
			return;
		} else {
			for(Node nodeChild : node.getChildren()){
				ArrayList<Node> nodes = generateNodes(nodeChild);
				nodeChild.addChildren(nodes);
				nodeChild.setParentNode(node);
				generateChildren(nodeChild);
			}
		}
	}
	
	public Node dfs(Graph tree, String searchingPattern){
		Stack<Node> visited = new Stack<Node>();
		Stack<Node> working = new Stack<Node>();
		working.push(tree.getRoot());
		int count = 0;
		while(!working.isEmpty()){
			count++;
			Node currentNode = working.pop();
			if(visited.contains(currentNode)){
				continue;
			}
			visited.push(currentNode);
			if(currentNode.getValue().equals(searchingPattern)){
				//System.out.println("========= Current node: " + count +"*"+ currentNode.getValue());
				return currentNode;
				//break;
			} else {
				//System.out.println("Current node: " + count +"*"+ currentNode.getValue());
			}
			for(Node child : currentNode.getChildren()){
				working.push(child);
			}
		}
		
		return null;
	}
	
	public ArrayList<Node> generateNodes(Node startingNode){
		String startingPoint = startingNode.getValue();
		ArrayList<Node> nodes = new ArrayList<Node>();
		//System.out.println("Start generating nodes...");
		int free_position = startingPoint.indexOf("_");
		if(free_position - 1 >= 0 && startingPoint.charAt(free_position - 1) == '>'){
			String nodeValue = startingPoint.substring(0, free_position-1) + '_' + '>' + startingPoint.substring(free_position+1, startingPoint.length());
			Node node = new Node(nodeValue);
			node.setParentNode(startingNode);
			nodes.add(node);
			//System.out.println(nodeValue);
		}
		
		if(free_position + 1 < startingPoint.length() && startingPoint.charAt(free_position + 1) == '<'){
			String nodeValue = startingPoint.substring(0, free_position) + '<' + '_' + startingPoint.substring(free_position+2, startingPoint.length());
			Node node = new Node(nodeValue);
			node.setParentNode(startingNode);
			nodes.add(node);
			//System.out.println(nodeValue);
		}
		
		if(free_position - 2 >= 0 && startingPoint.charAt(free_position - 2) == '>'){
			String nodeValue = startingPoint.substring(0, free_position-2) + '_' + startingPoint.charAt(free_position - 1) + '>' + startingPoint.substring(free_position+1, startingPoint.length());
			Node node = new Node(nodeValue);
			node.setParentNode(startingNode);
			nodes.add(node);
			//System.out.println(nodeValue);
		}
		
		if(free_position + 2 < startingPoint.length() && startingPoint.charAt(free_position + 2) == '<'){
			String nodeValue = startingPoint.substring(0, free_position) + '<' + startingPoint.charAt(free_position + 1) + '_' + startingPoint.substring(free_position+3, startingPoint.length());
			Node node = new Node(nodeValue);
			node.setParentNode(startingNode);
			nodes.add(node);
			//System.out.println(nodeValue);
		}
		
		return nodes;
	}
	
	public String generateSearchingPattern(String pattern){
		int freeSpacePosition = pattern.indexOf('_');
		String targetPattern = pattern.substring(freeSpacePosition + 1, pattern.length()) + '_'
				+ pattern.substring(0, freeSpacePosition);
		return targetPattern;
	}
}
