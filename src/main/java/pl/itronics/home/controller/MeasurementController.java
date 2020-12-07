package pl.itronics.home.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.itronics.home.domain.Measurement;
import pl.itronics.home.service.MeasurementService;

import java.util.List;

@RestController
@Api(value = "measurement", tags = {"measurement"})
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/measurement/save")
    public ResponseEntity<Measurement> saveMeasurement(Measurement measurement) {
        return new ResponseEntity<>(measurementService.saveMeasurement(measurement), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/measurement/getById")
    public ResponseEntity<Measurement> getMeasurementById(Integer id) {
        List<Measurement> measurements = measurementService.findMeasurementById(id);

        if(measurements.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(measurements.get(0), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/measurement/getAll")
    public ResponseEntity<List<Measurement>> getAllMeasurements(Integer id) {
        List<Measurement> measurements = measurementService.findAll();

        if(measurements.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }
}
