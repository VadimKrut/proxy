package pathcreator.ru.parse.interceptor;

import io.grpc.*;
import pathcreator.ru.parse.util.TokenUtil;
import pathcreator.ru.parse.util.TokenUtilGrpc;

public final class AuthGrpcInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            final ServerCall<ReqT, RespT> call,
            final Metadata headers,
            final ServerCallHandler<ReqT, RespT> next
    ) {
        final String token = TokenUtil.bearer(headers);
        final Context ctx = Context.current().withValue(TokenUtilGrpc.TOKEN, token);
        return Contexts.interceptCall(ctx, call, headers, next);
    }
}