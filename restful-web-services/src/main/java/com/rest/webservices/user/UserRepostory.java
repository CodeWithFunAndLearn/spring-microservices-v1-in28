package com.rest.webservices.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostory extends JpaRepository<User,Integer> {
}
