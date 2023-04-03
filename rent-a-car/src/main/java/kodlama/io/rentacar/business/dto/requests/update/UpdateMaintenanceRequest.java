package kodlama.io.rentacar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceRequest {
    private String type;
    private boolean isFinished;
    private Date startMaintenance;
    private Date endMaintenance;
    private int car_id;
}
