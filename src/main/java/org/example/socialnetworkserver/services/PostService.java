package org.example.socialnetworkserver.services;

import org.example.socialnetworkserver.enteties.Post;
import org.example.socialnetworkserver.enteties.User;
import org.example.socialnetworkserver.repo.PostRepository;
import org.example.socialnetworkserver.repo.UserRepository;
import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public User getUserFromToken(String token){
            System.out.println(JwtUtil.validateToken(token));//TODO check matches usestate userName
            String userName = JwtUtil.getTokensValue(token);
            return userRepository.findByUserName(userName);
    }

    public  BasicResponse getMyFollowingsPosts(String tokenCookie) {
        try {
            User user = getUserFromToken(tokenCookie);
            ArrayList<Post> posts = (ArrayList<Post>) postRepository.findPostsByFollowing(user.getId());
            return new BasicResponse<>(true, posts);
        }catch (Exception e){
            return new BasicResponse<>(false,"bad token");
        }
    }
    public boolean getMyPosts(String tokenCookie) {
        return  true;
    }
    public boolean checkStr(String str) {
        return str != null && !str.isEmpty();
    }

    public BasicResponse<String> addPost(Post post, String token) {
        User user;
        try {
            user = getUserFromToken(token);
            post.setOwnerId((user.getId()));
            if (checkStr(post.getPhoto()) && checkStr(post.getCaption())){
                postRepository.save(post);
                return new BasicResponse<>(true,"posted by "+user.getUserName());
            }
            return new BasicResponse<>(false ,"bad post no photo or caption");
        } catch (Exception e) {
            return new BasicResponse<>(false,"bad token");
        }

    }

}
