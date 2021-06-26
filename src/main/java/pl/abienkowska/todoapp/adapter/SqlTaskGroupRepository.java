package pl.abienkowska.todoapp.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.abienkowska.todoapp.model.TaskGroup;
import pl.abienkowska.todoapp.model.TaskGroupRepository;

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {


}
