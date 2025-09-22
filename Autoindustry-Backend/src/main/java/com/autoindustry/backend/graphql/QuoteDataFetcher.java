package com.autoindustry.backend.graphql;

import com.autoindustry.backend.model.Quote;
import com.autoindustry.backend.service.QuoteService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QuoteDataFetcher implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Quote> getQuotesByRequestId(String requestId, DataFetchingEnvironment env) {
        return quoteService.getQuotesByRequestId(requestId);
    }

    public Quote submitQuote(Map<String, Object> input, DataFetchingEnvironment env) {
        Quote quote = new Quote();
        // Set repairShop from context or input
        quote.setRequestId((String) input.get("requestId"));
        quote.setPriceBreakdown(objectMapper.valueToTree(input.get("priceBreakdown")));
        quote.setEstimatedHours((Double) input.get("estimatedHours"));
        quote.setTotalPrice((Double) input.get("totalPrice"));
        return quoteService.submitQuote(quote);
    }
}