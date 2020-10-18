package demo.minda.tripapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.minda.tripapi.BaseResponse;
import demo.minda.tripapi.db.entity.DeviceLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trip extends BaseResponse {
    private String tripId;
    private String deviceId;
    private Character state;
    private Long startTime;
    private Long endTime;
    private List<DeviceLocation> path;
}
