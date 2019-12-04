package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("deparments", departmentRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }


     //**department
    @GetMapping("/addDepartment")
    public String departmentForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }

    @PostMapping("/processDepartment")
    public String processDept(@Valid @ModelAttribute Department department, BindingResult result){
        if(result.hasErrors()){
            return "departmentform";
        }
        departmentRepository.save(department);
        return "redirect:/";
    }

    //Employee****
    @GetMapping("/addEmployee")
    public String employeeForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeform";
    }

    @PostMapping("/processEmployee")
    public String processEmp(@Valid @ModelAttribute Employee employee, BindingResult result,
                             @RequestParam("departmentid") long departmentid){
        if(result.hasErrors()){
            return "employeeform";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }
}
