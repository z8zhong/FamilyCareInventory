package com.example.FamilyCareInventory.service;

import com.example.FamilyCareInventory.dao.BuyJdbcTemp;
import com.example.FamilyCareInventory.dao.TransJdbcTemp;
import com.example.FamilyCareInventory.model.AccountSummary;
import com.example.FamilyCareInventory.model.Buy;
import com.example.FamilyCareInventory.model.BuyLine;
import com.example.FamilyCareInventory.model.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TransService {
    private final TransJdbcTemp transJdbcTemp;

    @Autowired
    public TransService(TransJdbcTemp transJdbcTemp) {
        this.transJdbcTemp = transJdbcTemp;
    }

    public void addTrans(String name, Date date, float acc_amt, float cash_amt, String note) {
        float cur_acc_amt = Float.valueOf(transJdbcTemp.getCurAmt().get("cur_acc_amt").toString());
        float cur_cash_amt = Float.valueOf(transJdbcTemp.getCurAmt().get("cur_cash_amt").toString());
        int cur_trans_id = transJdbcTemp.getCurTransId();
        Trans trans = new Trans(cur_trans_id + 1, name, date,
                acc_amt + cash_amt, cur_acc_amt + acc_amt, cur_cash_amt + cash_amt,
                cur_acc_amt + cur_cash_amt + acc_amt + cash_amt, note);
        transJdbcTemp.addTrans(trans);
    }

    public ArrayList<Trans> getAllTrans() {
        return transJdbcTemp.getAllTrans();
    }

    public ArrayList<AccountSummary> getAccountSummary() {
        return transJdbcTemp.getAccountSummary();
    }
}
