package pl.pf.sonalake.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Kalkulator wynagrodznia")
@RestController
@RequestMapping(path = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaryCalculatorRestController {

    private final ISalaryCalculatorService salaryCalculatorService;

    public SalaryCalculatorRestController(ISalaryCalculatorService salaryCalculatorService) {
         this.salaryCalculatorService = salaryCalculatorService;
    }

    @ApiOperation(value = "Usługa przeliczająca wynagrodzenie do PLN", response = SalaryCalculatorResponse.class)
    @GetMapping(path = "/{countryCode}")
    public ResponseEntity<SalaryCalculatorResponse> getSalaryCalculation(@PathVariable CountryCode countryCode, @Valid SalaryCalculatorParams salaryCalculatorParams) {

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(salaryCalculatorService.getPolishSalary(countryCode, salaryCalculatorParams));
    }
}
