package com.example.form;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "氏名を入力してください")
    private String name;

    /**
     * メールアドレス
     */
    @NotBlank(message = "氏名を入力してください")
    private String mailAddress;

    /**
     * パスワード
     */
    @NotBlank(message = "氏名を入力してください")
    private String password;
}
