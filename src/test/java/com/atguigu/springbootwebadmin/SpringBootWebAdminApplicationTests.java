package com.atguigu.springbootwebadmin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
@Slf4j
@SpringBootTest
class SpringBootWebAdminApplicationTests {
    @Autowired//自动配置
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
//        jdbcTemplate.queryForObject("select name from employ where id = 2");//查询单个记录
//        jdbcTemplate.queryForList("select name from employ where id = 2");//查询多条记录
        Long aLong = jdbcTemplate.queryForObject("select count(*) from employ", long.class);
        log.info("记录总数{}",aLong);


    }

}
