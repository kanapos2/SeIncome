package Model;

import javafx.beans.property.SimpleStringProperty;

public class Income {
    protected SimpleStringProperty typeTable;
    protected SimpleStringProperty commentTable;
    protected SimpleStringProperty priceTable;

    public Income(){}

    public Income(String typeTable, String commentTable, String priceTable) {
        this.typeTable = new SimpleStringProperty(typeTable);
        this.commentTable = new SimpleStringProperty(commentTable);
        this.priceTable = new SimpleStringProperty(priceTable);
    }

    public String getTypeTable() {
        return typeTable.get();
    }

    public SimpleStringProperty typeTableProperty() {
        return typeTable;
    }

    public void setTypeTable(String typeTable) {
        this.typeTable.set(typeTable);
    }

    public String getCommentTable() {
        return commentTable.get();
    }

    public SimpleStringProperty commentTableProperty() {
        return commentTable;
    }

    public void setCommentTable(String commentTable) {
        this.commentTable.set(commentTable);
    }

    public String getPriceTable() {
        return priceTable.get();
    }

    public SimpleStringProperty priceTableProperty() {
        return priceTable;
    }

    public void setPriceTable(String priceTable) {
        this.priceTable.set(priceTable);
    }
}
