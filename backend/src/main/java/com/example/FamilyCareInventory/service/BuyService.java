package com.example.FamilyCareInventory.service;

import com.example.FamilyCareInventory.dao.BuyJdbcTemp;
import com.example.FamilyCareInventory.model.Buy;
import com.example.FamilyCareInventory.model.BuyLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class BuyService {
    private final BuyJdbcTemp buyJdbcTemp;
    private final TransService transService;

    @Autowired
    public BuyService(BuyJdbcTemp buyJdbcTemp, TransService transService) {
        this.buyJdbcTemp = buyJdbcTemp;
        this.transService = transService;
    }

    public int addBuy(Buy buy) {
        int buyId = buyJdbcTemp.addBuy(buy);
        int buyLineId = 1;
        ArrayList<BuyLine> buyLines = buy.getProducts();
        try {
            for (BuyLine buyline : buyLines) {
                buyline.setBuyId(buyId);
                buyline.setBuylineId(buyLineId);
                buyJdbcTemp.addBuyLine(buyline);
                buyLineId++;
            }
            transService.addTrans(buy.getName(), buy.getBuy_time(), -1 * buy.getDeposit(), 0, buy.getNote());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buyId;
    }

    public ArrayList<Buy> getAllBuy() {
        return buyJdbcTemp.getAllBuy();
    }

    public ArrayList<BuyLine> getBuyLine(int buyId) {
        return buyJdbcTemp.getBuyLine(buyId);
    }

    public void deleteBuy(int buyId) {
        Buy buy = buyJdbcTemp.getBuyByBuyId(buyId);
        try {
            buyJdbcTemp.deleteBuy(buyId);
            transService.addTrans("返还款：" + buy.getName(), new Date(), buy.getDeposit(), 0, buy.getNote());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateShipTime(Date ship_time, int buyId) {
        System.out.println("service:"+ship_time);
        buyJdbcTemp.updateShipTime(ship_time, buyId);
    }
}
