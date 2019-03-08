package net.chrzastek.hibernatepractice.task.dao;

import net.chrzastek.hibernatepractice.task.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TaskDao extends CrudRepository<Task, Integer> {
  List<Task> findByDuration(int duration);
}