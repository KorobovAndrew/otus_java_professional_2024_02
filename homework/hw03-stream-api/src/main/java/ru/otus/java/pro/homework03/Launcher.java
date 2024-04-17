package ru.otus.java.pro.homework03;

import ru.otus.java.pro.homework03.entity.Task;

import java.util.Comparator;
import java.util.List;

import static ru.otus.java.pro.homework03.entity.Status.*;
import static ru.otus.java.pro.homework03.entity.Status.IN_PROGRESS;

public class Launcher {
    public void launch() {
        var tasks = preparingTaskList();

        //Получение списка задач по выбранному статусу
        var todoTasks = tasks.stream().filter(task -> task.getStatus().equals(TODO)).toList();

        //Проверка наличия задачи с указанным ID;
        var isIdFiveTaskExist = tasks.stream().anyMatch(task -> task.getId().equals(5L));

        //Получение списка задач в отсортированном по статусу виде:
        // открыта, в работе, закрыта (можете выбирать любой статус и любой порядок,
        // главное чтобы было 3 разных статуса);
        var sortedTasks = tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .toList();

        //Подсчет количества задач по определенному статусу.
        var inProgressTasksCount = tasks.stream()
                .filter(task -> task.getStatus().equals(IN_PROGRESS))
                .count();
    }

    private List<Task> preparingTaskList() {
        List<Task> tasks = List.of(
                new Task(1L, "Купить машину", TODO),
                new Task(2L, "Сделать домашку по stream API", IN_PROGRESS),
                new Task(3L, "Купить квартиру", TODO),
                new Task(4L, "Закончить школу", CLOSED),
                new Task(5L, "Сделать уборку", OPEN),
                new Task(6L, "Скачать IDE", CLOSED),
                new Task(7L, "То сё пятое десятое", IN_PROGRESS),
                new Task(8L, "Стать синьором", IN_PROGRESS)
        );
        return tasks;
    }
}
