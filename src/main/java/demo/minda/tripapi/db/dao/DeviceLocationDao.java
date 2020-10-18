package demo.minda.tripapi.db.dao;

import demo.minda.tripapi.db.entity.DeviceLocation;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface DeviceLocationDao {
    void saveAll(List<DeviceLocation> deviceLocationList) throws IOException;

    List<DeviceLocation> findByDeviceId(String deviceId);

    List<DeviceLocation> findAll();
}
