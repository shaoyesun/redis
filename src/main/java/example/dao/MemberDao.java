package example.dao;

import org.springframework.stereotype.Repository;

import example.entity.Member;

/**
 * Created by root on 17-3-17.
 */
@Repository
public class MemberDao extends BaseDao<Member, Long> {

}
