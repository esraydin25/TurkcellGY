package kodlama.io.rentacar.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceResponse {
    private int id;
    private int car_id;
    private String type;
    private boolean isFinished;
    private Date startMaintenance;
    private Date endMaintenance;
}
