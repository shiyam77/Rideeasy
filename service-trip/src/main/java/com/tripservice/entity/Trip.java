import lombok.Data;
import com.tripservice.service.state.TripStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "trips")
@Data
Public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String passengerName;
    private String pickupLocation;
    private String destination;

    @Enumerated(EnumType.STRING) // Saves the Enum as a String in the DB (e.g., "REQUESTED")
    private TripStatus status;
}