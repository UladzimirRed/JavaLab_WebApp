package com.epam.lab.repository;

import com.epam.lab.model.Tag;

import java.util.List;

public interface TagDao extends BaseDao<Tag> {

    List<Tag> getTagsByNewsId(Long newsId);
}
