package pl.abienkowska.todoapp.logic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.abienkowska.todoapp.TaskConfigurationProperties;
import pl.abienkowska.todoapp.model.ProjectRepository;
import pl.abienkowska.todoapp.model.TaskGroupRepository;
import pl.abienkowska.todoapp.model.TaskRepository;

@Configuration
class LogicConfiguration {

    @Bean
    ProjectService service(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskGroupService taskGroupService,
            final TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository, taskGroupService, config);
    }

    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository taskGroupRepository,
            @Qualifier("sqlTaskRepository") final TaskRepository taskRepository) {
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }
}
