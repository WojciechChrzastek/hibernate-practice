package net.chrzastek.hibernatepractice.taskList;

import net.chrzastek.hibernatepractice.task.Task;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TASKLIST")
public class TaskList {
  private int id;
  private String listName;
  private String description;

  private List<Task> tasks = new ArrayList<>();

  public TaskList(String listName, String description) {
    this.listName = listName;
    this.description = description;
  }

  public TaskList() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @NotNull
  @Column(name = "ID", unique = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "LISTNAME")
  public String getListName() {
    return listName;
  }

  public void setListName(String listName) {
    this.listName = listName;
  }

  @Column(name = "DESCRIPTION")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @OneToMany(
          targetEntity = Task.class,
          mappedBy = "taskList",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}
