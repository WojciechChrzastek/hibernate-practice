package net.chrzastek.hibernatepractice.invoice.dao;

import net.chrzastek.hibernatepractice.invoice.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
}
