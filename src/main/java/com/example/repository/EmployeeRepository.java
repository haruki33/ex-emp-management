package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * employeesテーブルを操作するリポジトリ
 */
@Repository
public class EmployeeRepository {
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        return Employee.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .image(rs.getString("image"))
                .gender(rs.getString("gender"))
                .hireDate(rs.getDate("hire_date"))
                .mailAddress(rs.getString("mail_address"))
                .zipCode(rs.getString("zip_code"))
                .address(rs.getString("address"))
                .telephone(rs.getString("telephone"))
                .salary((Integer) rs.getObject("salary"))
                .characteristics(rs.getString("characteristics"))
                .dependentsCount((Integer) rs.getObject("dependents_count"))
                .build();
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 従業員一覧情報を入社日順（降順）で取得する.
     *
     * @return 従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返す
     */
    public List<Employee> findAll() {
        String sql = """
                SELECT
                    id
                    , name
                    , image
                    , gender
                    , hireDate
                    , mailAddress
                    , zipCode
                    , address
                    , telephone
                    , salary
                    , characteristics
                    , dependentsCount
                FROM
                    employees
                ORDER BY
                    hireDate DESC
                """;
        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 主キーから従業員情報を取得する.
     *
     * @param id ID
     * @return 従業員情報
     */
    public Employee findById(Integer id) {
        String sql = """
                SELECT
                    id
                ,   name
                ,   image
                ,   gender
                ,   hire_date
                ,   mail_address
                ,   zip_code
                ,   address
                ,   telephone
                ,   salary
                ,   characteristics
                ,   dependents_count
                FROM
                    employees
                WHERE
                    id = :id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 従業員情報を変更する.
     * <p>
     * idカラムを除いた従業員情報の全てのカラムを更新できる。
     *
     * @param employee 従業員情報
     */
    public void Update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String sql = """
                UPDATE
                    employees
                SET
                    name =:name
                ,   image = :image
                ,   gender = :gender
                ,   hire_date = :hireDare
                ,   mail_address = :mail_address
                ,   zip_code = :zipCode
                ,   address = :address
                ,   telephone = :telephone
                ,   salary = :salary
                ,   characteristics = :characteristics
                ,   dependents_count =:dependentsCount
                WHERE
                    id = :id
                """;

        template.update(sql, param);
    }
}
