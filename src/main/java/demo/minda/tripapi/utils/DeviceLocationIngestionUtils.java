package demo.minda.tripapi.utils;

import demo.minda.tripapi.db.dao.DeviceLocationDaoFileImpl;
import demo.minda.tripapi.db.entity.DeviceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceLocationIngestionUtils {

    @Autowired
    DeviceLocationDaoFileImpl deviceLocationDao;

    @Async
    public void ingestData(List<DeviceLocation> deviceLocationList) {
        try{
            deviceLocationDao.saveAll(deviceLocationList);
        }
        catch (Exception e){
            System.out.println("[ERROR] Error while ingesting data. Pushing it to error queue");
            // TODO : Push it to error queue
        }
    }
}
