package com.ranlychan.ktv.exception;

public class QueryRoomTypesFailed extends Exception{
    private static final String message = "QueryRoomTypesFailed";

    public QueryRoomTypesFailed() {
        super(message);
    }

    public QueryRoomTypesFailed(Throwable cause) {
        super(message, cause);
    }

}
