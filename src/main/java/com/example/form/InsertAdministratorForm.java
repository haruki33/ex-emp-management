package com.example.form;

import lombok.*;

/**
 * 管理者情報登録時に使用するフォーム.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
