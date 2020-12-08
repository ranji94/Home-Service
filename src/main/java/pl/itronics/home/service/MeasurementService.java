package pl.itronics.home.service;

import pl.itronics.home.domain.Measurement;
import pl.itronics.home.enums.MeasurementStatus;

import java.util.List;

public interface MeasurementService {
    public Measurement saveMeasurement(Measurement measurement);
    public List<Measurement> findMeasurementById(Integer id);
    public List<Measurement> findAll();
    public Measurement findLast();
    public String updateStatus(Integer id, MeasurementStatus status);
}
