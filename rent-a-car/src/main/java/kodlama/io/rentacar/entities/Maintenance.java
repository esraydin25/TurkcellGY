package kodlama.io.rentacar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="maintenances")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private boolean isFinished;
    @CreatedDate
    private Date startMaintenance;
    @LastModifiedDate
    private Date endMaintenance;

    @JsonManagedReference
    @ManyToMany( mappedBy = "maintenances",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Car> cars=new HashSet<>();

}
