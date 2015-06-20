package org.openlmis.stock.service;

/**
 * Created by Morley on 6/14/2015.
 */

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository repository;

    public List<Vaccine> getAll(){
        return repository.getAll();
    }

    public void save(Vaccine vaccine){
        if(vaccine.getId() == null){
            repository.insert(vaccine);
        }else {
            repository.update(vaccine);
        }
    }

    public Vaccine getById(Long id){
        return repository.getById(id);
    }

}