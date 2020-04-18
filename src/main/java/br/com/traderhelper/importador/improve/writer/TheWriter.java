package br.com.traderhelper.importador.improve.writer;

import br.com.traderhelper.importador.improve.data.DailyStockPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Component
public class TheWriter implements ItemWriter<DailyStockPriceDTO> {

    private static final String INSERT = "INSERT INTO dsp (tipreg, datapg, codbdi, codneg, tpmerc, nomres, especi, prazot, modref, preabe, premax, premin, premed, preult, preofc, preofv, totneg, quatot, voltot, preexe, indopc, datven, fatcot, ptoexe, codisi, dismes) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT ON CONSTRAINT dsp_pk DO NOTHING ";
    @Autowired
    private DataSource dataSource;
    private JdbcBatchItemWriter<DailyStockPriceDTO> writer;

    @BeforeStep
    public void beforeStep() {
        writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        writer.setSql(INSERT);
        writer.setItemPreparedStatementSetter((dsp, ps) -> {
            ps.setString(1, dsp.getTIPREG());
            ps.setDate(2, java.sql.Date.valueOf(dsp.getDATAPG()));
            ps.setString(3, dsp.getCODBDI());
            ps.setString(4, dsp.getCODNEG());
            ps.setString(5, dsp.getTPMERC());
            ps.setString(6, dsp.getNOMRES());
            ps.setString(7, dsp.getESPECI());
            ps.setInt(8, dsp.getPRAZOT().intValue());
            ps.setString(9, dsp.getMODREF());
            ps.setBigDecimal(10, dsp.getPREABE());
            ps.setBigDecimal(11, dsp.getPREMAX());
            ps.setBigDecimal(12, dsp.getPREMIN());
            ps.setBigDecimal(13, dsp.getPREMED());
            ps.setBigDecimal(14, dsp.getPREULT());
            ps.setBigDecimal(15, dsp.getPREOFC());
            ps.setBigDecimal(16, dsp.getPREOFV());
            ps.setInt(17, dsp.getTOTNEG().intValue());
            ps.setInt(18, dsp.getQUATOT().intValue());
            ps.setBigDecimal(19, dsp.getVOLTOT());
            ps.setBigDecimal(20, dsp.getPREEXE());
            ps.setString(21, dsp.getINDOPC());
            ps.setString(22, dsp.getDATVEN());
            ps.setString(23, dsp.getFATCOT());
            ps.setString(24, dsp.getPTOEXE());
            ps.setString(25, dsp.getCODISI());
            ps.setString(26, dsp.getDISMES());
        });
    }

    @Override
    public void write(List<? extends DailyStockPriceDTO> dsp) {
        //log.info("Write");
        try {
            writer.write(dsp);
        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            log.error("Erro ao persistir: ", e);
        }
    }
}
