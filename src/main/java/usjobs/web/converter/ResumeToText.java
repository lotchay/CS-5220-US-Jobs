package usjobs.web.converter;

import org.springframework.core.convert.converter.Converter;
import usjobs.model.Resume;

public class ResumeToText implements Converter<Resume, String> {

    @Override
    public String convert( Resume resume ) {

        return resume.getId().toString();
    }
}
