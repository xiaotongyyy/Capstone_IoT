package edu.capstone.t6.repository;

import edu.capstone.t6.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByOrderByIdAsc();

    Device findTopById(Long id);

}
