package demo.minda.tripapi.db.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceLocation {

    private String deviceId = "";
    private Long timestamp = 0L;
    private Long createdOn = 0L;
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private Integer ignitionstatus = 0;

}
