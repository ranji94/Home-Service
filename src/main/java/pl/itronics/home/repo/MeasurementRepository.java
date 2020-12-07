package pl.itronics.home.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.itronics.home.domain.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MeasurementRepository {
    @Autowired
    JdbcTemplate template;

    final String INSERT_READING_SQL = "INSERT INTO" +
            " measurements(" +
            " date, energy_reading, kitchen_hot_water_reading, kitchen_cold_water_reading, bathroom_hot_water_reading, bathroom_cold_water_reading)" +
            " VALUES (?, ?, ?, ?, ?, ?);";

    final String SELECT_ALL = "SELECT " +
            "id, " +
            "date, " +
            "energy_reading, " +
            "kitchen_hot_water_reading, " +
            "kitchen_cold_water_reading, " +
            "bathroom_hot_water_reading, " +
            "bathroom_cold_water_reading " +
            "FROM measurements";

    final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?;";

    public long insert(final Measurement measurement) {
        SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_READING_SQL);
            ps.setString(1, formatter.format(date));
            ps.setString(2, measurement.getEnergyReading().getEnergyReading().getValue());
            ps.setString(3, measurement.getKitchenReadings().getHotWater().getValue());
            ps.setString(4, measurement.getKitchenReadings().getColdWater().getValue());
            ps.setString(5, measurement.getBathroomReadings().getHotWater().getValue());
            ps.setString(6, measurement.getBathroomReadings().getColdWater().getValue());
            return ps;
        }, keyHolder);

        try {
            return (long) keyHolder.getKey();
        }
        catch (NullPointerException e) {
            return 0L;
        }
    }

    public List<Measurement> findMeasurementById(final int identifier) {
        try {
            Connection connection = template.getDataSource().getConnection();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(SELECT_BY_ID.replace("?", String.valueOf(identifier)));
            List<Measurement> measurements = mapRowsToMeasurements(rs);
            st.close();

            return measurements;
        }
        catch(SQLException e) {
            return null;
        }
    }

    public List<Measurement> findAll() {
        try {
            Connection connection = template.getDataSource().getConnection();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(SELECT_ALL);
            List<Measurement> measurements = mapRowsToMeasurements(rs);
            st.close();

            return measurements;
        }
        catch(SQLException e) {
            return null;
        }
    }

    private List<Measurement> mapRowsToMeasurements(ResultSet rs) throws SQLException{
        List<Measurement> measurements = new ArrayList<>();

        while (rs.next())
        {
            Measurement measurement = new Measurement();
            measurement.setId(rs.getInt("id"));
            measurement.setDate(rs.getString("date"));

            SingleEnergyReading singleEnergyReading = new SingleEnergyReading();
            singleEnergyReading.setEnergyReading(new EnergyMeter(rs.getString("energy_reading")));

            SingleWaterReading kitchenReadings = new SingleWaterReading();
            kitchenReadings.setHotWater(new WaterMeter(rs.getString("kitchen_hot_water_reading")));
            kitchenReadings.setColdWater(new WaterMeter(rs.getString("kitchen_cold_water_reading")));

            SingleWaterReading bathroomReadings = new SingleWaterReading();
            bathroomReadings.setHotWater(new WaterMeter(rs.getString("bathroom_hot_water_reading")));
            bathroomReadings.setColdWater(new WaterMeter(rs.getString("bathroom_cold_water_reading")));

            measurement.setEnergyReading(singleEnergyReading);
            measurement.setKitchenReadings(kitchenReadings);
            measurement.setBathroomReadings(bathroomReadings);

            measurements.add(measurement);
        }

        return measurements;
    }
}
