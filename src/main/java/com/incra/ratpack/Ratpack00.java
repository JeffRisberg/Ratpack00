package com.incra.ratpack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

/**
 * @author Jeff Risberg
 * @since 12/14/16
 */
public class Ratpack00 {

  private static final Logger LOGGER = LoggerFactory.getLogger(Ratpack00.class);

  public static void main(String[] args) throws Exception {

    RatpackServer.start(s -> s
        .serverConfig(config -> config.baseDir(BaseDir.find()))

        // This is a simple Ratpack-based registration - however, you can't use it in
        // combination with the Guice-based registration shown below.
        //.registryOf(registry -> registry.add(new HelloHandler()))

        // This is a Guice-based registration
        .registry(Guice.registry(r -> {
              r.bindInstance(HelloHandler.class, new HelloHandler());
              r.bindInstance(UserService.class, new DefaultUserService());
            })
        )

        .handlers(chain -> chain
            .path("foo", ctx -> ctx.render("from the foo handler")) // Map to /foo
            .path("bar", ctx -> ctx.render("from the bar handler")) // Map to /bar
            .path("hello", ctx -> ctx.get(HelloHandler.class).handle(ctx))
            .path("users", ctx -> ctx.render(ctx.get(UserService.class).getUsers().toString()))
            .all(ctx -> ctx.render("root handler!"))
        )
    );
  }
}
