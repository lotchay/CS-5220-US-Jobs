package usjobs.model.dao;

import java.util.List;

import usjobs.model.Resume;

public interface ResumeDao {

    List<Resume> getResumes( int userId );

    Resume saveResume( Resume resume );
    
    List<Resume> searchResumes(String searchTerm, String searchLoc);

    void deleteResume( Resume resume );

    Resume getResume( int id );
}
