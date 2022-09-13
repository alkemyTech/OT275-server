package com.alkemy.ong.infrastructure.rest.response.slide;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ListSlideResponse {

  private List<SlideResponse> slides;

}
