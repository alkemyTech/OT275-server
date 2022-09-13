package com.alkemy.ong.application.service.member.usecase;

import com.alkemy.ong.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IListMemberUseCase {

  Page<Member> findAll(Pageable pageable);

}
