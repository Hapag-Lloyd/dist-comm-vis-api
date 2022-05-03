package com.hlag.tools.commvis.analyzer.annotation;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.*;

/**
 * Annotated on methods to indicate that an HTTP(S) call is done. Based on this information projects communicating
 * with each other can be visualized.
 *
 * The destination endpoint is matched on projectId, method and path. The names of the path parameters do not have to
 * match.
 *
 * @see VisualizeHttpsCalls
 */
@Repeatable(VisualizeHttpsCalls.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeHttpsCall {
    /**
     * @return the method used to call path, e.g. GET, POST, ...
     */
    String method();

    /**
     * @return the path called, e.g. "/customer/{id}"
     */
    String path();

    /**
     * @return the project called
     */
    String projectId();

    /**
     * @return the name of the project called. Just for a better visibility in the code. The value isn't used.
     */
    String projectName() default "";
}
