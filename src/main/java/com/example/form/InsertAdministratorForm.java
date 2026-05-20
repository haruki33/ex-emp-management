package com.example.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

/**
 * 管理者情報登録時に使用するフォーム.
 */
public class InsertAdministratorForm {
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
