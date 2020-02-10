package com.converter.controller;

import com.converter.model.ConvertHistory;
import com.converter.repo.ConvertHistoryRepo;
import com.converter.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ConvertHistoryRepo convertHistoryRepo;

    @PostMapping
    public String search(@RequestParam(required = false) String dateStr,
                         @RequestParam String fromCharcode,
                         @RequestParam String toCharcode,
                         Model model) {
        List<ConvertHistory> histories = null;
        String fromName = currencyRepo.findByCharcode(fromCharcode).getName();
        String toName = currencyRepo.findByCharcode(toCharcode).getName();
        if (dateStr.isEmpty()) {
            histories = convertHistoryRepo.findAllByFromAndTo(fromName, toName);
        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(dateStr);
                histories = convertHistoryRepo.findAllByDateAndFromCurrencyAndToCurrencyOrderByDate(date, fromName, toName);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<Map<Object, Object>> curCodes2Names = currencyRepo.findAllCharcodesAndNames();

        model.addAttribute("histories", histories);
        model.addAttribute("curCodes2Names", curCodes2Names);
        return "history";
    }
}
