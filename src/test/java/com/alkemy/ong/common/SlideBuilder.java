package com.alkemy.ong.common;

import com.alkemy.ong.domain.Slide;

public class SlideBuilder {

  public static Slide random() {
    return new Slide(null, null, 1, "", "", "image/jpeg");
  }

  public static Slide withNullOrder() {
    return new Slide(null, null, null, "test", "", "image/jpeg");
  }

}
