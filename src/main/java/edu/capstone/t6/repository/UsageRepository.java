package edu.capstone.t6.repository;

import edu.capstone.t6.model.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface UsageRepository extends JpaRepository<Usage, Date> {

    @Modifying
    @Query(value = "UPDATE usage SET power = power + ?1, water = water + ?2 WHERE date = ?3", nativeQuery = true)
    int updateUsageByDate(Double power, Double water, Date date);

}
