package com.fc.ws.squad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {

}
