package com.heli.oa.sunshine.dao;

import com.heli.oa.sunshine.entity.Backlog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public interface BacklogDao {

    void addBacklog(Backlog backlog);

    void deleteBacklog(Backlog backlog);

    void updateBacklog(Backlog backlog);

    Backlog selectBacklog(Integer recordId);

    List<Backlog> listBacklogByPriority(Backlog backlog);

    List<Backlog> listByStatus(Backlog backlog);

    List<Backlog> listByStatusAndUserId(@Param("backlogStatus")String backlogStatus, @Param("userId")Integer userId);

    List<Backlog> listSetTimeBacklogByUserId(Backlog backlog);

    List<Backlog> searchDone(Backlog backlog) throws ParseException;
}
