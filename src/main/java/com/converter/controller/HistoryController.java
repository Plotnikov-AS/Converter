package com.converter.controller;

import com.converter.model.ConvertHistory;
import com.converter.model.User;
import com.converter.repo.ConvertHistoryRepo;
import com.converter.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ConvertHistoryRepo convertHistoryRepo;

    @GetMapping
    public String showHistory(Model model) {
        List<ConvertHistory> histories = convertHistoryRepo.findTop5ByDateOrderByDateDesc(new Date());
        List<Map<Object, Object>> curCodes2Names = currencyRepo.findAllCharcodesAndNames();

        model.addAttribute("curCodes2Names", curCodes2Names);
        model.addAttribute("histories", histories);
        return "history";
    }

    @PostMapping("/save")
    public String saveInHistory(@RequestParam("from") String fromAmountStr,
                                @RequestParam("fromCharcode") String fromCharcode,
                                @RequestParam("toCharcode") String toCharcode,
                                Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date date = new Date();
        Double courseFrom = currencyRepo.findByCharcode(fromCharcode).getCourse() / currencyRepo.findByCharcode(fromCharcode).getNominal();
        Double courseTo = currencyRepo.findByCharcode(toCharcode).getCourse() / currencyRepo.findByCharcode(toCharcode).getNominal();
        Double courseOnDate = courseFrom / courseTo;
        Double fromAmount = Double.parseDouble(fromAmountStr);
        Double toAmount;

        if (fromCharcode.equals(toCharcode)) {
            toAmount = fromAmount;
        } else {
            toAmount = convert(fromAmount, toCharcode);
        }

        ConvertHistory convertHistory = new ConvertHistory();
        convertHistory.setUserId(user.getId());
        convertHistory.setFromCurrency(currencyRepo.findByCharcode(fromCharcode).getName());
        convertHistory.setToCurrency(currencyRepo.findByCharcode(toCharcode).getName());
        convertHistory.setFromAmount(fromAmount);
        convertHistory.setToAmount(toAmount);
        convertHistory.setDate(date);
        convertHistory.setCourseOnDate(courseOnDate);
        convertHistoryRepo.save(convertHistory);

        model.addAttribute("from", fromAmountStr);
        model.addAttribute("fromCode", fromCharcode);
        model.addAttribute("toCode", toCharcode);
        return "redirect:/convertResult/" + convertHistoryRepo.findLastId();
    }

    private Double convert(Double fromAmount, String toCharcode) {
        Double toCourse = currencyRepo.findByCharcode(toCharcode).getCourse();
        Integer toNominal = currencyRepo.findByCharcode(toCharcode).getNominal();
        return fromAmount * toCourse / toNominal;
    }
}
