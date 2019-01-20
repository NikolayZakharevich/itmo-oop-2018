package dao;

import service.NoConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDao implements Dao {
    private String serverName;
    private String databaseName;
    private String userName;
    private String password;
    private Connection connection;

    public static class Builder {

        private String username;
        private String password;

        private String serverName = "localhost";
        private String databaseName = "shop_catalog";

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Builder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public Builder withDatabaseName(String dataBaseName) {
            this.databaseName = dataBaseName;
            return this;
        }

        public DatabaseDao build() {
            return new DatabaseDao(serverName, databaseName, username, password);
        }
    }

    public DatabaseDao(String serverName, String databaseName, String userName, String password) {
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        String connectionString = "jdbc:sqlserver://" + this.serverName + ";databaseName=" + this.databaseName;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString,
                    this.userName, this.password);
        } catch (SQLException e) {
            throw new DatabaseObjectNotFoundException("Connection failed. check server, database or user");
        } catch (ClassNotFoundException e) {
            throw new DriverRegistrationFailedException();
        }
    }


    @Override
    public boolean containsShop(String name) {
        String query = "select [id] from [shop] where [name]='" + name + "';";
        try {
            return executeQuery(query).next();
        } catch (SQLException e) {
            // do nothing
        }
        return false;
    }

    @Override
    public void addShop(String name) {
        String query = "insert into [shop]([name]) values('" + name + "');";
        executeQuery(query);
    }

    @Override
    public List<String> getShops() {
        String query = "select * from [shop]";
        ResultSet shopList = executeQuery(query);
        List<String> result = new ArrayList<>();
        try {
            while (shopList.next()) {
                result.add(shopList.getString(2));
            }
        } catch (SQLException e) {
            // do nothing
        }
        return result;
    }

    @Override
    public List<List<String>> getAllGoods(String shopName) {
        String query = "select g.name, g.cost, g.amount from storage as st " +
                "inner join good as g on st.good_id = g.id " +
                "inner join shop as s on st.shop_id = s.id " +
                "where s.name='" + shopName + "';";
        ResultSet resultSet = executeQuery(query);
        List<List<String>> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                result.add(row);
            }
        } catch (SQLException e) {
            // do nothing
        }
        return result;
    }

    @Override
    public boolean containsGood(String shopName, String name) {
        String query = "select s.id from storage as st " +
                "inner join good as g on st.good_id = g.id " +
                "inner join shop as s on st.shop_id = s.id " +
                "where s.name='" + shopName + "' and g.name='" + name + "';";
        try {
            return executeQuery(query).next();
        } catch (SQLException e) {
            // do nothing
        }
        return false;
    }


    @Override
    public void addGood(String shopName, String name, int cost, int amount) {
        String query = "insert into [good](name, cost, amount) " +
                "values('" + name + "', " + cost + ", " + amount + ");\n" +
                "insert into storage(shop_id, good_id) values " +
                "((SELECT id from shop where name='" + shopName + "'), " +
                "(SELECT id from good where name='" + name + "' and id=(select max(id) from good)))";
        executeQuery(query);
    }

    @Override
    public void setCost(String shopName, String goodName, int newCost) {
        String query = "update good set cost=" + newCost + " where id = (select g.id from storage as st " +
                "inner join good as g on st.good_id = g.id " +
                "inner join shop as s on st.shop_id = s.id " +
                "where s.name='" + shopName + "' and g.name='" + goodName + "');";
        executeQuery(query);
    }

    @Override
    public void setAmount(String shopName, String goodName, int newAmount) {
        String query = "update good set amount=" + newAmount + " where id = (select g.id from storage as st " +
                "inner join good as g on st.good_id = g.id " +
                "inner join shop as s on st.shop_id = s.id " +
                "where s.name='" + shopName + "' and g.name='" + goodName + "');";
        executeQuery(query);
    }

    @Override
    public int getCost(String shopName, String goodName) {
        String query = "select g.cost from storage as st " +
                "inner join good as g on st.good_id = g.id " +
                "inner join shop as s on st.shop_id = s.id " +
                "where s.name='" + shopName + "' and g.name='" + goodName + "';";
        ResultSet resultSet = executeQuery(query);
        try {
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getAmount(String shopName, String goodName) {
        String query = "select g.amount from storage as st " +
                "inner join good as g on st.good_id = g.id " +
                "inner join shop as s on st.shop_id = s.id " +
                "where s.name='" + shopName + "' and g.name='" + goodName + "';";
        ResultSet resultSet = executeQuery(query);
        try {
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            // do nothing
        }
        return 0;
    }


    public static Builder builder(String username, String password) {
        return new Builder(username, password);
    }

    private ResultSet executeQuery(String queryString) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
        } catch (SQLException e) {
            // do nothing
        }
        return resultSet;
    }


}
