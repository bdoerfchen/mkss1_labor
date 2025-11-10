package hsb.mkss1.ordersystem.entities;

import java.util.List;
import java.util.Optional;

public interface OrderRepo {
    void save(OrderEntity orderEntity);
    void remove(OrderEntity orderEntity);
    Optional<OrderEntity> findById(int id);
    List<OrderEntity> findAll();
}
