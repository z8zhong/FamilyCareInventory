package com.example.FamilyCareInventory.service;

import com.example.FamilyCareInventory.dao.CostJdbcTemp;
import com.example.FamilyCareInventory.model.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CostService {
    CostJdbcTemp costJdbcTemp;
    TransService transService;

    @Autowired
    public CostService(CostJdbcTemp costJdbcTemp, TransService transService) {
        this.costJdbcTemp = costJdbcTemp;
        this.transService = transService;
    }

    public int addCost(Cost cost) {
        int costId = 0;
        try {
            transService.addTrans(cost.getName(), cost.getCost_time(), -1 * cost.getAmt(), 0, cost.getNote());
            costId = costJdbcTemp.addCost(cost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return costId;
    }

    public ArrayList<Cost> getAllCost() {
        return costJdbcTemp.getAllCost();
    }

    public void deleteCost(int costId) {
        Cost cost = costJdbcTemp.getCostByCostId(costId);
        try {
            costJdbcTemp.deleteCost(costId);
            transService.addTrans("返还款：" + cost.getName(), new Date(), cost.getAmt(), 0, cost.getNote());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
