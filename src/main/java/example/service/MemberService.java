package example.service;

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
        memberDao.get(id);
        Member member1 = new Member();
        member1.setPassword("test1");
        member1.setUsername("test1");
        List<Member> list = new ArrayList<Member>();
        list.add(member);
        list.add(member1);
        memberDao.saves(list);
        return "success";
        //return memberDao.save(member).toString();
    }

    public Member getOne(Long id) {
        return memberDao.getOne(id);
    }

    public List<Member> findAll() {
        return memberDao.findAll();
    }

}
