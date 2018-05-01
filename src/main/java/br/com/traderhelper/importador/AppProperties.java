package br.com.traderhelper.importador;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Michael on 24/05/2017.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

	private String pathBovespa;
	private String pathCommodity;

	public String getPathBovespa() {
		return pathBovespa;
	}

	public void setPathBovespa(String pathBovespa) {
		this.pathBovespa = pathBovespa;
	}

	public String getPathCommodity() {
		return pathCommodity;
	}

	public void setPathCommodity(String pathCommodity) {
		this.pathCommodity = pathCommodity;
	}
}
