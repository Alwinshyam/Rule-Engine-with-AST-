package com.example.AST.dto;

import com.example.AST.model.Node;
import java.util.Map;

public class EvaluateRuleRequest {
    private Node rule;
    private Map<String, Object> data;

    public Node getRule() {
        return rule;
    }

    public void setRule(Node rule) {
        this.rule = rule;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
