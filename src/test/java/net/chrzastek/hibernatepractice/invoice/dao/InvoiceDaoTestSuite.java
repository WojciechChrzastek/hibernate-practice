package net.chrzastek.hibernatepractice.invoice.dao;

import net.chrzastek.hibernatepractice.invoice.Invoice;
import net.chrzastek.hibernatepractice.invoice.Item;
import net.chrzastek.hibernatepractice.invoice.Product;git
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {

  @Autowired
  ItemDao itemDao;

  @Autowired
  ProductDao productDao;

  @Autowired
  InvoiceDao invoiceDao;

  @Test
  public void testInvoiceDaoSave() {
    //Given
    Product product1 = new Product("product1");
    Product product2 = new Product("product2");
    Item item = new Item(BigDecimal.valueOf(1), 1, BigDecimal.valueOf(1));
    item.setProduct(product1);
    item.setProduct(product2);
    Invoice invoice = new Invoice("1");
    invoice.getItems().add(item);

    //When
    productDao.save(product1);
    productDao.save(product2);
    invoiceDao.save(invoice);

    //Then
    Assert.assertNotEquals(0, invoice.getId());
    Assert.assertNotEquals(0, item.getId());

    //CleanUp
    invoiceDao.deleteById(invoice.getId());
    itemDao.deleteById(item.getId());
    productDao.deleteById(product1.getId());
    productDao.deleteById(product2.getId());
  }
}
