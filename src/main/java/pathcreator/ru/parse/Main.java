package pathcreator.ru.parse;

import io.helidon.config.Config;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.grpc.GrpcReflectionFeature;
import io.helidon.webserver.grpc.GrpcRouting;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.http2.Http2Config;
import pathcreator.ru.parse.controller.grpc.GreetingGrpcController;
import pathcreator.ru.parse.controller.rest.GreetingController;
import pathcreator.ru.parse.exception.ApiException;
import pathcreator.ru.parse.handler.AnyErrorHandler;
import pathcreator.ru.parse.handler.ApiErrorHandler;
import pathcreator.ru.parse.interceptor.AuthGrpcInterceptor;
import pathcreator.ru.parse.util.EnvPlaceholders;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        final Config cfg = Config.create();

        final Http2Config http2 = Http2Config.builder().build();

        final String rawPort = cfg.get("server.port").asString().orElse("${SERVER_PORT:1439}");
        final int port = EnvPlaceholders.resolveInt(rawPort);

        final HttpRouting.Builder route = HttpRouting.builder()
                .error(ApiException.class, new ApiErrorHandler())
                .error(Throwable.class, new AnyErrorHandler())
                .register("/api", new GreetingController());

        final GrpcRouting.Builder grpc = GrpcRouting.builder()
                .intercept(new AuthGrpcInterceptor())
                .service(new GreetingGrpcController());

        final WebServer server = WebServer.builder()
                .port(port)
                .addProtocol(http2)
                .addRouting(route)
                .addRouting(grpc)
                .addFeature(GrpcReflectionFeature.create())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}