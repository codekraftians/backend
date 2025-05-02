package com.backend.backend.model;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.backend.backend.model.Event;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @SequenceGenerator(name = "category_id_sequence", sequenceName = "category_id_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id")

  private Integer id;

  @Column(unique = true)
  @NotBlank(message = "[ERROR!] El campo de categoria no puede estar vacio y tampoco tener espacios")
  @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "[ERROR!] No está permitido el uso de caracteres especiales")
  @Size(max = 20, message = "[ERROR!] Máximo de 20 caracteres permitidos en este campo")
  private String category;

  public enum CategoryType {
    ONLINE, IN_PERSON
  }

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CategoryType categoryType;

  public Category() {
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public CategoryType getCategoryType() {
    return this.categoryType;
  }

  public void setCategoryType(CategoryType categoryType) {
    this.categoryType = categoryType;
  }

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Event> events;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Category category = (Category) o;
    return id.equals(category.id) && categoryType == category.categoryType;
  }

}
