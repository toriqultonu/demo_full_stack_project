package com.marketplace.grpc;

import com.marketplace.service.InventoryService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class InventoryGrpcService extends InventoryServiceGrpc.InventoryServiceImplBase {

    @Autowired
    private InventoryService service;

    @Override
    public void addPart(AddPartRequest request, StreamObserver<SparePart> responseObserver) {
        SparePart part = service.addPart(mapFromGrpc(request.getPart()));
        responseObserver.onNext(mapToGrpc(part));
        responseObserver.onCompleted();
    }

    @Override
    public void getPart(GetPartRequest request, StreamObserver<SparePart> responseObserver) {
        SparePart part = service.getPart(request.getId());
        responseObserver.onNext(mapToGrpc(part));
        responseObserver.onCompleted();
    }

    // Mapping methods
    private SparePart mapToGrpc(com.marketplace.entity.SparePart entity) {
        // Implement
        return null;
    }

    private com.marketplace.entity.SparePart mapFromGrpc(SparePart grpc) {
        // Implement
        return null;
    }
}