// RuleController.java
package com.example.AST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.AST.dto.CombineRuleRequest;
import com.example.AST.dto.EvaluateRuleRequest;
import com.example.AST.model.Node;
import com.example.AST.service.RuleService;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/create")
    public Node createRule(@RequestBody String ruleString) {
        return ruleService.createRule(ruleString);
    }

    @PostMapping("/combine")
    public Node combineRules(@RequestBody CombineRuleRequest request, @RequestParam String operator) {
        return ruleService.combineRules(request.getRule1(), request.getRule2(), operator);
    }

    @PostMapping("/evaluate")
    public boolean evaluateRule(@RequestBody EvaluateRuleRequest request) {
        return ruleService.evaluateRule(request.getRule(), request.getData());
    }
}
