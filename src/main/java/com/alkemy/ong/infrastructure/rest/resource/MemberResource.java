package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IListMemberUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.common.PaginatedResultsRetrieved;
import com.alkemy.ong.infrastructure.rest.mapper.ListMemberMapper;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.ListMemberResponse;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberResource {

  @Autowired
  private final IDeleteMemberUseCase deleteMemberUseCase;

  private final IListMemberUseCase listMemberUseCase;
  private final ListMemberMapper listMemberMapper;
  private final PaginatedResultsRetrieved resultsRetrieved;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListMemberResponse> list(@PageableDefault(size = 10) Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    Page<Member> resultPage = listMemberUseCase.findAll(pageable);
    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder,
        response,
        "/members",
        resultPage.getNumber(),
        resultPage.getTotalPages(),
        resultPage.getSize()
    );
    ListMemberResponse listMemberResponse = listMemberMapper.toResponse(resultPage);
    return ResponseEntity.ok().body(listMemberResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteMemberUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
