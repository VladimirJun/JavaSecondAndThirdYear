package org.example.utilities;

public class HTTPStatus {

    public static final int OK = 200;          // GET
    public static final int CREATED = 201;     // POST
    public static final int NO_CONTENT = 204;  // PATCH, DELETE

    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int FAILED_DEPENDENCY = 424;

    public static final int INTERNAL_SERVER_ERROR = 500;
}