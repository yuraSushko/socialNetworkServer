package org.example.socialnetworkserver.enteties;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="POST")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;
    private String photo;
    private String caption;
//    private String comment ;//= new ArrayList<String>();
    private int likes;

    public Long getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCaption() {
        return caption;
    }

//    public String getComments() {
//        return comment;
//    }

    public int getLikes() {
        return likes;
    }
}
