/*
package br.com.traderhelper.importador.improve.writer;

import br.com.traderhelper.importador.improve.data.DailyStockPriceDTO;
import lombok.extern.slf4j.Slf4j;
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
public class PromotionWriter implements ItemWriter<DailyStockPriceDTO> {

    private static final String DELETE_FROM_PROMOTION = "DELETE FROM PROMOTION WHERE IDPROMOCAO = ? AND IDPROGRAMA = ?";

    @Autowired
    private DataSource dataSource;

    @Override
    public void write(List<? extends DailyStockPriceDTO> expiredPromotions) throws Exception {
        JdbcBatchItemWriter<DailyStockPriceDTO> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setJdbcTemplate(new NamedParameterJdbcTemplate(this.dataSource));
        writer.setSql(DELETE_FROM_PROMOTION);
        writer.setItemPreparedStatementSetter((promotionOld, ps) -> {
            ps.setString(1, promotionOld.getIdpromocao());
            ps.setString(2, promotionOld.getIdprograma());
        });
        writer.afterPropertiesSet();
        try {
            writer.write(expiredPromotions);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Registro não encontrado:" + e.getMessage());
        }
    }
}

*/
