package pl.pf.sonalake.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pf.sonalake.api.model.request.SalaryCalculatorParams;
import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.ISalaryCalculatorService;

import javax.validation.Valid;

/**
 * Klasa kontrololera usługi SalaryCalculator
 *
 * @author pfutro
 */
@RestController
@RequestMapping(path = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaryCalculatorRestController {

    private final ISalaryCalculatorService salaryCalculatorService;

    public SalaryCalculatorRestController(ISalaryCalculatorService salaryCalculatorService) {
         this.salaryCalculatorService = salaryCalculatorService;
    }

    @GetMapping(path = "/{countryCode}")
    public ResponseEntity<SalaryCalculatorResponse> getSalaryCalculation(@PathVariable CountryCode countryCode, @Valid SalaryCalculatorParams salaryCalculatorParams) {

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(salaryCalculatorService.getPolishSalary(countryCode, salaryCalculatorParams));
    }
}
