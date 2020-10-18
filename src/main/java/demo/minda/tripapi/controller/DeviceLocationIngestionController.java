package demo.minda.tripapi.controller;

import demo.minda.tripapi.BaseResponse;
import demo.minda.tripapi.db.entity.DeviceLocation;
import demo.minda.tripapi.service.DeviceLocationIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class DeviceLocationIngestionController {

    @Autowired
    DeviceLocationIngestionService deviceLocationIngestionService;

    @PostMapping("device/location")
    public BaseResponse ingestDeviceLocation(@RequestBody List<DeviceLocation> deviceLocationList){
        try{
            return deviceLocationIngestionService.ingestDeviceLocation(deviceLocationList);
        }
        catch (Exception e){
            return BaseResponse.builder().errorState("1").message("Error! Please try again").build();
        }
    }

}
