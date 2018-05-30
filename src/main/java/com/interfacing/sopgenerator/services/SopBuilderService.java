package com.interfacing.sopgenerator.services;

import com.interfacing.sopgenerator.helpers.Template;
import com.interfacing.sopgenerator.helpers.TextHelper;
import com.interfacing.sopgenerator.helpers.TocHelper;
import com.interfacing.sopgenerator.parts.*;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ObjectFactory;
import org.springframework.stereotype.Service;

import static com.interfacing.sopgenerator.helpers.TocSample.TOC_STYLE_MASK;

@Service
public class SopBuilderService {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    public void buildSOP() {
        Template wordTemplate = new Template();
        WordprocessingMLPackage template = wordTemplate.getTemplate();
        if (template != null)
        {
            MainDocumentPart documentPart = template.getMainDocumentPart();

            for (int i = 1; i < 10; i++)
            {
                documentPart.getPropertyResolver().activateStyle(String.format(TOC_STYLE_MASK, i));
            }

            CoverPage coverPage = new CoverPage();
            coverPage.buildCoverPage(factory, documentPart);

            /*
            RevisionHistory revisionHistory = new RevisionHistory(factory, documentPart);
            revisionHistory.fillTitle();
            revisionHistory.buildTable();
            */

            //process
            //ProcessCover processCover = new ProcessCover(factory, documentPart);
            //  processCover.build();

            ProcessContent processContent = new ProcessContent(factory, documentPart);
            processContent.build();

            //appendices
            /*
            AppendicesCover appendicesCover = new AppendicesCover(factory, documentPart);
            appendicesCover.build();

            AppendicesContent appendicesContent = new AppendicesContent(factory, documentPart);
            appendicesContent.build();
            */

            //rasci
            //RasciSection rasciSection = new RasciSection(factory, mdp);
            //rasciSection.build();

            TextHelper.getInstance().createPageBreak(factory, documentPart);
            TocHelper tocHelper = new TocHelper();
            tocHelper.generate(template, documentPart);

            try
            {
                wordTemplate.saveTemplate(template);
                //wordTemplate.saveToPDF(template);
            }
            catch (Docx4JException e)
            {
                e.printStackTrace();
            }
        }
    }
}
