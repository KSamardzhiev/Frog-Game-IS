package com.ksamardzhiev.is.task.from;

import java.util.ArrayList;

public class Node {

	private ArrayList<Node> childrenNodes = new ArrayList<Node>();
	private Node parentNode;
	private String value;
	
	public Node(String value){
		this.value = value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public void setParentNode(Node node){
		this.parentNode = node;
	}
	
	public Node getParentNode(){
		return this.parentNode;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void addChild(Node child){
		this.childrenNodes.add(child);
	}
	
	public void addChildren(ArrayList<Node> nodes){
		this.childrenNodes = nodes;
	}
	
	public int getChildrenSize(){
		return this.childrenNodes.size();
	}
	
	public ArrayList<Node> getChildren(){
		return this.childrenNodes;
	}
	
}
