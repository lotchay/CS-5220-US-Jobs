package usjobs.model.dao;

import java.util.List;

import usjobs.model.Resume;

public interface ResumeDao {

    List<Resume> getResumes( int userId );

    Resume saveResume( Resume resume );

    void deleteResume( Resume resume );

    Resume getResume( int id );
}
