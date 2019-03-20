package edu.capstone.t6.repository;

import edu.capstone.t6.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Modifying
    @Query(value = "UPDATE log SET run_stat = ?2 WHERE device_id = ?1", nativeQuery = true)
    int updateRunStatById(Long deviceId, Boolean runStat);

    Log findTopByRunStatAndDevice_Id(Boolean runStat,Long deviceId);

}
