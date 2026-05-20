package com.example.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

/**
 * 管理者情報を表すドメイン.
 */
public class Administrator {
    /**
     * ID
     */
    private Integer id;

    /**
     * 名前
     */
    private String name;

    /**
     * メールアドレス
     */
    private String mailAddress;

    /**
     * パスワード
     */
    private String password;
}
