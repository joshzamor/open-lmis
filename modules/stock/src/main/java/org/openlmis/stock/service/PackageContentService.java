<<<<<<< HEAD
package org.openlmis.stock.service;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.repository.DiluentRepository;
import org.openlmis.stock.repository.PackageContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class PackageContentService {

    @Autowired
    private PackageContentRepository repository;

    public List<PackageContent> getAll(){
        return repository.getAll();
    }

    public void save(PackageContent packageContent){
        if(packageContent.getId() == null){
            repository.insert(packageContent);
        }else {
            repository.update(packageContent);
        }
    }

    public PackageContent getById(Long id){
        return repository.getById(id);
    }
}
=======
package org.openlmis.stock.service;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.repository.DiluentRepository;
import org.openlmis.stock.repository.PackageContentRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class PackageContentService extends StockService<PackageContent>{

    @Autowired
    private PackageContentRepository repository;

    /*public List<PackageContent> getAll(){
        return repository.getAll();
    }

    public void save(PackageContent packageContent){
        if(packageContent.getId() == null){
            repository.insert(packageContent);
        }else {
            repository.update(packageContent);
        }
    }

    public PackageContent getById(Long id){
        return repository.getById(id);
    }

    public void deleteById(Long id){repository.deleteById(id);}*/

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
>>>>>>> ab13d65384b55a34d30f8383fc3f8589e803e45d
