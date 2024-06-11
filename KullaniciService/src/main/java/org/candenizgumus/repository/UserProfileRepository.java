package org.candenizgumus.repository;

import org.candenizgumus.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>
{
    Optional<UserProfile> findByAuthId(Long authId);
}
