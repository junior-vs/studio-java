package com.demo.estoque.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter {

  private String pattern = "dd/MM/yyyy";

  @Override
  public Object unmarshal(Object dateString) throws Exception {
    return new SimpleDateFormat(pattern).parse((String) dateString);
  }

  @Override
  public Object marshal(Object date) throws Exception {
    return new SimpleDateFormat(pattern).format(date);
  }
}
