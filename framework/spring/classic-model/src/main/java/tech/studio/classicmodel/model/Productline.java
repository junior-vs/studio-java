package tech.studio.classicmodel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "productlines")
public class Productline {

  @Id
  @Column(name = "productLine", nullable = false, length = 50)
  private String id;

  @Column(name = "textDescription", length = 4000)
  private String textDescription;

  @Lob
  @Column(name = "htmlDescription")
  private String htmlDescription;

  @Column(name = "image")
  private byte[] image;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTextDescription() {
    return textDescription;
  }

  public void setTextDescription(String textDescription) {
    this.textDescription = textDescription;
  }

  public String getHtmlDescription() {
    return htmlDescription;
  }

  public void setHtmlDescription(String htmlDescription) {
    this.htmlDescription = htmlDescription;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

}