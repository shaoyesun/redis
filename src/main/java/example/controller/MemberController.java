package example.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import example.entity.Member;
import example.service.MemberService;

/**
 * 1.spring事务管理实现
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/save")
    @ResponseBody
    @CacheEvict(value = "{findAll}")//清除缓存
    //@CachePut(value = "{findAll}")//更新缓存
    public String save() {
        return memberService.save();
    }

    @RequestMapping(value = "/getOne")
    @ResponseBody
    @Cacheable(value = "common", key = "#root.targetClass + '.' + #root.methodName + '?id=' + #id")
    //@CacheEvict(value = "common")
    public String getOne(Long id) {
        Member m = memberService.getOne(id);
        return JSONObject.fromObject(m).toString();
    }

    @RequestMapping(value = "/findAll")
    @ResponseBody
    @Cacheable(value = "common", key = "#root.targetClass + '.' + #root.methodName")
    //@CacheEvict(value="common", key = "'findAll'")
    public String findAll() {
        List<Member> list = memberService.findAll();
        return JSONArray.fromObject(list).toString();
    }

    public static void main(String[] args) {
        System.out.println(MemberController.class);
    }

}
