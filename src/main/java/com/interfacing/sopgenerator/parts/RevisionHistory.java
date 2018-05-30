package com.interfacing.sopgenerator.parts;

import com.interfacing.sopgenerator.helpers.TextHelper;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.sharedtypes.STOnOff;
import org.docx4j.wml.*;

import java.math.BigInteger;

public class RevisionHistory {
    private static final String REVISION_HISTORY_PLACEHOLDER = "[revision_history]";
    private ObjectFactory factory;
    private MainDocumentPart documentPart;
    private int revisionHistoryTitleIndex = -1;
    private TextHelper textHelper;

    public RevisionHistory(ObjectFactory factory, MainDocumentPart documentPart) {
        this.factory = factory;
        this.documentPart = documentPart;
        this.textHelper = TextHelper.getInstance();
    }

    public void fillTitle() {
        TextHelper helper = TextHelper.getInstance();
        helper.createPageBreak(factory, documentPart);
        revisionHistoryTitleIndex = helper.getParagraphIndex(documentPart, REVISION_HISTORY_PLACEHOLDER);
        helper.replacePlaceholderParagraph(factory, documentPart, "Revision History", REVISION_HISTORY_PLACEHOLDER);
    }

    public void buildTable() {
        Body body = documentPart.getJaxbElement().getBody();

        if (revisionHistoryTitleIndex != -1)
        {
            textHelper.addBlankSpace(factory, documentPart, 1);

            Tbl table = factory.createTbl();
            addTableProperties(table);
            addTableGrid(table);

            Tr headerRow = createHeader();
            table.getContent().add(headerRow);

            Tr contentRow = createContentRow();
            table.getContent().add(contentRow);

            documentPart.addObject(table);
        }
    }

    private Tr createContentRow() {
        //content
        Tr contentRow = factory.createTr();

        Tc col1 = factory.createTc();
        P p1 = documentPart.createParagraphOfText("0.05");
        col1.getContent().add(p1);
        contentRow.getContent().add(col1);

        Tc col2 = factory.createTc();
        P p2 = documentPart.createParagraphOfText("Matias Fontecilla");
        col2.getContent().add(p2);
        contentRow.getContent().add(col2);

        Tc col3 = factory.createTc();
        P p3 = documentPart.createParagraphOfText("In Progress");
        col3.getContent().add(p3);
        contentRow.getContent().add(col3);

        Tc col4 = factory.createTc();
        P p4 = documentPart.createParagraphOfText("16-Apr-2018");
        col4.getContent().add(p4);
        contentRow.getContent().add(col4);

        Tc col5 = factory.createTc();
        P p5 = documentPart.createParagraphOfText("");
        col5.getContent().add(p5);
        contentRow.getContent().add(col5);

        return contentRow;
    }

    private void addTableGrid(Tbl table) {
        TblGrid grid = factory.createTblGrid();
        TblGridCol col1 = factory.createTblGridCol();
        col1.setW(new BigInteger("4242"));
        TblGridCol col2 = factory.createTblGridCol();
        col2.setW(new BigInteger("4860"));
        grid.getGridCol().add(col1);
        grid.getGridCol().add(col2);

        table.setTblGrid(grid);
    }

    private void addTableProperties(Tbl table) {
        TblPr properties = factory.createTblPr();

        CTTblPrBase.TblStyle style = factory.createCTTblPrBaseTblStyle();
        style.setVal("TableGrid");
        properties.setTblStyle(style);

        Jc align = factory.createJc();
        align.setVal(JcEnumeration.CENTER);
        properties.setJc(align);

        TblWidth width = factory.createTblWidth();
        width.setW(new BigInteger("9102"));
        properties.setTblW(width);

        CTTblLook look = factory.createCTTblLook();
        look.setFirstRow(STOnOff.fromValue("1"));
        look.setLastRow(STOnOff.ONE);
        look.setFirstColumn(STOnOff.fromValue("1"));
        look.setLastColumn(STOnOff.ONE);
        properties.setTblLook(look);

        table.setTblPr(properties);
    }

    private void addColumnProperties(Tc column, String color, String fillColor, STVerticalJc align) {
        TcPr properties = factory.createTcPr();

        CTShd shd = factory.createCTShd();
        shd.setColor(color);
        shd.setFill(fillColor);
        shd.setVal(STShd.CLEAR);
        properties.setShd(shd);

        CTVerticalJc jc = factory.createCTVerticalJc();
        jc.setVal(align);
        properties.setVAlign(jc);

        column.setTcPr(properties);
    }


    private Tr createHeader() {
        Tr headerRow = factory.createTr();

        //header
        Tc version = factory.createTc();
        version.getContent().add(documentPart.createParagraphOfText("Version"));
        addColumnProperties(version, "auto", "AEAAAA", STVerticalJc.CENTER);
        headerRow.getContent().add(version);


        Tc author = factory.createTc();
        author.getContent().add(documentPart.createParagraphOfText("Author"));
        addColumnProperties(author, "auto", "AEAAAA", STVerticalJc.CENTER);
        headerRow.getContent().add(author);

        Tc status = factory.createTc();
        status.getContent().add(documentPart.createParagraphOfText("Status"));
        addColumnProperties(status, "auto", "AEAAAA", STVerticalJc.CENTER);
        headerRow.getContent().add(status);

        Tc dateOfVersion = factory.createTc();
        dateOfVersion.getContent().add(documentPart.createParagraphOfText("Date of Version"));
        addColumnProperties(dateOfVersion, "auto", "AEAAAA", STVerticalJc.CENTER);
        headerRow.getContent().add(dateOfVersion);

        Tc notes = factory.createTc();
        notes.getContent().add(documentPart.createParagraphOfText("Notes"));
        addColumnProperties(notes, "auto", "AEAAAA", STVerticalJc.CENTER);
        headerRow.getContent().add(notes);

        return headerRow;
    }
}
