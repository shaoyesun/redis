package example.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import example.entity.Member;
import example.service.MemberService;

/**
 * Created by root on 17-7-15.
 */
@Controller
@RequestMapping(value = "/key")
public class KeyController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    @Cacheable(value="common", key = "#root.targetClass + '.' + #root.methodName")
    public String findAll() {
        Member m = memberService.getOne(6l);
        return JSONObject.fromObject(m).toString();
    }

}
