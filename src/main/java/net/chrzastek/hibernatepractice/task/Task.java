package net.chrzastek.hibernatepractice.task;

import net.chrzastek.hibernatepractice.taskList.TaskList;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TASKS")
public class Task {
  private int id;
  private String description;
  private Date created;
  private int duration;
  private TaskFinancialDetails taskFinancialDetails;

  private TaskList taskList;


  public Task() {
  }

  public Task(String description, int duration) {
    this.description = description;
    this.created = new Date();
    this.duration = duration;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @NotNull
  @Column(name = "ID", unique = true)
  public int getId() {
    return id;
  }

  @Column(name = "DESCRIPTION")
  public String getDescription() {
    return description;
  }

  @NotNull
  @Column(name = "CREATED")
  public Date getCreated() {
    return created;
  }

  @Column(name = "DURATION")
  public int getDuration() {
    return duration;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "TASK_FINANCIALS_ID")
  public TaskFinancialDetails getTaskFinancialDetails() {
    return taskFinancialDetails;
  }

  public void setTaskFinancialDetails(TaskFinancialDetails taskFinancialDetails) {
    this.taskFinancialDetails = taskFinancialDetails;
  }

  @ManyToOne
  @JoinColumn(name = "TASKLIST_ID")
  public TaskList getTaskList() {
    return taskList;
  }

  public void setTaskList(TaskList taskList) {
    this.taskList = taskList;
  }
}
