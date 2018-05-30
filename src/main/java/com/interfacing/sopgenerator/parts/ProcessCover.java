package com.interfacing.sopgenerator.parts;

import com.interfacing.sopgenerator.helpers.TextHelper;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;


public class ProcessCover {
    private ObjectFactory factory;
    private MainDocumentPart documentPart;

    public ProcessCover(ObjectFactory factory, MainDocumentPart mdp) {
        this.factory = factory;
        this.documentPart = mdp;
    }

    public void build() {
        TextHelper helper = TextHelper.getInstance();

        helper.createPageBreak(factory, documentPart);

        P paragraph = documentPart.createStyledParagraphOfText("Heading1", "Process");
        helper.createRunPropertyFromParagraph(factory, paragraph, 20, true);

        helper.addBlankSpace(factory, documentPart, 20);

        documentPart.addObject(paragraph);

        helper.addBlankSpace(factory, documentPart, 1);
    }
}
