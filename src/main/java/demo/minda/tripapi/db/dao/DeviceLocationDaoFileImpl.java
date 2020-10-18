package demo.minda.tripapi.db.dao;

import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import demo.minda.tripapi.db.entity.DeviceLocation;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceLocationDaoFileImpl implements DeviceLocationDao {
    private static final String CSV_SEPARATOR = ",";

    @Override
    public void saveAll(List<DeviceLocation> deviceLocationList){
        try {
            FileWriter writer = new
                    FileWriter("device_location.csv", true);
            ColumnPositionMappingStrategy mappingStrategy =
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(DeviceLocation.class);

            String[] columns = new String[]
                    {"deviceId", "timestamp", "latitude", "longitude", "ignitionstatus"};
            mappingStrategy.setColumnMapping(columns);

            // Createing StatefulBeanToCsv object
            StatefulBeanToCsvBuilder<DeviceLocation> builder =
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).build();

            // Write list to StatefulBeanToCsv object
            beanWriter.write(deviceLocationList);

            // closing the writer object
            writer.close();
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            System.out.println("Error in ingestion of device location data" + e);
        }

    }

    @Override
    public List<DeviceLocation> findByDeviceId(String deviceId) {
        return findAll().stream().filter(deviceLocation -> deviceLocation.getDeviceId().equals(deviceId)).collect(Collectors.toList());
    }

    @Override
    public List<DeviceLocation> findAll() {
        HeaderColumnNameTranslateMappingStrategy<DeviceLocation> strategy =
                new HeaderColumnNameTranslateMappingStrategy<DeviceLocation>();
        strategy.setType(DeviceLocation.class);

        Map<String, String> mapping = new HashMap<>();
        mapping.put("deviceId", "deviceId");
        mapping.put("timestamp", "timestamp");
        mapping.put("latitude", "latitude");
        mapping.put("longitude", "longitude");
        mapping.put("ignitionstatus", "ignitionstatus");

        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader
                    ("device_location.csv"));

            CsvToBean csvToBean = new CsvToBean();
            List<DeviceLocation> list = csvToBean.parse(strategy, csvReader);
            return list;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
