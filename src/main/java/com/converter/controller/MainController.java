package com.converter.controller;

import com.converter.data.ParserXML;
import com.converter.model.ConvertHistory;
import com.converter.model.Currency;
import com.converter.model.User;
import com.converter.repo.ConvertHistoryRepo;
import com.converter.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ConvertHistoryRepo convertHistoryRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        Set<Currency> currencies;
        currencies = downloadCurrencies();
        Map<String, String> curCode2Name = new TreeMap<>();
        for (Currency currency : currencies) {
            curCode2Name.put(currency.getName(), currency.getCharcode());
        }
        curCode2Name.put("Российский рубль", "RUB");
        model.addAttribute("curCode2Name", curCode2Name);
        return "main";
    }

    public Set<Currency> downloadCurrencies() {
        Set<Currency> currencies;
        currencies = ParserXML.parse();
        assert currencies != null;
        for (Currency currency : currencies) {
            currencyRepo.save(currency);
        }
        return currencies;
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("from") String fromStr,
                          @RequestParam("fromCode") String fromCharcode,
                          @RequestParam("toCode") String toCharcode,
                          Model model) {

        Double fromAmount = Double.parseDouble(fromStr);
        Double convertRes;
        if (fromCharcode.equals(toCharcode)) {
            convertRes = fromAmount;
        } else {
            Double toValue = currencyRepo.findByCharcode(toCharcode).getValue();
            Integer toNominal = currencyRepo.findByCharcode(toCharcode).getNominal();
            convertRes = fromAmount * toValue / toNominal;
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        saveInHistory(
                fromCharcode,
                toCharcode,
                fromAmount,
                convertRes,
                new Date(),
                user);
        List<ConvertHistory> histories = convertHistoryRepo.findAllByUserId(user.getId());

        model.addAttribute("convertRes", convertRes);
        model.addAttribute("fromAmount", fromAmount);
        model.addAttribute("from", currencyRepo.findByCharcode(fromCharcode).getName());
        model.addAttribute("to", currencyRepo.findByCharcode(toCharcode).getName());
        model.addAttribute("histories", histories);
        return "convertResult";

    }

    private void saveInHistory(String fromCharcode,
                               String toCharcode,
                               Double fromAmount,
                               Double toAmount,
                               Date date,
                               User user) {
        ConvertHistory convertHistory = new ConvertHistory();

        convertHistory.setUserId(user.getId());

        convertHistory.setFromCurrency(currencyRepo.findByCharcode(fromCharcode).getName());
        convertHistory.setToCurrency(currencyRepo.findByCharcode(toCharcode).getName());
        convertHistory.setFromAmount(fromAmount);
        convertHistory.setToAmount(toAmount);
        convertHistory.setDate(date);
        convertHistoryRepo.save(convertHistory);
    }
}
