package kodlama.io.ecommerce.business.dto.request.update;

import kodlama.io.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private int category_id;
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;

    private Status status;

}
