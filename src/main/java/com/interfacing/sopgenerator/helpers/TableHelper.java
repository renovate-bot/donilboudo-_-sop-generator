package com.interfacing.sopgenerator.helpers;

import org.docx4j.wml.*;

import java.math.BigInteger;

public class TableHelper {
    private final static TableHelper instance = new TableHelper();

    public static TableHelper getInstance() {
        return instance;
    }

    private ObjectFactory factory;
    private String color;
    private String fillColor;
    private STVerticalJc align;
    private String borderSize;

    public TableHelper() {
    }

    public TableHelper(ObjectFactory factory) {
        this.factory = factory;
    }

//    private static Tc createColumn(ObjectFactory factory, String value) {
//        Tc column = factory.createTc();
//        column.getContent().add(createParagraph(value));
//
//        createColumnProperties(column);
//
//        return column;
//    }

    public void createColumnProperties(ObjectFactory factory, Tc column) {
        TcPr properties = factory.createTcPr();

        CTShd shd = factory.createCTShd();
        shd.setColor(this.color);
        shd.setFill(this.fillColor);
        properties.setShd(shd);

        CTVerticalJc align = factory.createCTVerticalJc();
        align.setVal(this.align);
        properties.setVAlign(align);

        column.setTcPr(properties);
    }

    public void createTableBorder(Tbl table) {
        TblBorders borders = factory.createTblBorders();

        borders.setTop(factory.createCTBorder());
        borders.getTop().setColor(this.color);
        borders.getTop().setSz(new BigInteger("6"));

        borders.setLeft(factory.createCTBorder());
        borders.getTop().setColor(this.color);
        borders.getLeft().setSz(new BigInteger("6"));

        borders.setBottom(factory.createCTBorder());
        borders.getTop().setColor(this.color);
        borders.getBottom().setSz(new BigInteger("6"));

        borders.setRight(factory.createCTBorder());
        borders.getTop().setColor(this.color);
        borders.getRight().setSz(new BigInteger("6"));

        borders.setInsideH(factory.createCTBorder());
        borders.getTop().setColor(this.color);
        borders.getInsideH().setSz(new BigInteger("6"));

        borders.setInsideV(factory.createCTBorder());
        borders.getTop().setColor(this.color);
        borders.getInsideV().setSz(new BigInteger("6"));

        table.getTblPr().setTblBorders(borders);
    }

    public void createTableGrid(Tbl table, int nbrColumns, String[] colWidths) {
        TblGrid grid = factory.createTblGrid();
        for (int index = 0; index < nbrColumns; index++)
        {
            TblGridCol col = factory.createTblGridCol();
            col.setW(new BigInteger(colWidths[index]));
            grid.getGridCol().add(col);
        }

        table.setTblGrid(grid);
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public void setAlign(STVerticalJc align) {
        this.align = align;
    }

    public void setBorderSize(String borderSize) {
        this.borderSize = borderSize;
    }

    public String getBorderSize() {
        return borderSize;
    }
}
