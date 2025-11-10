package hsb.mkss1.ordersystem.usecases.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private int id;
    private List<LineItemDTO> lineItems;
    private LocalDateTime checkoutTimestamp;
    private int lumpSum;

    public OrderDTO(int id, List<LineItemDTO> lineItems, LocalDateTime checkoutTimestamp, int lumpSum) {
        this.id = id;
        this.lineItems = lineItems;
        this.checkoutTimestamp = checkoutTimestamp;
        this.lumpSum = lumpSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LineItemDTO> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDTO> lineItems) {
        this.lineItems = lineItems;
    }

    public int getLumpSum() {
        return lumpSum;
    }

    public void setLumpSum(int lumpSum) {
        this.lumpSum = lumpSum;
    }

    public LocalDateTime getCheckoutTimestamp() {
        return checkoutTimestamp;
    }

    public void setCheckoutTimestamp(LocalDateTime checkoutTimestamp) {
        this.checkoutTimestamp = checkoutTimestamp;
    }
}
