package com.leoni.packaging.service;

import com.leoni.packaging.model.Group;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupService {
    Group findGroupById(Long id);
    Page<Group> findGroups(String group, int page, int size);
    List<Group> findGroups();
    Group addGroup(Group group);
    Group updateGroup(Long id, Group group);
    void deleteGroupById(Long id);
}
