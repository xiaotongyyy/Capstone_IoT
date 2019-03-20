package edu.capstone.t6.service;

import edu.capstone.t6.model.Usage;
import edu.capstone.t6.repository.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsageService {

    @Autowired
    private UsageRepository usageRepo;

    public List<Usage> listAll() {
        return usageRepo.findAll();
    }

}
