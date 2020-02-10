package com.converter.controller;

import com.converter.data.ParserXML;
import com.converter.model.ConvertHistory;
import com.converter.model.Currency;
import com.converter.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ConverterController {
    @Autowired
    private CurrencyRepo currencyRepo;

    @GetMapping("/")
    public String index() {
        return "redirect:/converter";
    }

    @GetMapping("/converter")
    public String mainPage(Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        if (currencyRepo.findByCharcode("RUB") == null ||
                !dateFormat.format(currencyRepo.findByCharcode("RUB").getDate().getTime()).equals(dateFormat.format(new Date()))) {
            downloadCurrencies();
        }
        List<Map<Object, Object>> curCodes2Names = currencyRepo.findAllCharcodesAndNames();
        model.addAttribute("curCodes2Names", curCodes2Names);
        return "converter";
    }

    public void downloadCurrencies() {
        Set<Currency> currencies;
        currencies = ParserXML.parse();
        assert currencies != null;
        for (Currency currency : currencies) {
            currencyRepo.save(currency);
        }
    }

    @GetMapping("/convertResult/{convertHistory}")
    public String convertResult(@PathVariable ConvertHistory convertHistory, Model model) {
        model.addAttribute("convertRes", convertHistory.getToAmount());
        model.addAttribute("fromAmount", convertHistory.getFromAmount());
        model.addAttribute("from", convertHistory.getFromCurrency());
        model.addAttribute("to", convertHistory.getToCurrency());
        return "convertResult";
    }

}
