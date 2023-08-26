package com.atguigu.springbootwebadmin.controller;

import com.atguigu.springbootwebadmin.bean.Employ;
import com.atguigu.springbootwebadmin.bean.User;
import com.atguigu.springbootwebadmin.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

//建立模板引擎来管理要转到的页面，页面核心控制单元
@Controller
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EmployService employService;
    @ResponseBody//响应数据，但不跳转
    @GetMapping("/emp")
    public Employ getById(@RequestParam("id") Long id){

        return  employService.getEmpByid(id);
    }

    @ResponseBody//数据响应出去，不跳转
    @GetMapping("/sql")
    public String queryFromDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from employ", long.class);
        return aLong.toString();
    }
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";//thymeleaf去掉前缀和后缀，只需要名字即可
    }

    @PostMapping("/login") //默认是转发，即转发到main页面，刷新是还是从login里面重新转发，这里可以做个定向的main页面
    public String main(User user, HttpSession session,Model model){ //处理登录的请求,使用User中定义好的登录，登录成功后存到session里面
        if(StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())) {
            session.setAttribute("loginUser",user);//登录成功就把这个用户保存起来，并重定向到main页面
            return "redirect:/main.html";//登录成功就重定向到这个main.html,通过重定向来防止表单重复提交
        }else{
            model.addAttribute("msg","账号密码错误");//model里面提示账号密码错误
            return "login";//登录不成功回到登录页
        }
    }
    @GetMapping("/main.html")//真正的去main页面
    public String mainPage(HttpSession session,Model model){
        //这个页面必须登录以后才能显示，所以要做个判断，登陆了才能进入，要不转不进来（拦截器或过滤器等）
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){ //登录成功
            return "main";
        }else {
            model.addAttribute("msg","请重新登录");
            return "login";
        }

    }
}
