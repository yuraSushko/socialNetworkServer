package org.example.socialnetworkserver.repo;

import org.example.socialnetworkserver.enteties.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

// contains built-in functions for db actions
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.ownerId = :userName")
    List< Post> findPostsByMe(@Param("userName") String userName);


    @Query("""
            select p from Post p
            where p.ownerId in (select f.followsId from Follows f where f.userId = :userId)
            """)
    List<Post> findPostsByFollowing(@Param("userId") Long userId);

}