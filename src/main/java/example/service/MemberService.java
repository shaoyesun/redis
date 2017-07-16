package example.service;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import example.dao.MemberDao;
import example.entity.Member;

/**
 * Created by root on 17-3-17.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public String save() {
        Member member = new Member();
        member.setPassword("test0");
        member.setUsername("test0");
        Serializable id = memberDao.save(member);
        return JSONObject.fromObject(memberDao.get(id)).toString();
    }

    public String update(Member member) {
        Serializable id = memberDao.save(member);
        return JSONObject.fromObject(memberDao.get(id)).toString();
    }

    public Member getOne(Long id) {
        return memberDao.getOne(id);
    }

    public List<Member> findAll() {
        return memberDao.findAll();
    }

}
