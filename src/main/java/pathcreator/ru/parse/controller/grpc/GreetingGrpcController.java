package pathcreator.ru.parse.controller.grpc;

import io.grpc.stub.StreamObserver;
import pathcreator.ru.parse.controller.grpc.handler.GreetingGrpcHandler;
import pathcreator.ru.parse.proto.greeting.model.RequestModel;
import pathcreator.ru.parse.proto.greeting.model.ResponseModel;
import pathcreator.ru.parse.proto.greeting.service.GreetingServiceProtoGrpc;

public final class GreetingGrpcController extends GreetingServiceProtoGrpc.GreetingServiceProtoImplBase {

    @Override
    public void greeting(final RequestModel request, final StreamObserver<ResponseModel> responseObserver) {
        GreetingGrpcHandler.greeting(request, responseObserver);
    }
}