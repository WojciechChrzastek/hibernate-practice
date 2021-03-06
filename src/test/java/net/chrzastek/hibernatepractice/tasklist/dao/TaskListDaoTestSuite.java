package net.chrzastek.hibernatepractice.tasklist.dao;

import net.chrzastek.hibernatepractice.task.Task;
import net.chrzastek.hibernatepractice.task.TaskFinancialDetails;
import net.chrzastek.hibernatepractice.task.dao.TaskDao;
import net.chrzastek.hibernatepractice.taskList.TaskList;
import net.chrzastek.hibernatepractice.taskList.dao.TaskListDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListDaoTestSuite {
  @Autowired
  TaskListDao taskListDao;

  @Autowired
  TaskDao taskDao;
  private static String LISTNAME = "ToDo List";

  @Test
  public void testFindByListName() {
    //Given

    TaskList taskList = new TaskList("listN", "desc");
    taskListDao.save(taskList);
    String listName = taskList.getListName();

    //When
    List<TaskList> readTaskList = taskListDao.findByListName(listName);

    //Then
    Assert.assertEquals(1, readTaskList.size());

    //CleanUp
    int id = readTaskList.get(0).getId();
    taskListDao.deleteById(id);
  }

  @Test
  public void testTaskListDaoSaveWithTasks() {
    //Given
    Task task = new Task("Test: Learn Hibernate", 14);
    Task task2 = new Task("Test: Write some entities", 3);
    TaskFinancialDetails tfd = new TaskFinancialDetails(new BigDecimal(20), false);
    TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
    task.setTaskFinancialDetails(tfd);
    task2.setTaskFinancialDetails(tfd2);
    TaskList taskList = new TaskList("ToDo list", "ToDo tasks");
    taskList.getTasks().add(task);
    taskList.getTasks().add(task2);
    task.setTaskList(taskList);
    task2.setTaskList(taskList);

    //When
    taskListDao.save(taskList);
    int id = taskList.getId();

    //Then
    Assert.assertNotEquals(0, id);

    //CleanUp
    taskListDao.deleteById(id);
  }

  @Test
  public void testNamedQueries() {
    //Given
    Task task1 = new Task("Test: Study Hibernate", 3);
    Task task2 = new Task("Test: Practice Named Queries", 6);
    Task task3 = new Task("Test: Study native queries", 7);
    Task task4 = new Task("Test: Makse some tests", 13);

    TaskFinancialDetails tfd1 = new TaskFinancialDetails(new BigDecimal(5), false);
    TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
    TaskFinancialDetails tfd3 = new TaskFinancialDetails(new BigDecimal(20), false);
    TaskFinancialDetails tfd4 = new TaskFinancialDetails(new BigDecimal(15), false);

    task1.setTaskFinancialDetails(tfd1);
    task2.setTaskFinancialDetails(tfd2);
    task3.setTaskFinancialDetails(tfd3);
    task4.setTaskFinancialDetails(tfd4);

    TaskList taskList = new TaskList(LISTNAME, "ToDo tasks");
    taskList.getTasks().add(task1);
    taskList.getTasks().add(task2);
    taskList.getTasks().add(task3);
    taskList.getTasks().add(task4);

    task1.setTaskList(taskList);
    task2.setTaskList(taskList);
    task3.setTaskList(taskList);
    task4.setTaskList(taskList);

    taskListDao.save(taskList);
    int id = taskList.getId();

    //When
    List<Task> longTasks = taskDao.retrieveLongTasks();
    List<Task> shortTasks = taskDao.retrieveShortTasks();
    List<Task> enoughTimeTasks = taskDao.retrieveTasksWithEnoughTime();
    List<Task> durationLongerThanTasks = taskDao.retrieveTasksWithDurationLongerThan(6);


    //Then
    try {
      Assert.assertEquals(1, longTasks.size());
      Assert.assertEquals(3, shortTasks.size());
      Assert.assertEquals(3, enoughTimeTasks.size());
      Assert.assertEquals(2, durationLongerThanTasks.size());
    } finally {
      //CleanUp
      taskListDao.deleteById(id);
    }
  }
}
