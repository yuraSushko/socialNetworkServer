package org.example.socialnetworkserver.services;
import org.example.socialnetworkserver.enteties.User;
import org.example.socialnetworkserver.repo.UserRepository;
import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public boolean checkStr(String str) {
        return str != null && !str.isEmpty();
    }

    public boolean checkUserName(String userName) {
        return (checkStr(userName) && userName.length() >= 3 && userName.length() <= 20);

    }

    public boolean checkMatch(String regex, String strToCheck) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strToCheck);
        return matcher.matches();
    }

    public boolean checkEmailIsValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return checkMatch(emailRegex, email);
    }




    public boolean checkPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/\\?]).*$";

        if (checkStr(password) && (password.length() >= 6 && password.length() <= 8)) {
            return checkMatch(regex, password);
        }
        return false;
    }

    public boolean checkPhoneNumber(String password) {
        String regex = "^0\\d{9,14}$";
        return checkMatch(regex, password);

    }

    public BasicResponse signIn(String username, String password) {
        User user = userRepository.findByUserName(username);
        if(user!=null && user.getPassword().equals(password)){
            String token = JwtUtil.generateToken(user.getUserName());
            return new BasicResponse(true , token);
        }
        return new BasicResponse(false,"incorrect userName or password");
    }



    public BasicResponse createUser(User user) {
        User newUser = userRepository.findByUserName(user.getUserName());
        if(newUser!=null){
           return new BasicResponse(false , "userName already exist please input another name");
        }

        User newEmail = userRepository.findUserByEmail(user.getEmail());
        if(newEmail!=null){
            return new BasicResponse(false , "email already exist please input another email");
        }
        BasicResponse checkUserData=checkSentUserSetupDataValid(user);
        if (checkUserData==null){
            userRepository.save(user);
            return new BasicResponse(true , "succseful user create");

        }else{return checkUserData;}

    }

    public BasicResponse checkSentUserSetupDataValid(User user){
        if(!checkUserName(user.getUserName())) {
         return new BasicResponse(false, "invalid userName");
        }
        if(!checkPassword(user.getPassword())){
            return new BasicResponse(false, "invalid password");
        }
        if(!checkEmailIsValid(user.getEmail()) ){
            return new BasicResponse(false, "invalid email");
        }
        if(!checkPhoneNumber(user.getPhoneNumber())){
            return new BasicResponse(false, "invalid phoneNumber");

        }
        return null;

    }











}