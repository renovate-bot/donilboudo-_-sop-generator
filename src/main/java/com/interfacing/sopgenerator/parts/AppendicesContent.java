package com.interfacing.sopgenerator.parts;

import com.interfacing.sopgenerator.helpers.TextHelper;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;

public class AppendicesContent {
    private ObjectFactory factory;
    private MainDocumentPart documentPart;
    private TextHelper textHelper;

    public AppendicesContent(ObjectFactory factory, MainDocumentPart documentPart) {
        this.factory = factory;
        this.documentPart = documentPart;
        textHelper = TextHelper.getInstance();
    }

    public void build() {
        textHelper.createPageBreak(factory, documentPart);

        //roles and responsibilities
        P paragraph1 = documentPart.createStyledParagraphOfText("Heading2","1. Roles and Responsibilities");
        textHelper.createRunPropertyFromParagraph(factory, paragraph1, 14, true);
        documentPart.addObject(paragraph1);
        textHelper.addBlankSpace(factory, documentPart, 1);

        P paragraph2 = documentPart.createParagraphOfText("IT Specialist");
        textHelper.createRunPropertyFromParagraph(factory, paragraph2, 10, true);
        documentPart.addObject(paragraph2);
        textHelper.addBlankSpace(factory, documentPart, 1);

        P paragraph3 = documentPart.createParagraphOfText("S1.2.1 New ticket for new hire");
        textHelper.createRunPropertyFromParagraph(factory, paragraph3, 10, false);
        textHelper.createParagraphProperties(factory, paragraph3);
        documentPart.addObject(paragraph3);

        P paragraph4 = documentPart.createParagraphOfText("S1.2.11 Dedicated machine is delivered");
        textHelper.createRunPropertyFromParagraph(factory, paragraph4, 10, false);
        textHelper.createParagraphProperties(factory, paragraph4);
        documentPart.addObject(paragraph4);

        P paragraph5 = documentPart.createParagraphOfText("S1.2.12 Configure machine");
        textHelper.createRunPropertyFromParagraph(factory, paragraph5, 10, false);
        textHelper.createParagraphProperties(factory, paragraph5);
        documentPart.addObject(paragraph5);

        P paragraph6 = documentPart.createParagraphOfText("S1.2.13 Ship machine");
        textHelper.createRunPropertyFromParagraph(factory, paragraph6, 10, false);
        textHelper.createParagraphProperties(factory, paragraph6);
        documentPart.addObject(paragraph6);
    }
}
