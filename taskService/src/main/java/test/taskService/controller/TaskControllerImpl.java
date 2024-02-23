package test.taskService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.taskService.service.TaskService;
import test.taskService.dto.Task;

import java.util.List;

@RestController
@RequestMapping("/tasks") // должен обязательно совпадать с интерфейсом
public class TaskControllerImpl implements TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        String updateTask = this.taskService.getTaskById(id).getName();
        taskService.updateTask(id, task);
        return ResponseEntity.ok(String.format("Задача \"%s\" обновлена", updateTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "id") Long id) {
        String delTaskName = taskService.getTaskById(id).getName();
        this.taskService.deleteTask(id);
        return ResponseEntity.ok(String.format("Задача \"%s\" удалена", delTaskName));
    }

    @PostMapping
    public ResponseEntity<String> registerTask(@RequestBody Task task) {
        taskService.registerTask(task);
        return ResponseEntity.ok(String.format("Задача  \"%s\" успешно зарегистрирована", task.getName()));
    }

    /**
     * Оставил метод специально - разобраться, почему не работает такой запрос?
     */
//    @GetMapping("test/{id}/{name}")
//    public ResponseEntity<String> testChangeNameTask(@PathVariable(name = "id") Long id,
//                                                     @PathVariable(name = "name") String name){
//        Task newTaskTest = this.taskService.getTaskById(id);
//        newTaskTest.setName(name);
//        taskService.registerTask(newTaskTest);
//        return ResponseEntity.ok("Имя задачи изменено");
//    }
}
