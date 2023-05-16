package kodlama.io.ecommerce.business.dto.response.create.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private int categoryId;
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;


}
