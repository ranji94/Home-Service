package pl.itronics.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.itronics.home.domain.Measurement;
import pl.itronics.home.repo.MeasurementRepository;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public Measurement saveMeasurement(Measurement measurement) {
        measurementRepository.insert(measurement);
        return measurement;
    }

    @Override
    public List<Measurement> findMeasurementById(Integer id) {
        return measurementRepository.findMeasurementById(id);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
}
