package com.shekhar.JwtWithLogOutAndRefreshToken.repository;

import com.shekhar.JwtWithLogOutAndRefreshToken.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

//    @Query(value = """
//            select t from Token t inner join User u on t.user.id=u,id where u.id = :id and  (t.expired = false or t.revoked = false)
//            """)
//    List<Token> findAllValidTokensByUser(Long id);

    @Query("SELECT t FROM Token t INNER JOIN t.user u WHERE u.id = :id AND (t.expired = false OR t.revoked = false)")
    List<Token> findAllValidTokensByUser(Long id);


    Optional<Token> findByToken(String token);

}
