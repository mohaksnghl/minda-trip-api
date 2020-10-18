package demo.minda.tripapi.controller;

import demo.minda.tripapi.BaseResponse;
import demo.minda.tripapi.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

    @Autowired
    TripService tripService;

    @GetMapping("/state")
    public BaseResponse getTripCurrentState(@RequestParam("deviceId") String deviceId){
        return tripService.getTripState(deviceId);
    }

    @GetMapping("/path")
    public BaseResponse getTripPath(@RequestParam("deviceId") String deviceId){
        return tripService.getTripPath(deviceId);
    }

    @GetMapping("/history")
    public BaseResponse getTripHistory(){
        return tripService.getTripHistory();
    }
}
