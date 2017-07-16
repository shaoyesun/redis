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
 * @缓存测试
 * @ 1.缓存添加(@Cacheable)
 * @ 2.缓存一致性(@CacheEvict @CachePut)
 * @ 3.缓存key值重名(jpel)
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //添加数据时清除缓存，保证数据的一致性
    @RequestMapping(value = "/save")
    @ResponseBody
    @CacheEvict(value = "common", key = "'class example.controller.MemberController.findAll'")//清除缓存
    public String save() {
        return memberService.save();
    }

    @RequestMapping(value = "/findAll")
    @ResponseBody
    @Cacheable(value = "common", key = "#root.targetClass + '.' + #root.methodName")
    public String findAll() {
        List<Member> list = memberService.findAll();
        return JSONArray.fromObject(list).toString();
    }

    @RequestMapping(value = "/getOne")
    @ResponseBody
    @Cacheable(value = "common", key = "#root.targetClass + '.' + #root.methodName + '?id=' + #id")
    public String getOne(Long id) {
        Member m = memberService.getOne(id);
        return JSONObject.fromObject(m).toString();
    }

    //更新数据时，更新缓存
    @RequestMapping(value = "/update")
    @ResponseBody
    @CachePut(value = "common", key = "'class example.controller.MemberController.getOne?id=' + #id")
    public String update(Long id) {
        Member m = memberService.getOne(id);
        m.setUsername("test_update");
        return JSONObject.fromObject(memberService.update(m)).toString();
    }

}
