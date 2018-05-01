package br.com.traderhelper.importador.domain.commodities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Michael Sta. Helena on 13/12/2016.
 */
public class RegistroCotacaoDiariaCommodity {
	// CODECO - Código comoddity
	private String CODECO;
	// NAMECO - Nome comoddity
	private String NAMECO;
	// SETORC - Setor comoddity
	private String SETORC;
	// DATECT - Data cotação
	private String DATECT;
	// PRICEC - Preço fechamento
	private String PRICEC;
	// OPLENVL - Valor de abertura
	private String OPENVL;
	// HIGHVL - Valor mais alto
	private String HIGHVL;
	// LOWVLE - Valor mais baixo
	private String LOWVLE;

	public String getCODECO() {
		return CODECO;
	}

	public void setCODECO(String CODECO) {
		this.CODECO = CODECO;
	}

	public String getNAMECO() {
		return NAMECO;
	}

	public void setNAMECO(String NAMECO) {
		this.NAMECO = NAMECO;
	}

	public String getSETORC() {
		return SETORC;
	}

	public void setSETORC(String SETORC) {
		this.SETORC = SETORC;
	}

	public String getDATECT() {
		return DATECT;
	}

	public void setDATECT(String DATECT) {
		this.DATECT = DATECT;
	}

	public String getPRICEC() {
		return PRICEC;
	}

	public void setPRICEC(String PRICEC) {
		this.PRICEC = PRICEC;
	}

	public String getOPENVL() {
		return OPENVL;
	}

	public void setOPENVL(String OPENVL) {
		this.OPENVL = OPENVL;
	}

	public String getHIGHVL() {
		return HIGHVL;
	}

	public void setHIGHVL(String HIGHVL) {
		this.HIGHVL = HIGHVL;
	}

	public String getLOWVLE() {
		return LOWVLE;
	}

	public void setLOWVLE(String LOWVLE) {
		this.LOWVLE = LOWVLE;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("CODECO", CODECO).append("NAMECO", NAMECO).append("SETORC", SETORC)
				.append("DATECT", DATECT).append("PRICEC", PRICEC).append("OPENVL", OPENVL).append("HIGHVL", HIGHVL)
				.append("LOWVLE", LOWVLE).toString();
	}

	public static final class RegistroCotacaoDiariaCommodityBuilder {
		// CODECO - Código comoddity
		private String CODECO;
		// NAMECO - Nome comoddity
		private String NAMECO;
		// SETORC - Setor comoddity
		private String SETORC;
		// DATECT - Data cotação
		private String DATECT;
		// PRICEC - Preço fechamento
		private String PRICEC;
		// OPLENVL - Valor de abertura
		private String OPENVL;
		// HIGHVL - Valor mais alto
		private String HIGHVL;
		// LOWVLE - Valor mais baixo
		private String LOWVLE;

		public RegistroCotacaoDiariaCommodityBuilder() {
		}

		public static RegistroCotacaoDiariaCommodityBuilder aRegistroCotacaoDiariaCommodity() {
			return new RegistroCotacaoDiariaCommodityBuilder();
		}

		public RegistroCotacaoDiariaCommodityBuilder withCODECO(String CODECO) {
			this.CODECO = CODECO;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withNAMECO(String NAMECO) {
			this.NAMECO = NAMECO;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withSETORC(String SETORC) {
			this.SETORC = SETORC;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withDATECT(String DATECT) {
			this.DATECT = DATECT;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withPRICEC(String PRICEC) {
			this.PRICEC = PRICEC;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withOPENVL(String OPENVL) {
			this.OPENVL = OPENVL;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withHIGHVL(String HIGHVL) {
			this.HIGHVL = HIGHVL;
			return this;
		}

		public RegistroCotacaoDiariaCommodityBuilder withLOWVLE(String LOWVLE) {
			this.LOWVLE = LOWVLE;
			return this;
		}

		public RegistroCotacaoDiariaCommodity build() {
			RegistroCotacaoDiariaCommodity registroCotacaoDiariaCommodity = new RegistroCotacaoDiariaCommodity();
			registroCotacaoDiariaCommodity.setCODECO(CODECO);
			registroCotacaoDiariaCommodity.setNAMECO(NAMECO);
			registroCotacaoDiariaCommodity.setSETORC(SETORC);
			registroCotacaoDiariaCommodity.setDATECT(DATECT);
			registroCotacaoDiariaCommodity.setPRICEC(PRICEC);
			registroCotacaoDiariaCommodity.setOPENVL(OPENVL);
			registroCotacaoDiariaCommodity.setHIGHVL(HIGHVL);
			registroCotacaoDiariaCommodity.setLOWVLE(LOWVLE);
			return registroCotacaoDiariaCommodity;
		}
	}
}
