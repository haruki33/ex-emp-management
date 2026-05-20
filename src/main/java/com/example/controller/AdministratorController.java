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
     * ログイン画面を表示する.
     *
     * @param form フォーム
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * インサート画面にフォワードする.
     *
     * @param form フォーム
     * @return インサート画面
     */
    @GetMapping("/to-insert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者登録を行う.
     *
     * @param form フォーム
     * @return ログイン画面
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
     * @param form  フォーム
     * @param model モデル
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

    /**
     * ログアウトする.
     *
     * @return ログイン画面
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
