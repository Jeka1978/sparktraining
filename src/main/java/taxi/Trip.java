package taxi;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Evegeny on 28/05/2017.
 */
@Data
@Builder
public class Trip {
    private String id;
    private int distance;
    private String city;
}
