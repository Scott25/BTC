package com.example;

import com.example.bitcoin.BTC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BTC.class)
public class testBTC {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testgetBTCpriceByCurrencyCodeMissingParameter() throws Exception {
        mockMvc.perform(get("/btc")).andExpect(status().isBadRequest());
    }

    @Test
    public void testgetBTCpriceByCurrencyCodeWithParameter() throws Exception {
        mockMvc.perform(get("/btc").param("currency", "USD")).andExpect(status().isOk());
    }

    @Test
    public void testgetBTCHistoryDataByCurrencyCodeWithoutParameter() throws Exception {
        mockMvc.perform(get("/history")).andExpect(status().isBadRequest());
    }

    @Test
    public void testgetBTCHistoryDataByCurrencyCodeWithParameter() throws Exception {
        mockMvc.perform(get("/history").param("currency", "USD")).andExpect(status().isOk());
    }

}
