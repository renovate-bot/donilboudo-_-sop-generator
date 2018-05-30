package com.interfacing.sopgenerator.helpers;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TextHelper {
    private final static TextHelper instance = new TextHelper();

    public static TextHelper getInstance() {
        return instance;
    }
//
//    private ObjectFactory factory;
//    private MainDocumentPart mdp;

    public TextHelper() {

    }

//    public TextHelper(ObjectFactory factory, MainDocumentPart mdp) {
//        this.factory = factory;
//        this.mdp = mdp;
//    }

    public void replacePlaceholderParagraph(ObjectFactory factory, MainDocumentPart mdp, String value, String placeholder) {

        List<Object> paragraphs = getAllElementFromObject(mdp, P.class);

        for (Object paragraph : paragraphs)
        {
            P paragraphElement = (P) paragraph;
            if (paragraphElement.toString().equals(placeholder))
            {
                Text t = factory.createText();
                t.setValue(value);
                R run = factory.createR();
                run.getContent().add(t);

                RPr runProperties = null;
                List<Object> runs = paragraphElement.getContent();
                for (Object runObj : runs)
                {
                    if (runObj instanceof R)
                    {
                        R runElement = (R) runObj;
                        runProperties = runElement.getRPr();
                    }
                }

                if (runProperties != null)
                {
                    run.setRPr(runProperties);
                }

                paragraphElement.getContent().clear();
                paragraphElement.getContent().add(run);
            }
        }
    }

    private List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement)
        {
            obj = ((JAXBElement<?>) obj).getValue();
        }

        if (obj.getClass().equals(toSearch))
        {
            result.add(obj);
        }
        else if (obj instanceof ContentAccessor)
        {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children)
            {
                result.addAll(getAllElementFromObject(child, toSearch));
            }

        }
        return result;
    }

    public R createRun(ObjectFactory factory, String value) {
        R run = factory.createR();
        run.getContent().add(createText(factory, value));

        return run;
    }

    private Text createText(ObjectFactory factory, String value) {
        Text text = factory.createText();
        text.setValue(value);

        return text;
    }

    public int getParagraphIndex(MainDocumentPart documentPart, String value) {
        int index = 0;
        boolean found = false;

        for (Object object : documentPart.getContent())
        {
            if (object instanceof P)
            {
                P obj = (P) object;
                if (obj.toString().equals(value))
                {
                    found = true;
                    break;
                }
            }
            else if (object instanceof R)
            {
                R obj = (R) object;
                if (obj.toString().equals(value))
                {
                    found = true;

                }
            }
            index++;
        }

        if (!found)
        {
            return -1;
        }

        return index;
    }

    public void createPageBreak(ObjectFactory factory, MainDocumentPart mdp) {
        Br br = factory.createBr();
        br.setType(STBrType.PAGE);

        R run = factory.createR();
        run.getContent().add(br);

        P paragraph = factory.createP();
        paragraph.getContent().add(run);

        mdp.getJaxbElement().getBody().getContent().add(paragraph);
    }

    public void createRunProperty(ObjectFactory factory, R run, int fontSize, boolean bold) {
        //we nee to multiplied by 2
        fontSize = fontSize * 2;

        RPr property = factory.createRPr();

        //text size
        HpsMeasure measure = factory.createHpsMeasure();
        measure.setVal(new BigInteger(String.valueOf(fontSize)));
        property.setSz(measure);

        //bold
        if (bold)
        {
            BooleanDefaultTrue bdt = new BooleanDefaultTrue();
            bdt.setVal(bold);
            property.setB(bdt);
        }

        run.setRPr(property);
    }

    public void createRunPropertyFromParagraph(ObjectFactory factory, P paragraph, int fontSize, boolean bold) {
        R run = null;
        List<Object> content = paragraph.getContent();
        for (Object object : content)
        {
            if (object instanceof R)
            {
                run = (R) object;
            }
        }

        createRunProperty(factory, run, fontSize, bold);
    }

    public void createParagraphProperties(ObjectFactory factory, P paragraph) {
        PPr properties = factory.createPPr();

        PPrBase.PStyle style = factory.createPPrBasePStyle();
        style.setVal("ListParagraph");
        properties.setPStyle(style);

        PPrBase.NumPr numPr = factory.createPPrBaseNumPr();

        PPrBase.NumPr.Ilvl ilvl = factory.createPPrBaseNumPrIlvl();
        ilvl.setVal(new BigInteger("0"));
        numPr.setIlvl(ilvl);

        PPrBase.NumPr.NumId numId = factory.createPPrBaseNumPrNumId();
        numId.setVal(new BigInteger("2"));
        numPr.setNumId(numId);

        properties.setNumPr(numPr);

        paragraph.setPPr(properties);
    }

    public void addBlankSpace(ObjectFactory factory, MainDocumentPart documentPart, int occurrence) {
        for (int i = 0; i < occurrence; i++)
        {
            documentPart.addObject(factory.createP());
        }
    }
}
