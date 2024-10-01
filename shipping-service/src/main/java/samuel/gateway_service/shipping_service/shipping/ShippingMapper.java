package samuel.gateway_service.shipping_service.shipping;

import jakarta.persistence.Column;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShippingMapper {

    public ShippingResponse convertToShippingResponse(Shipping shipping) {

        return ShippingResponse
                .builder()
                .shippingId(shipping.getShippingId())
                .customerName(shipping.getCustomerName())
                .address(shipping.getAddress())
                .deliveryDate(shipping.getDeliveryDate())
                .productId(shipping.getProductId())
                .productName(shipping.getProductName())
                .productDescription(shipping.getProductDescription())
                .productPrice(shipping.getProductPrice())
                .quantity(shipping.getQuantity())
                .build();
    }
}
