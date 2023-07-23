package com.interview.airobotics.repo;

import com.interview.airobotics.data.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUserName(String username);
}
