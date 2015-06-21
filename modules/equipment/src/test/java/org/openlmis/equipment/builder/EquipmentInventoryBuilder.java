package org.openlmis.equipment.builder;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.PropertyLookup;
import org.openlmis.equipment.domain.EquipmentInventory;

public class EquipmentInventoryBuilder {


  public static final Instantiator<EquipmentInventory> defaultEquipmentInventory = new Instantiator<EquipmentInventory>() {

    @Override
    public EquipmentInventory instantiate(PropertyLookup<EquipmentInventory> lookup) {
      EquipmentInventory item = new EquipmentInventory();
      item.setOperationalStatusId(1L);
      item.setIsActive(true);

      item.setHasServiceContract(false);
      item.setModel("234");
      return item;
    }
  };
}
