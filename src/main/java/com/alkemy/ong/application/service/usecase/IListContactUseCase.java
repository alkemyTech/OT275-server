package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Contact;
import java.util.List;

public interface IListContactUseCase {

  List<Contact> findAll();

}
