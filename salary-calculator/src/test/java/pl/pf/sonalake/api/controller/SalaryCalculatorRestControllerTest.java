package pl.pf.sonalake.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;
import pl.pf.sonalake.service.impl.SalaryCalculatorService;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
/**
 * Test mesody kontrolera REST ${@link SalaryCalculatorRestController}
 *
 * @author pfutro
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SalaryCalculatorRestController.class)
public class SalaryCalculatorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryCalculatorService salaryServiceMock;

    @Test
    public void getSalaryCalculation() throws Exception {

        //given
        String controllerUri = "/country";
        CountryCode countryCode = CountryCode.DE;
        BigDecimal dailySalaryBrutto = new BigDecimal(100);
        BigDecimal exchangeRate = new BigDecimal(4.5);
        BigDecimal monthlyNettoPLN = new BigDecimal(4500);

        SalaryCalculatorResponse salaryCalculatorResponse = new SalaryCalculatorResponse();
        salaryCalculatorResponse.setExchangeRate(exchangeRate);
        salaryCalculatorResponse.setMonthlyNettoPLN(monthlyNettoPLN);

        UriComponentsBuilder uri = UriComponentsBuilder
                .fromUriString(controllerUri)
                .pathSegment(countryCode.name())
                .queryParam("dailySalary", dailySalaryBrutto.toString());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri.toUriString()).accept(MediaType.APPLICATION_JSON);

        Mockito.when(salaryServiceMock.getPolishSalary(Mockito.any(), Mockito.any()))
                .thenReturn(salaryCalculatorResponse);

        //when and then
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.exchangeRate", is(exchangeRate.doubleValue())))
                .andExpect(jsonPath("$.monthlyNettoPLN", is(monthlyNettoPLN.intValue())))
         ;

    }
}