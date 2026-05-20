package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者関連機能の処理の制御を行うコントローラー.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService service;
    @Autowired
    private HttpSession session;

    /**
     * login.htmlにフォワードする.
     *
     * @param form ログイン時に使用するフォーム
     * @return administrator/login.htmlにフォワード
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * insert.htmlにフォワードする.
     *
     * @param form 管理者情報登録時に使用するフォーム
     * @return administrator/insert.htmlにフォワード
     */
    @GetMapping("/to-insert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者登録（insert）を行う.
     *
     * @param form 管理者登録に使うフォーム
     * @return ログイン画面にリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        ModelMapper modelMapper = new ModelMapper();
        Administrator administrator = modelMapper.map(form, Administrator.class);
        service.insert(administrator);

        return "redirect:/";
    }

    /**
     * メールアドレスとパスワードでログインする.
     *
     * @param form  管理者登録で使用するフォーム
     * @param model エラーメッセージを格納するためのモデル
     * @return ログイン成功時は従業員一覧へ、失敗時はログイン画面へ遷移
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = service.login(form.getMailAddress(), form.getPassword());

        if (administrator == null) {
            model.addAttribute("errorMsg", "メールアドレスまたはパスワードが不正です。");
            return toLogin(form);
        }

        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/show-list";

    }
}
