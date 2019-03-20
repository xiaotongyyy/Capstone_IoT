package edu.capstone.t6.service;

import edu.capstone.t6.model.Log;
import edu.capstone.t6.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepo;

    public List<Log> listAll() {
        return logRepo.findAll();
    }

    public Long deleteAll() {
        logRepo.deleteAllInBatch();
        return logRepo.count();
    }

}
