package net.chrzastek.hibernatepractice.manytomany.dao;

import net.chrzastek.hibernatepractice.manytomany.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CompanyDao extends CrudRepository<Company, Integer> {
}
