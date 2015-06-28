package org.openlmis.stock.service;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VarDocument;
import org.openlmis.stock.repository.StockRepository;
import org.openlmis.stock.repository.VaccineRepository;
import org.openlmis.stock.repository.VarDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Morley on 6/28/2015.
 */
@Service
public class VarDocumentService extends StockService<VarDocument>{

        @Autowired
        private VarDocumentRepository repository;

        @Override
        public StockRepository getRepository() {
            return repository;
        }
}
