package pl.abienkowska.todoapp.model.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;
import pl.abienkowska.todoapp.model.Task;
import pl.abienkowska.todoapp.model.TaskGroup;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GroupReadModelTest {

    @Test
    @DisplayName("should create null deadline for group when no task deadline")
    void constructor_noDeadlines_createsNullDeadline(){
        //given
        var source = new TaskGroup();
        source.setDescription("foo");
        source.setTasks(Set.of(new Task("bar", null)));

        //when
        var result = new GroupReadModel(source);

        //then
        assertThat(result).hasFieldOrPropertyWithValue("deadline", null);
    }

}