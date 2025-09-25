package pathcreator.ru.parse.controller.grpc.handler;

import io.grpc.stub.StreamObserver;
import pathcreator.ru.parse.exception.ApiException;
import pathcreator.ru.parse.mapper.GrpcStatusCode;
import pathcreator.ru.parse.proto.greeting.model.RequestModel;
import pathcreator.ru.parse.proto.greeting.model.ResponseModel;
import pathcreator.ru.parse.service.GreetingService;
import pathcreator.ru.parse.util.TokenUtilGrpc;

public final class GreetingGrpcHandler {

    private GreetingGrpcHandler() {
    }

    public static void greeting(final RequestModel request, final StreamObserver<ResponseModel> responseObserver) {
        try {
            final String token = TokenUtilGrpc.currentToken();
            final String out = GreetingService.greeting(request.getValue(), token);
            responseObserver.onNext(ResponseModel.newBuilder().setValue(out).build());
            responseObserver.onCompleted();
        } catch (final ApiException ex) {
            responseObserver.onError(GrpcStatusCode.toGrpc(ex));
        } catch (final Throwable t) {
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("UNEXPECTED_ERROR").withCause(t).asRuntimeException());
        }
    }
}