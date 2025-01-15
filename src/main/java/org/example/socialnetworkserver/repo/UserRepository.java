package org.example.socialnetworkserver.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




import org.example.socialnetworkserver.enteties.User;

@Repository

// contains built-in functions for db actions
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByUserName(String username);

    @Query("select u from User u where u.id = :id")
    User findByUserId(@Param("id") String id);


    @Query("select u from User u where u.userName = :userName")
    User findByUserName(@Param("userName") String userName);


    @Query("select u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email); //findByEmail(String email);


}