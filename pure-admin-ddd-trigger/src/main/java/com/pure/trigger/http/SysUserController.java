package com.pure.trigger.http;

import com.pure.domain.user.repository.ISysUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/sysUser")
@RestController
public class SysUserController {

    @Resource
    public ISysUserRepository<Object,Long> sysUserRepository;

    @GetMapping("/getUserById/{id}")
    public Object getUser(@PathVariable("id") Long id) {
        return sysUserRepository.selectById(id).orElse(null);
    }

    @GetMapping("/getUserByName/{name}")
    public Object getUserByName(@PathVariable("name") String name) {
        return sysUserRepository.getSysUserByUsername(name);
    }
}
