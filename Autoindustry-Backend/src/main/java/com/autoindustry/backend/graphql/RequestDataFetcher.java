package com.autoindustry.backend.graphql;

import com.autoindustry.backend.document.RepairRequestDocument;
import com.autoindustry.backend.service.RequestService;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RequestDataFetcher implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private RequestService requestService;

    public List<RepairRequestDocument> getAllRequests(DataFetchingEnvironment env) {
        return requestService.getAllRequests();
    }

    public RepairRequestDocument submitRequest(Map<String, Object> input, DataFetchingEnvironment env) {
        RepairRequestDocument request = new RepairRequestDocument();
        request.setOwnerId((String) input.get("ownerId"));
        request.setDescription((String) input.get("description"));
        request.setCarBrand((String) input.get("carBrand"));
        request.setCarModel((String) input.get("carModel"));
        request.setYear((Integer) input.get("year"));
        request.setPartsNeeded((List<String>) input.get("partsNeeded"));
        return requestService.submitRequest(request);
    }
}