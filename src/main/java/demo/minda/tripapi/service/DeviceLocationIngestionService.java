package demo.minda.tripapi.service;

import demo.minda.tripapi.BaseResponse;
import demo.minda.tripapi.db.entity.DeviceLocation;
import demo.minda.tripapi.utils.DeviceLocationIngestionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceLocationIngestionService {

    @Autowired
    DeviceLocationIngestionUtils deviceLocationIngestionUtils;

    public BaseResponse ingestDeviceLocation(List<DeviceLocation> deviceLocationList) {

        // Should be done by using a MQ in between
        deviceLocationIngestionUtils.ingestData(deviceLocationList);
        return BaseResponse.builder().errorState("0").message("Success").build();

    }
}
