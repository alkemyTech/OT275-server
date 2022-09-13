package com.alkemy.ong.infrastructure.rest.response.contact;

import com.alkemy.ong.infrastructure.rest.response.contact.GetContactResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListContactResponse {

  private List<GetContactResponse> contacts;

}
