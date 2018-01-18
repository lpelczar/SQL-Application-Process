package dao;

import models.Mentor;

import java.sql.Statement;
import java.util.List;

public interface MentorDAO {

    List<Mentor> query(Statement statement);
    boolean add(Mentor mentor);
    boolean remove(Mentor mentor);
    boolean update(Mentor mentor);

}