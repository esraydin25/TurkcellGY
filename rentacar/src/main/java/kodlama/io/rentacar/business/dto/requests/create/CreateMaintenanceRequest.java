package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {
    private int car_id;
    private String information;


}