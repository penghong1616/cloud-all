package com.ph.repository;

import com.ph.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/21/ 11:22
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);
}
