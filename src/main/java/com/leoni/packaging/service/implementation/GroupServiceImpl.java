package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.GroupRepository;
import com.leoni.packaging.model.Group;
import com.leoni.packaging.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public Group findGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Group Not Found"));
    }

    @Override
    public Page<Group> findGroups(String group, int page, int size) {
        return groupRepository.findByGroup(group,PageRequest.of(page, size));
    }

    @Override
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group addGroup(Group group) {
        group.setId(null);
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Long id, Group group) {
        findGroupById(id);
        group.setId(id);
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }
}
