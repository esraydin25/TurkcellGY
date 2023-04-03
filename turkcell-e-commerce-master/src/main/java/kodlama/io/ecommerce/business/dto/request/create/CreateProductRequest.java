package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kodlama.io.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;

    private Status status;
    private int category_id;

}
