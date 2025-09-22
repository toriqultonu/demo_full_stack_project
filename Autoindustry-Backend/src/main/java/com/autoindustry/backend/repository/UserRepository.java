package com.autoindustry.backend.repository;

import com.autoindustry.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // For searchable encrypted fields (partial search using hash or like on prefix)
    @Query("SELECT u FROM User u WHERE md5(u.email) LIKE ?1%")  // Example for partial hash search
    List<User> searchByEmailPrefix(String prefixHash);
}