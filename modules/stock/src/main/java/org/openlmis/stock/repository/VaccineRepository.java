package org.openlmis.stock.repository;

/**
 * Created by Morley on 6/14/2015.
 */
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.mapper.VaccineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VaccineRepository {

    @Autowired
    private VaccineMapper mapper;

    public void update(Vaccine vaccine){
        mapper.update(vaccine);
    }

    public void insert(Vaccine vaccine){
        mapper.insert(vaccine);
    }

    public List<Vaccine> getAll(){
        return mapper.getAll();
    }

    public Vaccine getById(Long id) {
        return mapper.getById(id);
    }
}