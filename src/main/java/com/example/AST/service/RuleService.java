package com.example.AST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.AST.model.Node;
import com.example.AST.repository.RuleRepository;

import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public Node createRule(String ruleString) {
        return parseRule(ruleString);
    }

    public Node combineRules(Node rule1, Node rule2, String operator) {
        Node combined = new Node();
        combined.setType(operator);
        combined.setLeft(rule1);
        combined.setRight(rule2);
        return ruleRepository.save(combined);
    }

    public boolean evaluateRule(Node node, Map<String, Object> data) {
        if (node.getType().equals("operand")) {
            return evaluateCondition(node.getValue(), data);
        }
        boolean leftResult = evaluateRule(node.getLeft(), data);
        boolean rightResult = evaluateRule(node.getRight(), data);
        return node.getType().equals("AND") ? leftResult && rightResult : leftResult || rightResult;
    }

    private boolean evaluateCondition(String condition, Map<String, Object> data) {
        if (condition.contains(">")) {
            String[] parts = condition.split(">");
            String field = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            return (int) data.get(field) > value;
        } else if (condition.contains("=")) {
            String[] parts = condition.split("=");
            String field = parts[0].trim();
            String value = parts[1].trim().replace("'", "");
            return data.get(field).equals(value);
        } else if (condition.contains("<")) {
            String[] parts = condition.split("<");
            String field = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            return (int) data.get(field) < value;
        }
        return false;
    }

    private Node parseRule(String ruleString) {
        ruleString = ruleString.trim();
        if (ruleString.startsWith("(") && ruleString.endsWith(")")) {
            ruleString = ruleString.substring(1, ruleString.length() - 1).trim();
        }

        if (ruleString.contains(" AND ")) {
            String[] parts = ruleString.split(" AND ", 2);
            Node root = new Node();
            root.setType("AND");
            root.setLeft(parseRule(parts[0].trim()));
            root.setRight(parseRule(parts[1].trim()));
            return root;
        } else if (ruleString.contains(" OR ")) {
            String[] parts = ruleString.split(" OR ", 2);
            Node root = new Node();
            root.setType("OR");
            root.setLeft(parseRule(parts[0].trim()));
            root.setRight(parseRule(parts[1].trim()));
            return root;
        } else {
            Node leaf = new Node();
            leaf.setType("operand");
            leaf.setValue(ruleString.replace("\"", "").trim());
            return leaf;
        }
    }
}
