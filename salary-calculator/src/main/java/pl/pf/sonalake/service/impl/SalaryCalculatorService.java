package pl.pf.sonalake.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pf.sonalake.api.model.request.SalaryCalculatorParams;
import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.api.model.dict.CountryData;
import pl.pf.sonalake.dto.IResponseConverter;
import pl.pf.sonalake.exeption.NbpExeption;
import pl.pf.sonalake.io.entity.CurrencyEntity;
import pl.pf.sonalake.io.repository.INbpRepository;
import pl.pf.sonalake.service.ISalaryCalculatorService;
import pl.pf.sonalake.service.SalaryCalculation;

import java.math.BigDecimal;
import java.util.Optional;


/**
 * Klasa serwisu usługi.<br>
 * Pobiera kursy walut i przekazuje razem z kodem kraju i stawką dzienną brutto do klasy SalaryCalculation.
 *
 *
 * @author pfutro
 */
@Slf4j
@Service
public class SalaryCalculatorService implements ISalaryCalculatorService {


    @Autowired
    private final SalaryCalculation salaryCalculation;

    @Autowired
    private final INbpRepository nbpRepository;

    @Autowired
    private final IResponseConverter responseConverter;

    public SalaryCalculatorService(SalaryCalculation salaryCalculation, INbpRepository nbpRepository, IResponseConverter responseConverter) {
        this.salaryCalculation = salaryCalculation;
        this.nbpRepository = nbpRepository;
        this.responseConverter = responseConverter;
    }


    @Override
    public SalaryCalculatorResponse getPolishSalary(CountryCode countryCode, SalaryCalculatorParams salaryCalculatorParams) {


        BigDecimal mid = new BigDecimal(1);
        if (!CountryCode.PL.equals(countryCode)) {
            Optional<CurrencyEntity> currencyEntity = nbpRepository.getCurrentExchangeRate(CountryData.forCountryCode(countryCode).getCurrencyCode()); //tu może rzucić wyjatek!! -> do obsluzenai
            mid = currencyEntity.orElseThrow(() -> new NbpExeption("Brak kursu wymiany waluty.")).getRates().get(0).getMid();
        }

        log.debug("SalaryCalculatorService: #countryCode:{} #dailySalary:{} #mid:{}", countryCode, salaryCalculatorParams.getDailySalary(), mid );
        return responseConverter.convert(salaryCalculation.calculateSalary(countryCode, salaryCalculatorParams.getDailySalary(), mid), mid);
    }
}
