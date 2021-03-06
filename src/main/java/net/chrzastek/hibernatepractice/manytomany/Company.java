package net.chrzastek.hibernatepractice.manytomany;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "Company.retrieveCompaniesWithGivenName",
        query = "SELECT * FROM COMPANIES" +
                " WHERE COMPANY_NAME LIKE CONCAT(:PARAMETER, '%')",
        resultClass = Company.class
)

//ALTERNATIVELY -> " WHERE COMPANY_NAME LIKE SUBSTRING(:SIGNS, 1, 4)",

@Entity
@Table(name = "COMPANIES")
public class Company {
  private int id;
  private String name;

  private List<Employee> employees = new ArrayList<>();

  public Company() {
  }

  public Company(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @NotNull
  @Column(name = "COMPANY_ID", unique = true)
  public int getId() {
    return id;
  }

  @NotNull
  @Column(name = "COMPANY_NAME")
  public String getName() {
    return name;
  }

  private void setId(int id) {
    this.id = id;
  }

  private void setName(String name) {
    this.name = name;
  }

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companies")
  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
}
