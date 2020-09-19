package cc.young.common.mongodb.entity;


public class Condition {
    private String columnName;
    private Opt opt;
    private Object value;

    public Condition() {
    }

    public Condition(String columnName, Opt opt, Object value) {
        this.columnName = columnName;
        this.opt = opt;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Opt getOpt() {
        return opt;
    }

    public void setOpt(Opt opt) {
        this.opt = opt;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}