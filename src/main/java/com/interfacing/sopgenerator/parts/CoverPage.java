package com.interfacing.sopgenerator.parts;

import com.interfacing.sopgenerator.helpers.TextHelper;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ObjectFactory;

import java.util.Date;


public class CoverPage {
    private static final String COVER_PAGE_SOP_PLACEHOLDER          = "[cover_page_sop]";
    private static final String COVER_PAGE_FOR_PLACEHOLDER          = "[cover_page_for]";
    private static final String COVER_PAGE_SOP_NAME_PLACEHOLDER     = "[cover_page_sop_name]";
    private static final String COVER_PAGE_VERSION_PLACEHOLDER      = "[cover_page_version]";
    private static final String COVER_PAGE_DATE_PLACEHOLDER         = "[cover_page_date]";
    private static final String COVER_PAGE_STATUS_PLACEHOLDER       = "[cover_page_status]";
    private static final String COVER_PAGE_ISSUED_BY_PLACEHOLDER    = "[cover_page_issue_by]";

    public void buildCoverPage(ObjectFactory factory, MainDocumentPart mdp) {
        String cover_page_sop = "Standard Operating Procedures";
        String cover_page_for = "for";
        String cover_page_sop_name = "Perform IT Pre-boarding Activities";
        String cover_page_version = "v 0.05";
        String cover_page_status = "In Progress";
        String cover_page_date = new Date().toString();

        TextHelper helper = TextHelper.getInstance();

        helper.replacePlaceholderParagraph(factory, mdp, cover_page_sop, COVER_PAGE_SOP_PLACEHOLDER);
        helper.replacePlaceholderParagraph(factory, mdp, cover_page_for, COVER_PAGE_FOR_PLACEHOLDER);
        helper.replacePlaceholderParagraph(factory, mdp, cover_page_sop_name, COVER_PAGE_SOP_NAME_PLACEHOLDER);
        helper.replacePlaceholderParagraph(factory, mdp, cover_page_version, COVER_PAGE_VERSION_PLACEHOLDER);
        helper.replacePlaceholderParagraph(factory, mdp, cover_page_date, COVER_PAGE_DATE_PLACEHOLDER);
        helper.replacePlaceholderParagraph(factory, mdp, cover_page_status, COVER_PAGE_STATUS_PLACEHOLDER);

    }
}
