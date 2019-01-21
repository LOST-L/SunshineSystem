package com.heli.oa.sunshine.service;

import com.heli.oa.sunshine.entity.Backlog;

import java.text.ParseException;
import java.util.List;


public interface BacklogService {

    void addBacklog(Backlog backlog);

    void deleteBacklog(Backlog backlog);

    String setTime(Backlog backlog);

    void insertBacklog(Backlog backlog);

    void cancelTime(Backlog todo);

    void overTime(Backlog backlog);

    List<Backlog> listBacklogByPriority(Backlog backlog);

    List<Backlog> listByStatus(Backlog backlog);

    List<Backlog> listSetTimeBacklogByUserId(Backlog backlog);

    List<Backlog> searchDone(Backlog backlog) throws ParseException;
}
