package net.chrzastek.hibernatepractice.task.dao;

import net.chrzastek.hibernatepractice.task.Task;
import net.chrzastek.hibernatepractice.task.TaskFinancialDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDaoTestSuite {
  @Autowired
  private TaskDao taskDao;
  private static final String DESCRIPTION = "Test: Learn Hibernate";

  @Test
  public void testTaskDaoSave() {
    //Given
    Task task = new Task(DESCRIPTION, 7);

    //When
    taskDao.save(task);

    //Then
    int id = task.getId();

//  Don't know how to access method if variable type is Optional:
//  Optional<Task> readTask = taskDao.findById(id);

    Task readTask = taskDao.findById(id).orElse(null);

    if (readTask != null) {
      Assert.assertEquals(id, readTask.getId());
    }

    //CleanUp
    taskDao.deleteById(id);
  }

  @Test
  public void testTaskDaoFindByDuration() {
    //Given
    Task task = new Task(DESCRIPTION, 7);
    taskDao.save(task);
    int duration = task.getDuration();

    //When
    List<Task> readTasks = taskDao.findByDuration(duration);

    //Then
    Assert.assertEquals(1, readTasks.size());

    //CleanUp
    int id = readTasks.get(0).getId();
    taskDao.deleteById(id);
  }

  @Test
  public void testTaskDaoSaveWithFinancialDetails() {
    //Given
    Task task = new Task(DESCRIPTION, 30);
    task.setTaskFinancialDetails(new TaskFinancialDetails(new BigDecimal(120), false));

    //When
    taskDao.save(task);
    int id = task.getId();

    //Then
    Assert.assertNotEquals(0, id);

    //CleanUp
    taskDao.deleteById(id);
  }
}
