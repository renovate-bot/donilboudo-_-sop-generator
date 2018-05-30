package com.interfacing.sopgenerator.parts;

import com.interfacing.sopgenerator.domain.Activity;
import com.interfacing.sopgenerator.domain.Process;
import com.interfacing.sopgenerator.helpers.PrepareData;
import com.interfacing.sopgenerator.helpers.TextHelper;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.sharedtypes.STOnOff;
import org.docx4j.wml.*;

import java.math.BigInteger;
import java.util.List;

import static com.interfacing.sopgenerator.parts.ProcessPlaceholders.*;

public class ProcessContent {
    private ObjectFactory factory;
    private MainDocumentPart documentPart;
    private TextHelper textHelper;

    public ProcessContent(ObjectFactory factory, MainDocumentPart documentPart) {
        this.factory = factory;
        this.documentPart = documentPart;
        textHelper = TextHelper.getInstance();
    }

    public void build() {
        PrepareData data = new PrepareData();
        Process process = data.buildProcess();

        if (process != null)
        {
            getProcessTagsInDocument();
            //helper.createPageBreak(factory, documentPart);

            fillProcessName(process);

            addProcessGoal(process);

            addProcessDescription(process);

            if (process.getActivities().size() > 0)
            {
                createProcedure(process);
            }

        }
    }

    private void getProcessTagsInDocument() {

    }

    private void createProcedure(Process process) {
        P procedureTitle = documentPart.createStyledParagraphOfText("Normal", "Procedure");
        documentPart.addObject(procedureTitle);
        textHelper.createRunPropertyFromParagraph(factory, procedureTitle, 12, true);

        textHelper.addBlankSpace(factory, documentPart, 1);

        addActivities(process);
    }

    private void addActivities(Process process) {
        for (Activity activity : process.getActivities())
        {
            addActivity(activity);
        }
    }

    private void addActivity(Activity activity) {
        P name = documentPart.createStyledParagraphOfText("Normal", activity.getName());
        documentPart.addObject(name);
        textHelper.createRunPropertyFromParagraph(factory, name, 11, true);

        addRolesAndAssetsTable(activity);

        if ((activity.getSimpleSteps() != null && !activity.getSimpleSteps().equals("")) ||
                (activity.getBulletSteps() != null) && !(activity.getBulletSteps().equals("")))
        {
            addSteps(activity);
        }
    }

    private void addSteps(Activity activity) {
        P stepsTitle = documentPart.createStyledParagraphOfText("Normal", "Steps:");
        documentPart.addObject(stepsTitle);
        textHelper.createRunPropertyFromParagraph(factory, stepsTitle, 10, true);

        textHelper.addBlankSpace(factory, documentPart, 1);

        P stepsContent = documentPart.createStyledParagraphOfText("Normal", activity.getSimpleSteps());
        documentPart.addObject(stepsContent);
        textHelper.createRunPropertyFromParagraph(factory, stepsContent, 11, false);

        textHelper.addBlankSpace(factory, documentPart, 1);
    }

    private void addRolesAndAssetsTable(Activity activity) {
        textHelper.addBlankSpace(factory, documentPart, 1);

        List<String> roles = activity.getRoles();
        List<String> assets = activity.getAssets();

        Tbl table = factory.createTbl();
        addTableProperties(table);
        addTableGrid(table);

        //header
        Tc rolesColumn = factory.createTc();
        rolesColumn.getContent().add(documentPart.createParagraphOfText("Roles"));
        addColumnProperties(rolesColumn, "auto", "AEAAAA", STVerticalJc.CENTER);

        Tc assetsColumn = factory.createTc();
        assetsColumn.getContent().add(documentPart.createParagraphOfText("Assets"));
        addColumnProperties(assetsColumn, "auto", "AEAAAA", STVerticalJc.CENTER);

        Tr headerRow = factory.createTr();
        addRowProperty(headerRow);
        headerRow.getContent().add(rolesColumn);
        headerRow.getContent().add(assetsColumn);

        table.getContent().add(headerRow);

        //content
        Tr contentRow = factory.createTr();
        Tc col1 = factory.createTc();
        P paragraph1 = factory.createP();
        if (roles != null)
        {
            for (String role : roles)
            {
                R run = textHelper.createRun(factory, role);
                paragraph1.getContent().add(run);
            }
        }
        //textHelper.createParagraphProperties(factory, paragraph1);
        col1.getContent().add(paragraph1);
        contentRow.getContent().add(col1);

        Tc col2 = factory.createTc();
        P paragraph2 = factory.createP();
        if (assets != null)
        {
            for (String asset : assets)
            {
                R run = textHelper.createRun(factory, asset);
                paragraph2.getContent().add(run);
            }
        }
        //textHelper.createParagraphProperties(factory, paragraph2);
        col2.getContent().add(paragraph2);
        contentRow.getContent().add(col2);

        table.getContent().add(contentRow);

        documentPart.addObject(table);

        textHelper.addBlankSpace(factory, documentPart, 1);
    }

    private void addRowProperty(Tr row) {

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

    private void addProcessDescription(Process process) {
        //get description paragraph position
        int index = textHelper.getParagraphIndex(documentPart, PLACEHOLDER_PROCESS_DESCRIPTION);

        if (index > -1)
        {
            R run = textHelper.createRun(factory, "Description");
            P descriptionTitle = ((P) documentPart.getContent().get(index));
            RPr style = null;
            //get paragraph style
            if (descriptionTitle.getContent().get(0) instanceof R)
            {
                style = ((R) descriptionTitle.getContent().get(0)).getRPr();
            }
            descriptionTitle.getContent().clear();
            run.setRPr(style);
            descriptionTitle.getContent().add(run);

            textHelper.addBlankSpace(factory, documentPart, 1);

            P descriptionContent = documentPart.createStyledParagraphOfText("Normal", process.getDescription());
            documentPart.getContent().add(index + 1, descriptionContent);
            textHelper.createRunPropertyFromParagraph(factory, descriptionContent, 10, false);

            textHelper.addBlankSpace(factory, documentPart, 1);
        }
    }

    private void addProcessGoal(Process process) {
        //get goal paragraph position
        int index = textHelper.getParagraphIndex(documentPart, PLACEHOLDER_PROCESS_GOAL);

        if (index > -1)
        {
            R run = textHelper.createRun(factory, "Goal");
            P goalTitle = ((P) documentPart.getContent().get(index));
            RPr style = null;
            //get paragraph style
            if (goalTitle.getContent().get(0) instanceof R)
            {
                style = ((R) goalTitle.getContent().get(0)).getRPr();
            }

            goalTitle.getContent().clear();
            run.setRPr(style);
            goalTitle.getContent().add(run);

            textHelper.addBlankSpace(factory, documentPart, 1);

            P goalContent = documentPart.createStyledParagraphOfText("Normal", process.getGoal());
            documentPart.getContent().add(index + 1, goalContent);
            textHelper.createRunPropertyFromParagraph(factory, goalContent, 10, false);

            textHelper.addBlankSpace(factory, documentPart, 1);
        }
    }

    private void fillProcessName(Process process) {
        int index = textHelper.getParagraphIndex(documentPart, PLACEHOLDER_PROCESS_NAME);

        String name = process.getName();
        String version = process.getVersion();
        String status = process.getStatus();

        if (version != null)
        {
            name = name + "(v. " + version;
        }
        if (status != null && !status.equals(""))
        {
            name = name + ", " + status + ")";
        }
        else
        {
            name = name + ")";
        }

        P nameParagraph = documentPart.createStyledParagraphOfText("Heading2", name);
        documentPart.addObject(nameParagraph);
        textHelper.createRunPropertyFromParagraph(factory, nameParagraph, 14, true);

        textHelper.addBlankSpace(factory, documentPart, 2);

    }
}
