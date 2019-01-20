package dao;

public class Authorizator {
    private String username;
    private String password;

    public Authorizator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Dao connect() {
        return DatabaseDao.builder(username, password).build();
    }
}
