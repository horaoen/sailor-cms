package com.horaoen;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DeepSearch {
    
    private static Node root;
    public static void initData() {
        root = new Node();
        root.setValue("A");
        Node rootSon1 = new Node();
        rootSon1.setValue("B");
        Node rootSon2 = new Node();
        rootSon2.setValue("C");
        
        ArrayList<Node> rootChildren = new ArrayList<>();
        rootChildren.add(rootSon1);
        rootChildren.add(rootSon2);
        root.setChildren(rootChildren);
        
        Node rootSon1Son1 = new Node();
        rootSon1Son1.setValue("D");
        Node rootSon1Son2 = new Node();
        rootSon1Son2.setValue("E");
        
        ArrayList<Node> rootSon1Children = new ArrayList<>();
        rootSon1Children.add(rootSon1Son1);
        rootSon1Children.add(rootSon1Son2);
        rootSon1.setChildren(rootSon1Children);
    }
    
    @Test
    public void SearchChildren() {
        initData();
        Node node = root;
        SearchChildren(node);
    }
    
    public void SearchChildren(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getValue());
        List<Node> children = node.getChildren();
        if (children != null) {
            for (Node child : children) {
                SearchChildren(child);
            }
        }
    }
    
    
    @Data
    public static class Node {
        private String value;
        private Node parent;
        private List<Node> children;
    }
}

