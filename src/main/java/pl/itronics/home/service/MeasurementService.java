package pl.itronics.home.service;

import org.springframework.stereotype.Service;
import pl.itronics.home.domain.Measurement;

import java.util.List;

public interface MeasurementService {
    public Measurement saveMeasurement(Measurement measurement);
    public List<Measurement> findMeasurementById(Integer id);
    public List<Measurement> findAll();
}
