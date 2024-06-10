package com.candenizgumus.repository;

import com.candenizgumus.entity.Auth;
import com.candenizgumus.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>
{
}
