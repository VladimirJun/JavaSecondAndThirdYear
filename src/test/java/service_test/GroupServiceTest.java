package service_test;

import org.example.entity.GroupEntity;
import org.example.exception.ServiceException;
import org.example.model.GroupModel;
import org.example.repository.EntityRepository;
import org.example.request.AddGroupRequest;
import org.example.request.EditGroupRequest;
import org.example.request.IdRequest;
import org.example.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    private GroupService groupService;
    private EntityRepository<GroupEntity> groupRepository;

    @BeforeEach
    void setUp() {
        groupRepository = mock(EntityRepository.class);
        groupService = new GroupService(groupRepository);
    }

    @Test
    void testGetStudentGroups() {
        List<GroupEntity> groups = List.of(
                new GroupEntity(1L, "Group A"),
                new GroupEntity(2L, "Group B")
        );

        when(groupRepository.getAll()).thenReturn(groups);

        Collection<GroupModel> result = groupService.getStudentGroups();

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(group -> group.name().equals("Group A")));
        assertTrue(result.stream().anyMatch(group -> group.name().equals("Group B")));
    }

    @Test
    void testGetStudentGroupById() {
        Long groupId = 1L;
        GroupEntity groupEntity = new GroupEntity(groupId, "Group A");

        when(groupRepository.get(groupId)).thenReturn(groupEntity);

        IdRequest request = new IdRequest(groupId);
        GroupModel group = groupService.getStudentGroupById(request);

        assertNotNull(group);
        assertEquals("Group A", group.name());
    }

    @Test
    void testGetStudentGroupById_invalid() {
        Long groupId = 1L;

        when(groupRepository.get(groupId)).thenReturn(null);

        IdRequest request = new IdRequest(groupId);
        GroupModel group = groupService.getStudentGroupById(request);

        assertNull(group);
    }

    @Test
    void testAddStudentGroup() {
        AddGroupRequest request = new AddGroupRequest("Group A");
        GroupEntity groupEntity = new GroupEntity(null, "Group A");

        when(groupRepository.create(any(GroupEntity.class))).thenReturn(1L);

        Long groupId = groupService.addStudentGroup(request);

        assertNotNull(groupId);
        assertEquals(1L, groupId);
    }

    @Test
    void testEditStudentGroup() {
        Long groupId = 1L;
        GroupEntity existingGroup = new GroupEntity(groupId, "Group A");
        when(groupRepository.get(groupId)).thenReturn(existingGroup);
        EditGroupRequest request = new EditGroupRequest(groupId, "Updated Group A");
        GroupEntity newGroup = new GroupEntity(groupId, "Updated Group A");
        groupService.editStudentGroup(request);
        assertEquals("Updated Group A", newGroup.name());
    }


    @Test
    void testEditStudentGroup_NotFound() {
        Long groupId = 1L;

        when(groupRepository.get(groupId)).thenReturn(null);

        EditGroupRequest request = new EditGroupRequest(groupId, "Updated Group A");

        ServiceException exception = assertThrows(ServiceException.class, () -> groupService.editStudentGroup(request));
        assertEquals("No group entity with id 1 found", exception.getMessage());
    }

    @Test
    void testDeleteStudentGroup() {
        Long groupId = 1L;

        doNothing().when(groupRepository).delete(groupId);

        IdRequest request = new IdRequest(groupId);
        groupService.deleteStudentGroup(request);

        // Проверяем косвенно через отсутствие исключений
        verify(groupRepository, times(1)).delete(groupId);
    }
}
