package com.marketplace.graphql;

import com.marketplace.entity.Quote;
import com.marketplace.service.RepairService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteResolver implements GraphQLQueryResolver {

    @Autowired
    private RepairService service;

    public Quote getQuote(Long id) {
        return service.getQuote(id); // Assume method
    }

    // Add resolvers for requests, etc.
}