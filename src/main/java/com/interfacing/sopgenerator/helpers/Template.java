package com.interfacing.sopgenerator.helpers;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class Template {

    public WordprocessingMLPackage getTemplate() {
        File doc = new File("sop-template-2.docx");
        WordprocessingMLPackage template = null;
        try
        {
            template = Docx4J.load(doc);

        }
        catch (Docx4JException e)
        {
            e.printStackTrace();
        }

        return template;
    }

    public void saveTemplate(WordprocessingMLPackage template) throws Docx4JException {
        File f = new File("sop-template-generated.docx");
        template.save(f);
    }

    public void saveToPDF(WordprocessingMLPackage template) throws FileNotFoundException, Docx4JException {
        OutputStream os = new java.io.FileOutputStream("sop-template-generated.pdf");
        Docx4J.toPDF(template, os);
    }
}
