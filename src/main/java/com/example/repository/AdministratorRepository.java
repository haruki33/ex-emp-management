package com.example.repository;

import com.example.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Administratorsテーブルを操作するリポジトリ.
 */
@Repository
public class AdministratorRepository {
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        return Administrator.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .mailAddress(rs.getString("mail_address"))
                .password(rs.getString("password"))
                .build();
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 従業員情報
     */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String sql = """
                INSERT INTO
                    administrators ( name, mail_address, password)
                VALUES
                                   (:name, :mailAddress, :password)
                """;

        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return メールアドレスとパスワードが一致した従業員情報　1件も存在しない場合はnullを返す
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = """
                SELECT
                    id, name, mail_address, password
                FROM
                    administrators
                WHERE
                    mail_address = :mailAddress
                    AND
                    password = :password
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);

        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if (administratorList.isEmpty()) {
            return null;
        }
        return administratorList.getFirst();


    }
}
