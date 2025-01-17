package com.kevdn.airbnb.domain.user.repository;


import com.kevdn.airbnb.domain.user.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
