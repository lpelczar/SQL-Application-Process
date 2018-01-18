package dao;

import models.Applicant;

import java.sql.Statement;
import java.util.List;

public interface ApplicantDAO {

    List<Applicant> query(Statement statement);
    boolean add(Applicant applicant);
    boolean remove(Applicant applicant);
    boolean update(Applicant applicant);

}