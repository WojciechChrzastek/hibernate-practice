package net.chrzastek.hibernatepractice.invoice.dao;

import net.chrzastek.hibernatepractice.invoice.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ItemDao extends CrudRepository<Item, Integer> {
}
