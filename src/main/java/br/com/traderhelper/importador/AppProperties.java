package br.com.traderhelper.importador;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Michael on 24/05/2017.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
