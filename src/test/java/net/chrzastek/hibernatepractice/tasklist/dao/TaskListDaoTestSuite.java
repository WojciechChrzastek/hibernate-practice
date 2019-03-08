package net.chrzastek.hibernatepractice.tasklist.dao;

import net.chrzastek.hibernatepractice.task.dao.TaskDao;
import net.chrzastek.hibernatepractice.taskList.TaskList;
import net.chrzastek.hibernatepractice.taskList.dao.TaskListDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListDaoTestSuite {
  @Autowired
  TaskListDao taskListDao;

  @Autowired
  TaskDao taskDao;

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
}
