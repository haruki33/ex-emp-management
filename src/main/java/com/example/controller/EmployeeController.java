package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * @param model モデル
     * @return 従業員情報一覧画面
     */
    @GetMapping("/show-list")
    public String showList(Model model) {
        List<Employee> employeeList = service.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }

    /**
     * 従業員詳細画面を表示.
     *
     * @param id    ID
     * @param model モデル
     * @param form  フォーム
     * @return 詳細画面
     */
    @GetMapping("/show-detail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        Employee employee = service.showDetail(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        return "/employee/detail";
    }

    /**
     * 扶養人数を変更する.
     *
     * @param form フォーム
     * @return 従業員一覧
     */
    @PostMapping("/update")
    public String update(UpdateEmployeeForm form) {
        service.UpdateDependentsCount(
                Integer.parseInt(form.getId()),
                Integer.parseInt(form.getDependentsCount())
        );
        return "redirect:/employee/show-list";
    }
}
