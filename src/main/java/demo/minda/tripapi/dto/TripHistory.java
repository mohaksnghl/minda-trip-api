package demo.minda.tripapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.minda.tripapi.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TripHistory extends BaseResponse {
    private int tripStarted;
    private int tripCompleted;
}
