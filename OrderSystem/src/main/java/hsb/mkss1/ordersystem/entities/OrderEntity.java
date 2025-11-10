package hsb.mkss1.ordersystem.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderEntity {

    private Integer id;
    private final List<LineItemEntity> lineItemEntities = new ArrayList<>();
    private LocalDateTime checkoutTimestamp;
    private int lumpSum = 0;

    public void addItem(LineItemEntity lineItemEntity) {
        if (lineItemEntity != null) {
            lineItemEntities.add(lineItemEntity);
            lumpSum += lineItemEntity.getPrice();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LineItemEntity> getItems() {
        return Collections.unmodifiableList(lineItemEntities);
    }

    public void setCheckoutTimestamp(LocalDateTime checkoutTimestamp) {
        this.checkoutTimestamp = checkoutTimestamp;
    }

    public LocalDateTime getCheckoutTimestamp() {
        return checkoutTimestamp;
    }

    public int getLumpSum() {
        return this.lumpSum;
    }

    public OrderEntity createCopy() {
        OrderEntity copy = new OrderEntity();
        lineItemEntities.stream().map(LineItemEntity::createCopy).forEach(copy::addItem);
        copy.setId(id);
        copy.setCheckoutTimestamp(checkoutTimestamp);

        return copy;
    }
}
