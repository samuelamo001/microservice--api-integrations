package samuel.gateway_service.shipping_service.shipping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingResponse{
    private Long shippingId;
    private String customerName;
    private String address;
    private LocalDate deliveryDate;
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Double quantity;
    private String message;
}

