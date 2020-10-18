package demo.minda.tripapi.service;

import demo.minda.tripapi.BaseResponse;
import demo.minda.tripapi.db.dao.DeviceLocationDaoFileImpl;
import demo.minda.tripapi.db.entity.DeviceLocation;
import demo.minda.tripapi.dto.Trip;
import demo.minda.tripapi.dto.TripHistory;
import demo.minda.tripapi.emums.TripStatus;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripService {

    // TODO : Move it to config
    public final int IGNITION_STATUS_THRESHOLD = 3;

    @Autowired
    DeviceLocationDaoFileImpl deviceLocationDao;

    public BaseResponse getTripState(String deviceId) {
        List<DeviceLocation> deviceLocationList = deviceLocationDao.findByDeviceId(deviceId);

        if(CollectionUtils.isEmpty(deviceLocationList)){
            return BaseResponse.builder().message("Unknown Trip").errorState("1").build();
        }

        deviceLocationList.sort((o1, o2) -> (int) (o1.getTimestamp() - o2.getTimestamp()));
        int ignitionStatusStopCount = 0;
        int ignitionStatusStartCount = 0;
        TripStatus tripStatus = TripStatus.UNDEFINED;

        for (DeviceLocation deviceLocation : deviceLocationList){

            if(deviceLocation.getIgnitionstatus() == null){
                continue;
            }

            if(deviceLocation.getIgnitionstatus() == 1){
                ignitionStatusStartCount++;
                ignitionStatusStopCount = 0;
                if(ignitionStatusStartCount == IGNITION_STATUS_THRESHOLD){
                    tripStatus = TripStatus.START;
                }
            }
            else {
                ignitionStatusStopCount++;
                ignitionStatusStartCount = 0;
                if(ignitionStatusStopCount == IGNITION_STATUS_THRESHOLD){
                    tripStatus = TripStatus.END;
                }
            }
        }
        return Trip.builder().state(tripStatus.getStatus()).deviceId(deviceId).message("Success").errorState("0").build();
    }

    public BaseResponse getTripPath(String deviceId) {
        List<DeviceLocation> deviceLocationList = deviceLocationDao.findByDeviceId(deviceId);

        if(CollectionUtils.isEmpty(deviceLocationList)){
            return BaseResponse.builder().message("Unknown Trip").errorState("1").build();
        }

        deviceLocationList.sort((o1, o2) -> (int) (o1.getTimestamp() - o2.getTimestamp()));
        List<DeviceLocation> path = new ArrayList<>();
        for (DeviceLocation deviceLocation : deviceLocationList){
            path.add(DeviceLocation.builder()
                    .latitude(deviceLocation.getLatitude())
                    .longitude(deviceLocation.getLongitude())
                    .timestamp(deviceLocation.getTimestamp()).build());
        }

        return Trip.builder().deviceId(deviceId).path(path).errorState("0").message("Success").build();
    }

    public BaseResponse getTripHistory() {
        List<DeviceLocation> deviceLocationList = deviceLocationDao.findAll();

        if(CollectionUtils.isEmpty(deviceLocationList)){
            return BaseResponse.builder().errorState("1").message("Error fetching data").build();
        }
        deviceLocationList.sort((o1, o2) -> (int) (o1.getTimestamp() - o2.getTimestamp()));

        int tripStarted = 0;
        int tripEnded = 0;

        Map<String, List<DeviceLocation>> deviceTrips = deviceLocationList.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(DeviceLocation::getDeviceId));

        for (Map.Entry<String, List<DeviceLocation>> deviceTrip : deviceTrips.entrySet()){
            int tripStartIgnitionCount = 0;
            int tripEndIgnitionCount = 0;

            for (DeviceLocation deviceLocation : deviceTrip.getValue()){

                if(deviceLocation.getIgnitionstatus() == null){
                    continue;
                }

                if(deviceLocation.getIgnitionstatus() == 1){
                    tripStartIgnitionCount++;
                    tripEndIgnitionCount = 0;
                    if(tripStartIgnitionCount == IGNITION_STATUS_THRESHOLD){
                        tripStarted++;
                    }
                }
                else {
                    tripEndIgnitionCount++;
                    tripStartIgnitionCount = 0;
                    if(tripEndIgnitionCount == IGNITION_STATUS_THRESHOLD){
                        tripEnded++;
                    }
                }
            }
        }


        return TripHistory.builder().tripCompleted(tripEnded).tripStarted(tripStarted)
                .errorState("0").message("Success").build();
    }
}
