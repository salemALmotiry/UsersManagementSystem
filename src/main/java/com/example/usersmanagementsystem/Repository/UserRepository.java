package com.example.usersmanagementsystem.Repository;

import com.example.usersmanagementsystem.Model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserById(Integer id);


    //https://stackoverflow.com/questions/10377781/return-boolean-value-on-sql-select-statement#:~:text=What%20you%20have,AS%20BIT)%20END
    @Query("SELECT CASE WHEN EXISTS (select u from User u where u.userName = ?1 and u.password = ?2) then cast(1 as boolean ) else cast(0 as boolean) end")
    Boolean loginUser(String userName, String password);

    List<User> findUsersByRole(String role);



    @Query("select u from User u where u.age between ?1 AND ?2")

    List<User> findUsersByAge(Integer minAge, Integer maxAge);
}
