package hsb.mkss1.order_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private OrderStatusEnum status = OrderStatusEnum.EMPTY;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="order", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();
    private LocalDateTime checkoutTimestamp;
    private String customerName;


}
