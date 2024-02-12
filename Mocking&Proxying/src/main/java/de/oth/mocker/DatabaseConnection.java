package de.oth.mocker;

public interface DatabaseConnection {
    public void close();
    public void connect();
    public void query(String str);
    public void execute();
    public void delete();
}
