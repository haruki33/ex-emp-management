package com.example.form;

import lombok.*;

import java.sql.Date;

/**
 * 従業員の扶養人数変更時に使用するフォーム.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateEmployeeForm {
    /**
     * ID
     */
    private String id;

    /**
     * 名前
     */
    private String name;

    /**
     * 性別
     */
    private String gender;

    /**
     * 入社日
     */
    private Date hireDate;

    /**
     * メールアドレス
     */
    private String mailAddress;

    /**
     * 郵便番号
     */
    private String zipCode;

    /**
     * 住所
     */
    private String address;

    /**
     * 電話番号
     */
    private String telephone;

    /**
     * 給料
     */
    private String salary;

    /**
     * 特性
     */
    private String characteristics;

    /**
     * 扶養人数
     */
    private String dependentsCount;
}
