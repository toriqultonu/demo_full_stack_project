package com.autoindustry.backend.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class GraphQLConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private RequestDataFetcher requestDataFetcher;

    @Autowired
    private QuoteDataFetcher quoteDataFetcher;

    @Bean
    public GraphQL graphQL() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:graphql/schema.graphqls");
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(new InputStreamReader(resource.getInputStream()));

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("requests", requestDataFetcher.getAllRequests())
                        .dataFetcher("quotes", quoteDataFetcher.getQuotesByRequestId()))
                .type("Mutation", builder -> builder
                        .dataFetcher("submitRequest", requestDataFetcher.submitRequest())
                        .dataFetcher("submitQuote", quoteDataFetcher.submitQuote()))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
        return GraphQL.newGraphQL(schema).build();
    }
}