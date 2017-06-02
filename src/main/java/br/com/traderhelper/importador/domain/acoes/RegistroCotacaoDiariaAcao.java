package br.com.traderhelper.importador.domain.acoes;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Michael Sta. Helena on 13/12/2016.
 */
public class RegistroCotacaoDiariaAcao {
    //TIPREG - TIPO DE REGISTRO
    private String TIPREG;
    //DATA DO PREGÃO
    private String DATAPG;
    //CODBDI - CÓDIGO BDI
    private String CODBDI;
    //CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
    private String CODNEG;
    //TPMERC - TIPO DE MERCADO
    private String TPMERC;
    //NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
    private String NOMRES;
    //ESPECI - ESPECIFICAÇÃO DO PAPEL
    private String ESPECI;
    //PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
    private String PRAZOT;
    //MODREF - MOEDA DE REFERÊNCIA
    private String MODREF;
    //PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
    private String PREABE;
    //PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
    private String PREMAX;
    //PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
    private String PREMIN;
    //PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
    private String PREMED;
    //PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO
    private String PREULT;
    //PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
    private String PREOFC;
    //PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
    private String PREOFV;
    //TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
    private String TOTNEG;
    //QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
    private String QUATOT;
    //VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
    private String VOLTOT;
    //PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO PARA O MERCADO DE TERMO SECUNDÁRIO
    private String PREEXE;
    //INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    private String INDOPC;
    //DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    private String DATVEN;
    //FATCOT - FATOR DE COTAÇÃO DO PAPEL
    private String FATCOT;
    //PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
    private String PTOEXE;
    //CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
    private String CODISI;
    //DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
    private String DISMES;

    public String getTIPREG() {
        return TIPREG;
    }

    public void setTIPREG(String TIPREG) {
        this.TIPREG = TIPREG;
    }

    public String getDATAPG() {
        return DATAPG;
    }

    public void setDATAPG(String DATAPG) {
        this.DATAPG = DATAPG;
    }

    public String getCODBDI() {
        return CODBDI;
    }

    public void setCODBDI(String CODBDI) {
        this.CODBDI = CODBDI;
    }

    public String getCODNEG() {
        return CODNEG;
    }

    public void setCODNEG(String CODNEG) {
        this.CODNEG = CODNEG;
    }

    public String getTPMERC() {
        return TPMERC;
    }

    public void setTPMERC(String TPMERC) {
        this.TPMERC = TPMERC;
    }

    public String getNOMRES() {
        return NOMRES;
    }

    public void setNOMRES(String NOMRES) {
        this.NOMRES = NOMRES;
    }

    public String getESPECI() {
        return ESPECI;
    }

    public void setESPECI(String ESPECI) {
        this.ESPECI = ESPECI;
    }

    public String getPRAZOT() {
        return PRAZOT;
    }

    public void setPRAZOT(String PRAZOT) {
        this.PRAZOT = PRAZOT;
    }

    public String getMODREF() {
        return MODREF;
    }

    public void setMODREF(String MODREF) {
        this.MODREF = MODREF;
    }

    public String getPREABE() {
        return PREABE;
    }

    public void setPREABE(String PREABE) {
        this.PREABE = PREABE;
    }

    public String getPREMAX() {
        return PREMAX;
    }

    public void setPREMAX(String PREMAX) {
        this.PREMAX = PREMAX;
    }

    public String getPREMIN() {
        return PREMIN;
    }

    public void setPREMIN(String PREMIN) {
        this.PREMIN = PREMIN;
    }

    public String getPREMED() {
        return PREMED;
    }

    public void setPREMED(String PREMED) {
        this.PREMED = PREMED;
    }

    public String getPREULT() {
        return PREULT;
    }

    public void setPREULT(String PREULT) {
        this.PREULT = PREULT;
    }

    public String getPREOFC() {
        return PREOFC;
    }

    public void setPREOFC(String PREOFC) {
        this.PREOFC = PREOFC;
    }

    public String getPREOFV() {
        return PREOFV;
    }

    public void setPREOFV(String PREOFV) {
        this.PREOFV = PREOFV;
    }

    public String getTOTNEG() {
        return TOTNEG;
    }

    public void setTOTNEG(String TOTNEG) {
        this.TOTNEG = TOTNEG;
    }

    public String getQUATOT() {
        return QUATOT;
    }

    public void setQUATOT(String QUATOT) {
        this.QUATOT = QUATOT;
    }

    public String getVOLTOT() {
        return VOLTOT;
    }

    public void setVOLTOT(String VOLTOT) {
        this.VOLTOT = VOLTOT;
    }

    public String getPREEXE() {
        return PREEXE;
    }

    public void setPREEXE(String PREEXE) {
        this.PREEXE = PREEXE;
    }

    public String getINDOPC() {
        return INDOPC;
    }

    public void setINDOPC(String INDOPC) {
        this.INDOPC = INDOPC;
    }

    public String getDATVEN() {
        return DATVEN;
    }

    public void setDATVEN(String DATVEN) {
        this.DATVEN = DATVEN;
    }

    public String getFATCOT() {
        return FATCOT;
    }

    public void setFATCOT(String FATCOT) {
        this.FATCOT = FATCOT;
    }

    public String getPTOEXE() {
        return PTOEXE;
    }

    public void setPTOEXE(String PTOEXE) {
        this.PTOEXE = PTOEXE;
    }

    public String getCODISI() {
        return CODISI;
    }

    public void setCODISI(String CODISI) {
        this.CODISI = CODISI;
    }

    public String getDISMES() {
        return DISMES;
    }

    public void setDISMES(String DISMES) {
        this.DISMES = DISMES;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\nTIPREG", TIPREG)
                .append("\nDATAPG", DATAPG)
                .append("\nCODBDI", CODBDI)
                .append("\nCODNEG", CODNEG)
                .append("\nTPMERC", TPMERC)
                .append("\nNOMRES", NOMRES)
                .append("\nESPECI", ESPECI)
                .append("\nPRAZOT", PRAZOT)
                .append("\nMODREF", MODREF)
                .append("\nPREABE", PREABE)
                .append("\nPREMAX", PREMAX)
                .append("\nPREMIN", PREMIN)
                .append("\nPREMED", PREMED)
                .append("\nPREULT", PREULT)
                .append("\nPREOFC", PREOFC)
                .append("\nPREOFV", PREOFV)
                .append("\nTOTNEG", TOTNEG)
                .append("\nQUATOT", QUATOT)
                .append("\nVOLTOT", VOLTOT)
                .append("\nPREEXE", PREEXE)
                .append("\nINDOPC", INDOPC)
                .append("\nDATVEN", DATVEN)
                .append("\nFATCOT", FATCOT)
                .append("\nPTOEXE", PTOEXE)
                .append("\nCODISI", CODISI)
                .append("\nDISMES", DISMES)
                .toString();
    }

    public static final class RegistroCotacaoDiariaBuilder {
        //TIPREG - TIPO DE REGISTRO
        private String TIPREG;
        //DATA DO PREGÃO
        private String DATAPG;
        //CODBDI - CÓDIGO BDI
        private String CODBDI;
        //CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
        private String CODNEG;
        //TPMERC - TIPO DE MERCADO
        private String TPMERC;
        //NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
        private String NOMRES;
        //ESPECI - ESPECIFICAÇÃO DO PAPEL
        private String ESPECI;
        //PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
        private String PRAZOT;
        //MODREF - MOEDA DE REFERÊNCIA
        private String MODREF;
        //PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
        private String PREABE;
        //PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
        private String PREMAX;
        //PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
        private String PREMIN;
        //PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
        private String PREMED;
        //PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO
        private String PREULT;
        //PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
        private String PREOFC;
        //PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
        private String PREOFV;
        //TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
        private String TOTNEG;
        //QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
        private String QUATOT;
        //VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
        private String VOLTOT;
        //PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO PARA O MERCADO DE TERMO SECUNDÁRIO
        private String PREEXE;
        //INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
        private String INDOPC;
        //DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
        private String DATVEN;
        //FATCOT - FATOR DE COTAÇÃO DO PAPEL
        private String FATCOT;
        //PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
        private String PTOEXE;
        //CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
        private String CODISI;
        //DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
        private String DISMES;

        public RegistroCotacaoDiariaBuilder() {
        }

        public static RegistroCotacaoDiariaBuilder aRegistroCotacaoDiaria() {
            return new RegistroCotacaoDiariaBuilder();
        }

        public RegistroCotacaoDiariaBuilder withTIPREG(String TIPREG) {
            this.TIPREG = TIPREG;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withDATAPG(String DATAPG) {
            this.DATAPG = DATAPG;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withCODBDI(String CODBDI) {
            this.CODBDI = CODBDI;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withCODNEG(String CODNEG) {
            this.CODNEG = CODNEG;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withTPMERC(String TPMERC) {
            this.TPMERC = TPMERC;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withNOMRES(String NOMRES) {
            this.NOMRES = NOMRES;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withESPECI(String ESPECI) {
            this.ESPECI = ESPECI;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPRAZOT(String PRAZOT) {
            this.PRAZOT = PRAZOT;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withMODREF(String MODREF) {
            this.MODREF = MODREF;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREABE(String PREABE) {
            this.PREABE = PREABE;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREMAX(String PREMAX) {
            this.PREMAX = PREMAX;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREMIN(String PREMIN) {
            this.PREMIN = PREMIN;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREMED(String PREMED) {
            this.PREMED = PREMED;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREULT(String PREULT) {
            this.PREULT = PREULT;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREOFC(String PREOFC) {
            this.PREOFC = PREOFC;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREOFV(String PREOFV) {
            this.PREOFV = PREOFV;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withTOTNEG(String TOTNEG) {
            this.TOTNEG = TOTNEG;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withQUATOT(String QUATOT) {
            this.QUATOT = QUATOT;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withVOLTOT(String VOLTOT) {
            this.VOLTOT = VOLTOT;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPREEXE(String PREEXE) {
            this.PREEXE = PREEXE;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withINDOPC(String INDOPC) {
            this.INDOPC = INDOPC;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withDATVEN(String DATVEN) {
            this.DATVEN = DATVEN;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withFATCOT(String FATCOT) {
            this.FATCOT = FATCOT;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withPTOEXE(String PTOEXE) {
            this.PTOEXE = PTOEXE;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withCODISI(String CODISI) {
            this.CODISI = CODISI;
            return this;
        }

        public RegistroCotacaoDiariaBuilder withDISMES(String DISMES) {
            this.DISMES = DISMES;
            return this;
        }

        public RegistroCotacaoDiariaAcao build() {
            RegistroCotacaoDiariaAcao registroCotacaoDiariaAcao = new RegistroCotacaoDiariaAcao();
            registroCotacaoDiariaAcao.setTIPREG(TIPREG);
            registroCotacaoDiariaAcao.setDATAPG(DATAPG);
            registroCotacaoDiariaAcao.setCODBDI(CODBDI);
            registroCotacaoDiariaAcao.setCODNEG(CODNEG);
            registroCotacaoDiariaAcao.setTPMERC(TPMERC);
            registroCotacaoDiariaAcao.setNOMRES(NOMRES);
            registroCotacaoDiariaAcao.setESPECI(ESPECI);
            registroCotacaoDiariaAcao.setPRAZOT(PRAZOT);
            registroCotacaoDiariaAcao.setMODREF(MODREF);
            registroCotacaoDiariaAcao.setPREABE(PREABE);
            registroCotacaoDiariaAcao.setPREMAX(PREMAX);
            registroCotacaoDiariaAcao.setPREMIN(PREMIN);
            registroCotacaoDiariaAcao.setPREMED(PREMED);
            registroCotacaoDiariaAcao.setPREULT(PREULT);
            registroCotacaoDiariaAcao.setPREOFC(PREOFC);
            registroCotacaoDiariaAcao.setPREOFV(PREOFV);
            registroCotacaoDiariaAcao.setTOTNEG(TOTNEG);
            registroCotacaoDiariaAcao.setQUATOT(QUATOT);
            registroCotacaoDiariaAcao.setVOLTOT(VOLTOT);
            registroCotacaoDiariaAcao.setPREEXE(PREEXE);
            registroCotacaoDiariaAcao.setINDOPC(INDOPC);
            registroCotacaoDiariaAcao.setDATVEN(DATVEN);
            registroCotacaoDiariaAcao.setFATCOT(FATCOT);
            registroCotacaoDiariaAcao.setPTOEXE(PTOEXE);
            registroCotacaoDiariaAcao.setCODISI(CODISI);
            registroCotacaoDiariaAcao.setDISMES(DISMES);
            return registroCotacaoDiariaAcao;
        }
    }
}
