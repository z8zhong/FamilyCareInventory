package com.example.FamilyCareInventory.dao;

import com.example.FamilyCareInventory.model.AccountSummary;
import com.example.FamilyCareInventory.model.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TransJdbcTemp {
    private static final String INSERT_TRANS_SQL =
            "INSERT INTO Account(transId, trans_name, trans_time, trans_amt, cur_acc_amt, cur_cash_amt, total_amt, note) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_TRANS_SQL =
            "SELECT * FROM Account ORDER BY trans_time DESC, transId DESC";

    private static final String SELECT_CUR_AMT_SQL =
            "SELECT cur_acc_amt, cur_cash_amt FROM Account ORDER BY transId DESC LIMIT 1";
    private static final String SELECT_LAST_INSERT_TRANS_ID_SQL =
            "SELECT MAX(transId) AS transId FROM Account";

    private static final String SELECT_ACCOUNT_SUMMARY_SQL =
            "SELECT IFNULL(Buy.deposit, 0) AS buy_amt, \n" +
                    "       IFNULL(`Cost`.amt, 0) AS cost_amt,\n" +
                    "       IFNULL(`Order`.total, 0) AS order_amt,\n" +
                    "       IFNULL(acc.cur_acc_amt, 0) AS cur_acc_amt,\n" +
                    "       IFNULL(acc.cur_cash_amt, 0) AS cur_cash_amt,\n" +
                    "       IFNULL(acc.total_amt, 0) AS cur_total_amt\n" +
                    "FROM \n" +
                    "(SELECT SUM(deposit) AS deposit FROM Buy) Buy,\n" +
                    "(SELECT SUM(amt) AS amt FROM `Cost`) `Cost`, \n" +
                    "(SELECT SUM(total) AS total FROM `Order`) `Order`, \n" +
                    "(SELECT cur_acc_amt, cur_cash_amt, total_amt FROM Account ORDER BY transId DESC LIMIT 1) acc";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TransJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTrans(Trans trans) {
        jdbcTemplate.update(INSERT_TRANS_SQL,
                trans.getTransId(),
                trans.getTrans_name(),
                trans.getTrans_date(),
                trans.getTrans_amt(),
                trans.getCur_acc_amt(),
                trans.getCur_cash_amt(),
                trans.getTotal_amt(),
                trans.getNote());
    }

    public int getCurTransId() {
        int transId = 0;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_LAST_INSERT_TRANS_ID_SQL);
        if (rs.next()) {
            transId = rs.getInt("transId");
        }
        return transId;
    }

    public ArrayList<Trans> getAllTrans() {
        ArrayList<Trans> transList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_TRANS_SQL);
        while (rs.next()) {
            Trans trans = new Trans(rs.getInt("transId"), rs.getString("trans_name"), rs.getDate("trans_time"),
                    rs.getFloat("trans_amt"), rs.getFloat("cur_acc_amt"), rs.getFloat("cur_cash_amt"), rs.getFloat("total_amt"), rs.getString("note"));
            transList.add(trans);
        }
        return transList;
    }

    public HashMap getCurAmt() {
        HashMap curAmt = new HashMap<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_CUR_AMT_SQL);
        while (rs.next()) {
            curAmt.put("cur_acc_amt", rs.getFloat("cur_acc_amt"));
            curAmt.put("cur_cash_amt", rs.getFloat("cur_cash_amt"));
        }
        return curAmt;
    }

    public ArrayList<AccountSummary> getAccountSummary() {
        ArrayList<AccountSummary> accountSummarys = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ACCOUNT_SUMMARY_SQL);
        while (rs.next()) {
        AccountSummary accountSummary = new AccountSummary(rs.getFloat("buy_amt"), rs.getFloat("cost_amt"), rs.getFloat("order_amt"),
                    rs.getFloat("cur_acc_amt"), rs.getFloat("cur_cash_amt"), rs.getFloat("cur_total_amt"));

        accountSummarys.add(accountSummary);
        }
        return accountSummarys;
    }
}
