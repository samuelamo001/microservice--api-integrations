package samuel.gateway_service.shipping_service.shipping;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShippingController {
    private final ShippingService shippingService;


    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping
    public ResponseEntity<ShippingResponse> createShipping(
            @RequestParam Long orderId,
            @RequestParam String customerName,
            @RequestParam String address,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDate
    ) {
        ShippingResponse response = shippingService.createShipping(orderId,customerName, address, deliveryDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ShippingResponse>> getAllShipping() {
        List<ShippingResponse> shipping = shippingService.getAllShipping();
        return ResponseEntity.ok(shipping);
    }
}

