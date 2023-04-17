package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {

    @NotBlank(message = "Alan boş geçilemez")
    private String cardHolder;
    @NotBlank(message = "Alan boş geçilemez")
    private String modelName;
    @NotBlank(message = "Alan boş geçilemez")
    private String brandName;
    @NotNull
    private String plate;
    @NotNull(message = "Alan boş geçilemez")
    @Min(value = 1999)
    private int modelYear;
    @NotNull(message = "Alan boş geçilemez")
    @Min(0)
    private double dailyPrice;
    @NotNull(message = "Alan boş geçilemez")
    @Min(0)
    private int rentedForDays;
    private LocalDateTime rentedAte;

}
