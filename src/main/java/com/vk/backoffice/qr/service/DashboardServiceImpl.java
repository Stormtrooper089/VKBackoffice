package com.vk.backoffice.qr.service;

import com.vk.backoffice.qr.dao.QrDaoImpl;
import com.vk.backoffice.qr.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl {

    @Autowired
    private QrDaoImpl qrDao;

    public List<Statistic> getQrGenerationStats(String queryType) {
        List<Object[]> objectList = qrDao.getGeneratedQrStatisticsByMonth(queryType);
        List<Statistic> statistics = new ArrayList<>();
        for (Object[] object : objectList) {
            Statistic statistic = new Statistic(Integer.parseInt(object[0].toString()), Integer.parseInt(object[1].toString()));
            statistics.add(statistic);
        }
        for (int month = 1; month <= 12; month++) {
            final int currentKey = month;
            if (!statistics.stream().anyMatch(statistic ->
                    statistic.getKey() == currentKey)) {
                statistics.add(new Statistic(currentKey, 0));
            }
        }
        List<Statistic> statisticList = statistics.stream()
                .sorted(Comparator.comparing(Statistic::getKey))
                .collect(Collectors.toList());

        return statisticList;
    }

}
