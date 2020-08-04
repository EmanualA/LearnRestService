package com.LearnSpringBoot.RestServices.LearnRestService.Repository;

import com.LearnSpringBoot.RestServices.LearnRestService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
