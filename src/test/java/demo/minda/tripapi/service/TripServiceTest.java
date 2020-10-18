package demo.minda.tripapi.service;

import demo.minda.tripapi.BaseResponse;
import demo.minda.tripapi.db.dao.DeviceLocationDaoFileImpl;
import demo.minda.tripapi.db.entity.DeviceLocation;
import demo.minda.tripapi.dto.Trip;
import demo.minda.tripapi.dto.TripHistory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TripServiceTest {

    @Mock
    DeviceLocationDaoFileImpl deviceLocationDao;

    @InjectMocks
    TripService tripService;


    @Test
    void getTripHistoryTest1() {
        Mockito.when(deviceLocationDao.findAll()).thenReturn(getTest1Data());

        TripHistory response = (TripHistory) tripService.getTripHistory();
        assertEquals("0", response.getErrorState());
        assertEquals(1, response.getTripCompleted());
        assertEquals(2, response.getTripStarted());
    }

    @Test
    void getTripHistoryTest2() {
        Mockito.when(deviceLocationDao.findAll()).thenReturn(getTest2Data());

        TripHistory response = (TripHistory) tripService.getTripHistory();
        assertEquals("0", response.getErrorState());
        assertEquals(2, response.getTripCompleted());
        assertEquals(2, response.getTripStarted());
    }

    @Test
    void getTripHistoryTestNoData() {
        Mockito.when(deviceLocationDao.findAll()).thenReturn(new ArrayList<>());

        BaseResponse response = tripService.getTripHistory();
        assertEquals("1", response.getErrorState());
    }

    @Test
    void getTripStateTest1(){
        Mockito.when(deviceLocationDao.findByDeviceId("123")).thenReturn(getTest1Data().stream().filter(d->d.getDeviceId().equals("123")).collect(Collectors.toList()));
        Mockito.when(deviceLocationDao.findByDeviceId("124")).thenReturn(getTest1Data().stream().filter(d->d.getDeviceId().equals("124")).collect(Collectors.toList()));

        Trip response1 = (Trip) tripService.getTripState("123");
        Trip response2 = (Trip) tripService.getTripState("124");

        assertEquals("0", response1.getErrorState());
        assertEquals("0", response2.getErrorState());

        assertEquals('E', response1.getState());
        assertEquals('S', response2.getState());
    }

    @Test
    void getTripStateTest2(){
        Mockito.when(deviceLocationDao.findByDeviceId("123")).thenReturn(getTest2Data().stream().filter(d->d.getDeviceId().equals("123")).collect(Collectors.toList()));
        Mockito.when(deviceLocationDao.findByDeviceId("124")).thenReturn(getTest2Data().stream().filter(d->d.getDeviceId().equals("124")).collect(Collectors.toList()));

        Trip response1 = (Trip) tripService.getTripState("123");
        Trip response2 = (Trip) tripService.getTripState("124");

        assertEquals("0", response1.getErrorState());
        assertEquals("0", response2.getErrorState());

        assertEquals('E', response1.getState());
        assertEquals('E', response2.getState());
    }

    private List<DeviceLocation> getTest2Data() {
        List<DeviceLocation> deviceLocationListTest2 = new ArrayList<>();
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658000L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658000L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658050L).latitude(14.345).longitude(34.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658100L).latitude(12.347).longitude(32.237).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658100L).latitude(14.347).longitude(34.237).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658200L).latitude(12.347).longitude(32.239).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658100L).latitude(12.145).longitude(31.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658100L).latitude(12.349).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658200L).latitude(12.175).longitude(31.244).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658320L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658320L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658200L).latitude(12.349).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658201L).latitude(12.349).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658320L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658401L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658420L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658201L).latitude(12.349).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658240L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest2.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658440L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        return deviceLocationListTest2;

    }

    public List<DeviceLocation> getTest1Data(){
        List<DeviceLocation> deviceLocationListTest1 = new ArrayList<>();
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658000L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658000L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658050L).latitude(14.345).longitude(34.234).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658100L).latitude(12.347).longitude(32.237).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658100L).latitude(14.347).longitude(34.237).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658200L).latitude(12.347).longitude(32.239).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658200L).latitude(12.145).longitude(31.234).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658200L).latitude(12.349).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658300L).latitude(12.175).longitude(31.244).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658320L).latitude(12.345).longitude(12.345).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658320L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658200L).latitude(12.349).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("124").timestamp(1600435658201L).latitude(12.349).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658320L).latitude(12.345).longitude(32.234).ignitionstatus(1).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658401L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658420L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        deviceLocationListTest1.add(DeviceLocation.builder().deviceId("123").timestamp(1600435658420L).latitude(12.345).longitude(32.234).ignitionstatus(0).build());
        return deviceLocationListTest1;
    }
}