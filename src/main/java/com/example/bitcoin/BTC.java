package com.example.bitcoin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
public class BTC {

    @GetMapping("/btc")
    public String getBTCpriceByCurrencyCode(@RequestParam("currency") String getCurrency) throws IOException {

        String urlString = "https://api.coindesk.com/v1/bpi/historical/close.json?currency=" + getCurrency;

        LocalDate localDate = LocalDate.now();
        LocalDate currentMonth = localDate.minus(1, ChronoUnit.DAYS);

        URL url = new URL(urlString);
        try {
            InputStream inputStream = url.openStream();
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(inputStream));
            JsonObject jsonObject = new JsonParser().parse(readBuffer.readLine()).getAsJsonObject();
            jsonObject = jsonObject.get("bpi").getAsJsonObject();

            return " Price for 1 BTC = "+ jsonObject.get(currentMonth.toString()) + " " +getCurrency;
        } catch (FileNotFoundException currencyNotFound) {
            return "Wrong currency code.";
        }
    }

    @GetMapping("/history")
    public String getBTCHistoryDataByCurrencyCode(@RequestParam("currency") String getCurrencyCode) throws IOException {

        String urlString = "https://api.coindesk.com/v1/bpi/historical/close.json?currency=" + getCurrencyCode;

        LocalDate localDate = LocalDate.now();
        LocalDate previousMonth = localDate.minus(1, ChronoUnit.MONTHS)
                .minus(1, ChronoUnit.DAYS);
        LocalDate currentMonth = localDate.minus(1, ChronoUnit.DAYS);

        URL url = new URL(urlString);
        try {
            InputStream inputStream = url.openStream();
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(inputStream));
            JsonObject jsonObject = new JsonParser().parse(readBuffer.readLine()).getAsJsonObject();
            jsonObject = jsonObject.get("bpi").getAsJsonObject();

            return "Previous Month " + getCurrencyCode + " Price for 1 BTC = "
                    + jsonObject.get(previousMonth.toString()) + " Current Month "
                    + getCurrencyCode + " Price for 1 BTC = " + jsonObject.get(currentMonth.toString());
        } catch (FileNotFoundException currencyNotFound) {
            return "Wrong currency code.";
        }
    }
}
