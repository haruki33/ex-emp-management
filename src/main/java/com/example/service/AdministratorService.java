package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者情報を管理するサービス.
 */
@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository repository;

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

    /**
     * ログイン処理をする.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return メールアドレスとパスワードで検索した結果、一致した従業員情報　1件も存在しない場合はnullを返す
     */
    public Administrator login(String mailAddress, String password) {
        return repository.findByMailAddressAndPassword(mailAddress, password);
    }
}
