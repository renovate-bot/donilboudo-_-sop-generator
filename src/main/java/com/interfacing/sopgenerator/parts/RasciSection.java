package com.interfacing.sopgenerator.parts;

import com.interfacing.sopgenerator.helpers.TextHelper;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.math.BigInteger;

public class RasciSection {
    private ObjectFactory factory;
    private MainDocumentPart mdp;
    private TextHelper textHelper;

    public RasciSection(ObjectFactory factory, MainDocumentPart mdp) {
        this.factory = factory;
        this.mdp = mdp;
        this.textHelper = TextHelper.getInstance();
    }

    public void build() {
        textHelper.createPageBreak(factory, mdp);

        addSectionToParagraph();
        createSection();
    }

    private void addSectionToParagraph() {
        P paragraph = factory.createP();

        SectPr sectPr = factory.createSectPr();
        SectPr.PgSz pgSz = factory.createSectPrPgSz();
        pgSz.setW(new BigInteger("12240"));
        pgSz.setH(new BigInteger("15840"));
        sectPr.setPgSz(pgSz);

        SectPr.PgMar pgMar = factory.createSectPrPgMar();
        pgMar.setTop(new BigInteger("1440"));
        pgMar.setRight(new BigInteger("1440"));
        pgMar.setBottom(new BigInteger("1440"));
        pgMar.setLeft(new BigInteger("1440"));
        pgMar.setHeader(new BigInteger("708"));
        pgMar.setFooter(new BigInteger("708"));
        sectPr.setPgMar(pgMar);

        CTColumns columns = factory.createCTColumns();
        columns.setSpace(new BigInteger("708"));
        sectPr.setCols(columns);

        CTDocGrid docGrid = factory.createCTDocGrid();
        docGrid.setLinePitch(new BigInteger("360"));
        sectPr.setDocGrid(docGrid);

        PPr pPr = factory.createPPr();
        pPr.setSectPr(sectPr);
        paragraph.setPPr(pPr);

        mdp.addObject(paragraph);
    }

    private void createSection() {
        SectPr sectPr = factory.createSectPr();

        SectPr.PgSz pgSz = factory.createSectPrPgSz();
        pgSz.setW(new BigInteger("15840"));
        pgSz.setH(new BigInteger("12240"));
        sectPr.setPgSz(pgSz);

        SectPr.PgMar pgMar = factory.createSectPrPgMar();
        pgMar.setTop(new BigInteger("1440"));
        pgMar.setRight(new BigInteger("1440"));
        pgMar.setBottom(new BigInteger("1440"));
        pgMar.setLeft(new BigInteger("1440"));
        pgMar.setHeader(new BigInteger("708"));
        pgMar.setFooter(new BigInteger("708"));
        sectPr.setPgMar(pgMar);

        CTColumns columns = factory.createCTColumns();
        columns.setSpace(new BigInteger("708"));
        sectPr.setCols(columns);

        CTDocGrid docGrid = factory.createCTDocGrid();
        docGrid.setLinePitch(new BigInteger("360"));
        sectPr.setDocGrid(docGrid);

        mdp.addObject(sectPr);

    }
}
