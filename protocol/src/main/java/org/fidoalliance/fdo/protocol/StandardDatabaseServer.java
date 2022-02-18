package org.fidoalliance.fdo.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.h2.server.web.WebServer;
import org.h2.tools.Server;


public class StandardDatabaseServer implements DatabaseServer {


  private static LoggerService logger = new LoggerService(DatabaseServer.class);

  private static class RootConfig {
    @JsonProperty("h2-database")
    private ServiceConfig config = new ServiceConfig();

    public ServiceConfig getConfig() {
      return config;
    }
  }

  private static class ServiceConfig {
    @JsonProperty("web-server")
    private String[] webArgs;

    @JsonProperty("tcp-server")
    private String[] tcpArgs = new String[0];

    public String[] getTcpArgs() {
      return Config.resolve(tcpArgs);
    }

    public String[] getWebArgs() {
      if (webArgs != null) {
        return Config.resolve(webArgs);
      }
      return null;
    }
  }



  private static final ServiceConfig config = Config.getConfig(RootConfig.class).getConfig();



  @Override
  public void start() throws IOException {

    //-tcpPort, -tcpSSL, -tcpPassword, -tcpAllowOthers, -tcpDaemon, -trace, -ifExists, -ifNotExists, -baseDir, -key
    //-webPort, -webSSL, -webAllowOthers, -webDaemon, -trace, -ifExists, -ifNotExists, -baseDir,-properties

    //tcp port should be 9092
    //tcp port should be 8082

    try {
      //get the port from the connection string
      Server server = Server.createTcpServer(config.getTcpArgs()).start();

      logger.info("database tcp port " + server.getPort());

      String[] webArgs = config.getWebArgs();
      if (webArgs != null) {
        Server web = Server.createWebServer(webArgs).start();
        logger.info("database web port " + web.getPort());
      }
    } catch (SQLException e) {
      throw new IOException(e);
    }

  }
}
