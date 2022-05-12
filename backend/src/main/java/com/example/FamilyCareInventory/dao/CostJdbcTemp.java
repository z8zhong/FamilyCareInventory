package com.example.FamilyCareInventory.dao;

import com.example.FamilyCareInventory.model.Buy;
import com.example.FamilyCareInventory.model.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class CostJdbcTemp {
    private static final String INSERT_COST_SQL =
            "INSERT INTO `Cost`(name, amt, cost_time, note) VALUES(?, ?, ?, ?)";
    private static final String SELECT_LAST_INSERT_COST_ID_SQL =
            "SELECT LAST_INSERT_ID() AS costId FROM `Cost` LIMIT 1;";

    private static final String SELECT_COST_BY_COSTID_SQL =
            "SELECT * FROM `Cost` WHERE costId = ?";

    private static final String SELECT_COST_SQL =
            "SELECT * FROM `Cost` ORDER BY cost_time DESC";

    private static final String DELETE_COST_BY_COSTID_SQL =
            "DELETE FROM `Cost` WHERE costId = ?";

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CostJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addCost(Cost cost) {
        jdbcTemplate.update(INSERT_COST_SQL, cost.getName(), cost.getAmt(), cost.getCost_time(), cost.getNote());
        int costID = 0;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_LAST_INSERT_COST_ID_SQL);
        if (rs.next()) {
            costID = rs.getInt("costId");
        }
        return costID;
    }

    public ArrayList<Cost> getAllCost() {
        ArrayList<Cost> costList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_COST_SQL);
        while (rs.next()) {
            Cost cost = new Cost(rs.getInt("costId"), rs.getString("name"), rs.getFloat("amt"),
                    rs.getDate("cost_time"), rs.getString("note"));
            costList.add(cost);
        }
        return costList;
    }

    public Cost getCostByCostId(int costId) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_COST_BY_COSTID_SQL, costId);
        Cost cost = new Cost();
        while (rs.next()) {
            cost = new Cost(rs.getInt("costId"), rs.getString("name"), rs.getFloat("amt"),
                    rs.getDate("cost_time"), rs.getString("note"));
        }
        return cost;
    }

    public void deleteCost(int costId) {
        jdbcTemplate.update(DELETE_COST_BY_COSTID_SQL, costId);
    }
}
