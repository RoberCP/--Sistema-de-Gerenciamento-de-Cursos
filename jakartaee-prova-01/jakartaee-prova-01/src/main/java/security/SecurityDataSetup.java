package security;

import config.ApplicationConfig;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Singleton
@Startup
public class SecurityDataSetup {

    @Resource
    private DataSource dataSource; // default datasource

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    private ApplicationConfig applicationConfig;

    @PostConstruct
    public void init() {

        passwordHash.initialize(applicationConfig.getHashAlgorithmParameterMap());

        executeUpdate(dataSource, "DROP TABLE IF EXISTS usuario");
        executeUpdate(dataSource, "DROP TABLE IF EXISTS usurio_grupo");

        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS usuario(nome VARCHAR(64) PRIMARY KEY, senha VARCHAR(255))");
        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS usuario_grupo(usuario_nome VARCHAR(64), grupo_nome VARCHAR(64))");

        executeUpdate(dataSource, "INSERT INTO usuario VALUES('usuario', '" + passwordHash.generate("secret".toCharArray()) + "')");
        executeUpdate(dataSource, "INSERT INTO usuario VALUES('admin', '" + passwordHash.generate("secret".toCharArray()) + "')");

        executeUpdate(dataSource, "INSERT INTO usuario_grupo VALUES('admin', 'admin')");
        executeUpdate(dataSource, "INSERT INTO usuario_grupo VALUES('admin', 'usuario')");

        executeUpdate(dataSource, "INSERT INTO usuario_grupo VALUES('usuario', 'usuario')");
    }

    @PreDestroy
    public void destroy() {
        try {
            executeUpdate(dataSource, "DROP TABLE IF EXISTS usuario");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS usuario_grupo");
        } catch (Exception e) {
            // silently ignore
        }
    }

    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
