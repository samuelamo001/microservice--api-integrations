package samuel.gateway_service.shipping_service.shipping;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
