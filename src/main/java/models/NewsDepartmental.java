package models;

public class NewsDepartmental extends News{
    private int departmentid;
    public NewsDepartmental(String content, int departmnetid) {
        super(content);
        this.departmentid = departmnetid;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }
}
