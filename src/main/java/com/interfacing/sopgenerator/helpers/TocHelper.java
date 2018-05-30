package com.interfacing.sopgenerator.helpers;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.toc.TocException;
import org.docx4j.toc.TocGenerator;

public class TocHelper {
    public static final String TOC_STYLE_MASK = "TOC%s";
    private static final String TOC_PLACEHOLDER = "[Table_Of_Content]";

    public void generate(WordprocessingMLPackage wordMLPackage, MainDocumentPart documentPart) {
        int index = TextHelper.getInstance().getParagraphIndex(documentPart, TOC_PLACEHOLDER);
        if (index > -1)
        {
            documentPart.getContent().remove(index);
            TocGenerator tocGenerator;
            try
            {
                tocGenerator = new TocGenerator(wordMLPackage);
                // to generate page numbers, you should install your own local instance of Plutext PDF Converter,
                // and point to that in docx4j.properties

                tocGenerator.generateToc(index, " TOC \\o \"1-3\" \\h \\z \\u ", true);
            }
            catch (TocException e)
            {
                e.printStackTrace();
            }
        }
    }
}
