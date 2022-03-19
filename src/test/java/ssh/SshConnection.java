package ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SshConnection {

    private Session session;
    public static final String HOST = "34.88.21.195";

    public void connect() {
        try {
            JSch jsch = new JSch();

            String user = "romanshcherbich";
            int port = 22;
            String privateKey = "/Users/romanshcherbich/.ssh/common_m1";

            jsch.addIdentity(privateKey, "3210788a002pB!");
            log.info("identity added ");

            session = jsch.getSession(user, HOST, port);
            log.info("session created.");

            // disabling StrictHostKeyChecking may help to make connection but makes it insecure
            // see http://stackoverflow.com/questions/30178936/jsch-sftp-security-with-session-setconfigstricthostkeychecking-no
            //
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            if (session.isConnected()) {
                log.info(StringUtils.join("session to ", HOST, " connected....."));
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void disconnect() {
        if (session != null) {
            if (session.isConnected()) {
                log.info("Closing SSH Connection");
                session.disconnect();
            }
            if (!session.isConnected()) {
                log.info("session disconnected.....");
            }
        } else {
            log.info("session is not exist");
        }
    }

    public Session getSession() {
        return this.session;
    }

}
