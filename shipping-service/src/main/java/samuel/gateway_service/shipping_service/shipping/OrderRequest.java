package samuel.gateway_service.shipping_service.shipping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record OrderRequest(
         Long orderId,
         Long productId,
         String name,
         String description,
         double quantity,
         double price
) {}
