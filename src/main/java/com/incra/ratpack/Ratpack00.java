package com.incra.ratpack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                        .serverConfig(c -> c.baseDir(BaseDir.find()))
                        .handlers(chain -> chain
                                        .path("foo", ctx -> ctx.render("from the foo handler")) // Map to /foo
                                        .path("bar", ctx -> ctx.render("from the bar handler")) // Map to /bar
                                        .all(ctx -> ctx.render("root handler!"))
                        )
        );
    }
}