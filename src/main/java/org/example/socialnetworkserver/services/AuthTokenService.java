package org.example.socialnetworkserver.services;

import lombok.Setter;
import org.example.socialnetworkserver.enteties.User;
import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    public  BasicResponse<String> validateTokenToUser(String token ){
        boolean valid = JwtUtil.validateToken(token);//TODO check matches usestate userName
        String message = "bad token";
        if (valid){
            message = JwtUtil.getTokensValue(token);
        }
        return new BasicResponse<>(valid, message);
    }

}
