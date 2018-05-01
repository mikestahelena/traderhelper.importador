package br.com.traderhelper.importador.domain.acoes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 14/12/2016.
 */
public class CotacaoHistoricaAcao {

	private List<RegistroCotacaoDiariaAcao> registroCotacaoDiariaAcaoList;
	private RegistroTrailer registroTrailer;

	public List<RegistroCotacaoDiariaAcao> getRegistroCotacaoDiariaAcaoList() {
		if (registroCotacaoDiariaAcaoList == null) {
			registroCotacaoDiariaAcaoList = new ArrayList<>();
		}
		return registroCotacaoDiariaAcaoList;
	}

	public void setRegistroCotacaoDiariaAcaoList(List<RegistroCotacaoDiariaAcao> registroCotacaoDiariaAcaoList) {
		this.registroCotacaoDiariaAcaoList = registroCotacaoDiariaAcaoList;
	}

	public RegistroTrailer getRegistroTrailer() {
		return registroTrailer;
	}

	public void setRegistroTrailer(RegistroTrailer registroTrailer) {
		this.registroTrailer = registroTrailer;
	}

	public void addCotacaoDiaria(RegistroCotacaoDiariaAcao registroCotacaoDiariaAcao) {
		this.getRegistroCotacaoDiariaAcaoList().add(registroCotacaoDiariaAcao);
	}
}
