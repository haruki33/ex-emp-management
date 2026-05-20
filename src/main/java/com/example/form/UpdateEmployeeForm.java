package com.example.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

/**
 * 従業員の扶養人数変更時に使用するフォーム.
 */
public class UpdateEmployeeForm {
    /**
     * id
     */
    private String id;

    /**
     * 扶養人数
     */
    private String dependentsCount;
}
