package baro.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 14.05.2019 10:20
 */
public class EmbedContentController implements Initializable {

    @FXML
    private TextField authorName, authorAvatarUrl, title, titleUrl, footer, footerUrl, thumbnail,
            img, fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7,
            fieldName8, fieldName9;

    @FXML
    private TextArea fieldValue1, fieldValue2, fieldValue3, fieldValue4, fieldValue5, fieldValue6, fieldValue7,
            fieldValue8, fieldValue9;

    @FXML
    private CheckBox inline1, inline2, inline3, inline4, inline5, inline6, inline7, inline8, inline9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public TextField getAuthorName() {
        return authorName;
    }

    public TextField getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public TextField getTitle() {
        return title;
    }

    public TextField getTitleUrl() {
        return titleUrl;
    }

    public TextField getFooter() {
        return footer;
    }

    public TextField getFooterUrl() {
        return footerUrl;
    }

    public TextField getThumbnail() {
        return thumbnail;
    }

    public TextField getImg() {
        return img;
    }

    public TextField getFieldName1() {
        return fieldName1;
    }

    public TextField getFieldName2() {
        return fieldName2;
    }

    public TextField getFieldName3() {
        return fieldName3;
    }

    public TextField getFieldName4() {
        return fieldName4;
    }

    public TextField getFieldName5() {
        return fieldName5;
    }

    public TextField getFieldName6() {
        return fieldName6;
    }

    public TextField getFieldName7() {
        return fieldName7;
    }

    public TextField getFieldName8() {
        return fieldName8;
    }

    public TextField getFieldName9() {
        return fieldName9;
    }

    public TextArea getFieldValue1() {
        return fieldValue1;
    }

    public TextArea getFieldValue2() {
        return fieldValue2;
    }

    public TextArea getFieldValue3() {
        return fieldValue3;
    }

    public TextArea getFieldValue4() {
        return fieldValue4;
    }

    public TextArea getFieldValue5() {
        return fieldValue5;
    }

    public TextArea getFieldValue6() {
        return fieldValue6;
    }

    public TextArea getFieldValue7() {
        return fieldValue7;
    }

    public TextArea getFieldValue8() {
        return fieldValue8;
    }

    public TextArea getFieldValue9() {
        return fieldValue9;
    }

    public CheckBox getInline1() {
        return inline1;
    }

    public CheckBox getInline2() {
        return inline2;
    }

    public CheckBox getInline3() {
        return inline3;
    }

    public CheckBox getInline4() {
        return inline4;
    }

    public CheckBox getInline5() {
        return inline5;
    }

    public CheckBox getInline6() {
        return inline6;
    }

    public CheckBox getInline7() {
        return inline7;
    }

    public CheckBox getInline8() {
        return inline8;
    }

    public CheckBox getInline9() {
        return inline9;
    }
}
