package hsb.mkss1.ordersystem.persistence;

import hsb.mkss1.ordersystem.model.Order;

import java.util.List;
import java.util.Optional;

//@JpaRepository
public interface OrderRepo {
    void save(Order order);
    void remove(Order order);
    Optional<Order> findById(int id);
    List<Order> findAll();
}
