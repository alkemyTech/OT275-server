package com.alkemy.ong.common;

import com.alkemy.ong.domain.Slide;

public class SlideMother {

  public static Slide build(String imageUrl, String text, Integer order, String base64fileEncoded) {
    return new Slide(null, imageUrl, order, text, base64fileEncoded);
  }

  public static Slide random() {
    return new Slide(
        null,
        null,
        IntegerMother.random(),
        "",
        "");
  }

  public static Slide withNullOrder() {
    return new Slide(
        null,
        null,
        null,
        "test",
        "");
  }

}
