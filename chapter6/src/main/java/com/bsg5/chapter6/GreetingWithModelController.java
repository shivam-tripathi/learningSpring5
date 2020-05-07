package com.bsg5.chapter6;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingWithModelController {
    @GetMapping("/greeting/model/{name}")
    public String greeting(@PathVariable(name="name") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greeting/modelMap")
    public String greeting(ModelMap modelMap) {
        modelMap.addAttribute("HelloWorld");
        modelMap.addAttribute("ThreadbareLoaf");
        return "greeting";
    }

    @GetMapping("/greeting/modelAndView")
    public ModelAndView greeting() {
        Map<String, String> model = new HashMap<>();
        model.put("HelloWorld", "HelloWorld");
        model.put("ThreadbareLoaf", "ThreadbareLoaf");
        return new ModelAndView("greeting", model);
    }
}
