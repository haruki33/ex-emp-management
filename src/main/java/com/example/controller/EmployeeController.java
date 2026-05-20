package com.example.controller;

import com.example.domain.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員関連機能の処理の制御を行うコントローラー.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * 従業員情報全件表示する.
     *
     * @param model 従業員情報全件を書くのするためのモデル
     * @return 従業員情報一覧画面へフォワード
     */
    @GetMapping("/show-list")
    public String showList(Model model) {
        List<Employee> employeeList = service.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }
}
