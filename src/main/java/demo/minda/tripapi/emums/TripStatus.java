package demo.minda.tripapi.emums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TripStatus {

    UNDEFINED('U'), START('S'), END('E');
    private Character status;

}
