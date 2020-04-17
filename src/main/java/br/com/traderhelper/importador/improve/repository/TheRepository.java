package br.com.traderhelper.importador.improve.repository;

import br.com.traderhelper.importador.improve.data.StockPriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class TheRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<String> findAllCodneg() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForList("SELECT DISTINCT(CODNEG) FROM DSP WHERE CODBDI = '2' and ESPECI not like ('%DRN%') ORDER BY CODNEG",
                parameters, String.class);
    }

    public List<StockPriceDTO> findAllStockPricesCodneg(String codneg) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("codneg", codneg);
        return namedParameterJdbcTemplate.query("SELECT datapg, codbdi, codneg, tpmerc, nomres, especi, preabe, premax, premin, premed, preult, preofc, preofv, totneg, quatot, voltot, preexe, indopc, datven, fatcot, codisi, dismes FROM dsp WHERE CODNEG = :codneg",
                parameters, new BeanPropertyRowMapper<>(StockPriceDTO.class));

    }



    public List<Integer> findExistentPlusWithoutCategoryNotInPlus(List<String> plus) {
        String query = "SELECT CODPLUPRINC FROM PRODUCT WHERE CATEGORIA IS NULL ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if (!plus.isEmpty()) {
            parameters.addValue("plus", plus);
            query = query + "AND CODPLUPRINC NOT IN (:plus)";
        }
        return namedParameterJdbcTemplate.queryForList(query,
                parameters, Integer.class);
    }

/*    public int updateProducts(List<Product> productList) {
        int[] updated = jdbcTemplate.batchUpdate(
                "UPDATE PRODUCT SET DESCRICAO = ?, URL_IMG = ?, CATEGORIA = ? WHERE CODPLUPRINC = ? ",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, productList.get(i).getDescricao());
                        ps.setString(2, productList.get(i).getUrlImg());
                        ps.setString(3, productList.get(i).getCategoria());
                        ps.setString(4, productList.get(i).getCodpluprinc());
                    }

                    @Override
                    public int getBatchSize() {
                        return productList.size();
                    }
                });
        return IntStream.of(updated).sum();
    }*/
}
