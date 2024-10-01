package samuel.gateway_service.shipping_service.shipping;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shipped_orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long shippingId;
    @Column(name = "customer")
    private String customerName;
    private String address;
    private LocalDate deliveryDate;

    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Double quantity;



}
