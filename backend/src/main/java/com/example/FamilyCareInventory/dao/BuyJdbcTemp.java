package com.example.FamilyCareInventory.dao;

import com.example.FamilyCareInventory.model.Buy;
import com.example.FamilyCareInventory.model.BuyLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class BuyJdbcTemp {
    private static final String INSERT_BUY_SQL =
            "INSERT INTO Buy(buy_time, note, deposit, name) VALUES(?, ?, ?, ?)";

    private static final String INSERT_BUY_LINE_SQL =
            "INSERT INTO BuyLine(buyId, buylineId, product_name, product_version, quantity, price) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BUY_BY_BUYID_SQL =
            "SELECT * FROM Buy WHERE buyId = ?";
    private static final String SELECT_ALL_BUY_SQL =
            "SELECT * FROM Buy ORDER BY buy_time DESC";

    private static final String SELECT_BUY_LINE_BY_BUYID_SQL =
            "SELECT * FROM BuyLine WHERE buyId = ? ORDER BY buylineId DESC";

    private static final String SELECT_LAST_INSERT_BUY_ID_SQL =
            "SELECT LAST_INSERT_ID() AS buyId FROM Buy LIMIT 1;";

    private static final String DELETE_BUY_BY_BUYID_SQL =
            "DELETE FROM Buy WHERE buyId = ?";

    private static final String UPDATE_SHIP_TIME_SQL =
            "UPDATE Buy SET ship_time = ? WHERE buyId = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BuyJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addBuy(Buy buy) {
        jdbcTemplate.update(INSERT_BUY_SQL, buy.getBuy_time(), buy.getNote(), buy.getDeposit(), buy.getName());
        int buyID = 0;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_LAST_INSERT_BUY_ID_SQL);
        if (rs.next()) {
            buyID = rs.getInt("buyId");
        }
        return buyID;
    }

    public void addBuyLine(BuyLine buyLine) {
        jdbcTemplate.update(INSERT_BUY_LINE_SQL,
                buyLine.getBuyId(),
                buyLine.getBuylineId(),
                buyLine.getProduct_name(),
                buyLine.getProduct_version(),
                buyLine.getQuantity(),
                buyLine.getPrice());
    }

    public ArrayList<Buy> getAllBuy() {
        ArrayList<Buy> buyList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_BUY_SQL);
        while (rs.next()) {
            Buy buy = new Buy(rs.getInt("buyId"), rs.getDate("buy_time"), rs.getString("note"),
                    rs.getFloat("deposit"), null, rs.getDate("ship_time"), rs.getString("name"));
            buyList.add(buy);
        }
        return buyList;
    }

    public Buy getBuyByBuyId(int buyId) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_BUY_BY_BUYID_SQL, buyId);
        Buy buy = new Buy();
        while (rs.next()) {
            buy = new Buy(rs.getInt("buyId"), rs.getDate("buy_time"), rs.getString("note"),
                    rs.getFloat("deposit"), null, rs.getDate("ship_time"), rs.getString("name"));
        }
        return buy;
    }

    public ArrayList<BuyLine> getBuyLine(int buyId) {
        ArrayList<BuyLine> buyLineList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_BUY_LINE_BY_BUYID_SQL, buyId);
        while (rs.next()) {
            BuyLine buyLine = new BuyLine(rs.getInt("buyId"), rs.getInt("buylineId"), rs.getString("product_name"),
                    rs.getString("product_version"), rs.getInt("quantity"), rs.getFloat("price"));
            buyLineList.add(buyLine);
        }
        return buyLineList;
    }

    public void deleteBuy(int buyId) {
        jdbcTemplate.update(DELETE_BUY_BY_BUYID_SQL, buyId);
    }

    public void updateShipTime(Date ship_time, int buyId) {
        System.out.println("dao:"+ship_time);
        jdbcTemplate.update(UPDATE_SHIP_TIME_SQL, ship_time, buyId);
    }
}
