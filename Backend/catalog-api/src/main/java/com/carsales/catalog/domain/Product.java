package com.carsales.catalog.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Document(collection = "products")
public class Product {
  @Id
  private String id;

  @NotNull
  @Indexed
  private String categoryId;

  @NotBlank private String brand;
  @NotBlank private String model;
  private String version;

  @NotNull @Min(1886)
  private Integer year;

  @NotBlank private String color;

  @NotNull @Min(0)
  private BigDecimal listedPrice;

  private List<String> photos;

  // status: available|reserved|sold 
  @NotBlank
  private String status = "available";

  private Instant createdAt = Instant.now();
  private Instant updatedAt = Instant.now();

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getCategoryId() { return categoryId; }
  public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
  public String getBrand() { return brand; }
  public void setBrand(String brand) { this.brand = brand; }
  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }
  public String getVersion() { return version; }
  public void setVersion(String version) { this.version = version; }
  public Integer getYear() { return year; }
  public void setYear(Integer year) { this.year = year; }
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }
  public BigDecimal getListedPrice() { return listedPrice; }
  public void setListedPrice(BigDecimal listedPrice) { this.listedPrice = listedPrice; }
  public List<String> getPhotos() { return photos; }
  public void setPhotos(List<String> photos) { this.photos = photos; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
  public Instant getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
