package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.StockModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/18/2015.
 */
@Repository
public interface StockMapper<T extends StockModel> {
    @SelectProvider(type=ModelProviders.class, method="selectAll")
    List<T> getAll(@Param("object") T stockModel);

    Integer insert(T model);

    void update(T model);

    @SelectProvider(type=ModelProviders.class, method="selectById")
    T getById(@Param("id") Long id,T stockModel);

    @DeleteProvider(type=ModelProviders.class, method="deleteById")
    void deleteById(@Param("id") Long id,T stockModel);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    List<T> filter(@Param("filter") String filter,T stockModel);
}
