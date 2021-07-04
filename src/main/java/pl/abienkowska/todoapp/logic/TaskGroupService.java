package pl.abienkowska.todoapp.logic;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.abienkowska.todoapp.TaskConfigurationProperties;
import pl.abienkowska.todoapp.model.Task;
import pl.abienkowska.todoapp.model.TaskGroup;
import pl.abienkowska.todoapp.model.TaskGroupRepository;
import pl.abienkowska.todoapp.model.TaskRepository;
import pl.abienkowska.todoapp.model.projection.GroupReadModel;
import pl.abienkowska.todoapp.model.projection.GroupWriteModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {

    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    TaskGroupService(final TaskGroupRepository repository, final TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup (GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup (int groupId) {
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first.");
        }
        TaskGroup result = repository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }
}
