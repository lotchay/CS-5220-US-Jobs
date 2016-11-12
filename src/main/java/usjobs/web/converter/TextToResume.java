package usjobs.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import usjobs.model.Resume;
import usjobs.model.dao.ResumeDao;

public class TextToResume implements Converter<String, Resume> {

    @Autowired
    ResumeDao resumeDao;

    @Override
    public Resume convert( String text ) {

        return resumeDao.getResume( Integer.parseInt( text ) );
    }
}
