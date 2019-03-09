package net.chrzastek.hibernatepractice.invoice;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEMS")
public class Item {
  private int id;
  private Product product;
  private BigDecimal price;
  private int quantity;
  private BigDecimal value;
  private Invoice invoice;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @NotNull
  @Column(name = "ITEM_ID", unique = true)
  public int getId() {
    return id;
  }

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  public Product getProduct() {
    return product;
  }

  @NotNull
  @Column(name = "PRICE")
  public BigDecimal getPrice() {
    return price;
  }

  @NotNull
  @Column(name = "QUANTITY")
  public int getQuantity() {
    return quantity;
  }

  @NotNull
  @Column(name = "VALUE")
  public BigDecimal getValue() {
    return value;
  }

  @ManyToOne
  @JoinColumn(name = "INVOICE_ID")
  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public Item(BigDecimal price, int quantity, BigDecimal value) {
    this.price = price;
    this.quantity = quantity;
    this.value = value;
  }

  public Item() {
  }
}
