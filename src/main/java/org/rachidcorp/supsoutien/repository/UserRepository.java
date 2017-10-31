package org.rachidcorp.supsoutien.repository;


import java.util.List;
import org.rachidcorp.supsoutien.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdBooster(Long idBooster); 
    
    @Query("select u from User u where u.idBooster like :x")
    public Page<User> rechercherUserByIdBooster(@Param("x") Long idBooster, Pageable pageable);
    
    @Query("select u from User u where u.roleLib.id = 4")
    public List<User> getAllStaff();
}
