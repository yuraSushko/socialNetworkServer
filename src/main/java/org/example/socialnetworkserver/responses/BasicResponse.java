package org.example.socialnetworkserver.responses;

import lombok.Data;
@Data
public class BasicResponse <E>  {
    private boolean success;
    private E message;

    public BasicResponse(boolean success, E message) {
        this.success = success;
        this.message = message;
    }

}
