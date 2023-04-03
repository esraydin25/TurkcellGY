package kodlama.io.rentacar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    private State state;
    private double dailyPrice;

    @ManyToOne
    @JoinColumn(name="model_id")
    private Model model;


    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "maintenance_cars",
            joinColumns = @JoinColumn(name = "maintenance_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Set<Maintenance> maintenances;


}
