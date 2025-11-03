package hsb.mkss1.ordersystem.ui.gui;

import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;

public interface ServiceCreatedListener {
    void onItemCreated(ServiceDTO item);
}
