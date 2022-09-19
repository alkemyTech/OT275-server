package com.alkemy.ong.bigtest.members;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alkemy.ong.bigtest.BigTest;

import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import java.util.Optional;

public class DeleteMembersIntegrationTest extends BigTest {


  private void assertMemberHasBeenDeleted(Long randomMemberId) {
    Optional<MemberEntity> member = memberRepository.findById(randomMemberId);
    assertTrue(member.isPresent());
    assertTrue(member.get().isSoftDeleted());
  }
}
