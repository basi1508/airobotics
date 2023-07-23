package com.interview.airobotics.repo;

import com.interview.airobotics.data.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Iterable<Guest>getGuestByFirstName(String name);
}