package kodlama.io.rentacar.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {
    private int car_id;
    private String type;
    private boolean isFinished=false;
    private Date startMaintenance;

    private Date endMaintenance;



}
