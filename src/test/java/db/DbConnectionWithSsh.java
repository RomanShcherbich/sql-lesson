package db;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ssh.SshConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DbConnectionWithSsh {

    private static Connection dbConnection = null;

    protected static final int MY_SQL_PORT = 3307;
    protected static final String MY_SQL_HOST = StringUtils.join("jdbc:mysql://0.0.0.0:", MY_SQL_PORT, "/");
    protected static final String MY_SQL_DATABASE = "restfulbooker";
    protected static final String MY_SQL_CONNECTION_URL = StringUtils.join(MY_SQL_HOST, MY_SQL_DATABASE);
    protected static final String MY_SQL_USERNAME = "root";
    protected static final String MY_SQL_PASSWORD = "password";

    public static Connection getConnection() {
        if (dbConnection == null) {
            connect();
        }
        return dbConnection;
    }

    public static void connect() {
        int port = MY_SQL_PORT;
        int rport = port;

        SshConnection sshConnection = new SshConnection();
        String sshHost = SshConnection.HOST;
        Session sshSession = null;
        try {
            sshConnection.connect();
            sshSession = sshConnection.getSession();
            int assinged_port = sshSession.setPortForwardingL(port, sshHost, port);
            log.info(StringUtils.join("localhost:", assinged_port, " -> ", sshHost, ":", rport));
            log.info(StringUtils.join(sshHost, ":", assinged_port, " -> ", "0.0.0.0:", rport));
            log.info("Port Forwarded");
            dbConnection = DriverManager.getConnection(MY_SQL_CONNECTION_URL, MY_SQL_USERNAME, MY_SQL_PASSWORD);
        } catch (SQLException e) {
            log.error("Can't create connection to crud_jsp_jdbc_demo database");
            throw new RuntimeException(e);
        } catch (JSchException e) {
            log.error("Ssh exception");
            e.printStackTrace();
            sshConnection.disconnect();
        }
    }

}
