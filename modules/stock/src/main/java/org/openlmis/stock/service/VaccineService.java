<<<<<<< HEAD
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

=======
package org.openlmis.stock.service;

/**
 * Created by Morley on 6/14/2015.
 */

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.StockRepository;
import org.openlmis.stock.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService extends StockService<Vaccine>{

    @Autowired
    private VaccineRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }

>>>>>>> ab13d65384b55a34d30f8383fc3f8589e803e45d
}