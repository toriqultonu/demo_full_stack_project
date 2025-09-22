package com.autoindustry.backend.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/graphql")
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping
    public ResponseEntity<Map<String, Object>> execute(@RequestBody Map<String, Object> request) {
        ExecutionResult result = graphQL.execute(request.get("query").toString());
        return ResponseEntity.ok(result.toSpecification());
    }
}