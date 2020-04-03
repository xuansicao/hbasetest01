package entity;

public class Dep {
    private String id;
    private String org_id;
    private String org_code;
    private String org_name;
    private String org_parent_id;
    private String parent_code;
    private String parent_name;
    private String waiqin365_parent_id;
    private String full_ids;
    private String full_codes;
    private String full_names;
    private String org_sequence;
    private String org_status;
    private String create_time;
    private String modify_time;
    private String call_date;
    private String bu;

    public Dep(){}
    public Dep(String id, String org_name, String org_code){
        this.id = id;
        this.org_name = org_name;
        this.org_code = org_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_parent_id() {
        return org_parent_id;
    }

    public void setOrg_parent_id(String org_parent_id) {
        this.org_parent_id = org_parent_id;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getWaiqin365_parent_id() {
        return waiqin365_parent_id;
    }

    public void setWaiqin365_parent_id(String waiqin365_parent_id) {
        this.waiqin365_parent_id = waiqin365_parent_id;
    }

    public String getFull_ids() {
        return full_ids;
    }

    public void setFull_ids(String full_ids) {
        this.full_ids = full_ids;
    }

    public String getFull_codes() {
        return full_codes;
    }

    public void setFull_codes(String full_codes) {
        this.full_codes = full_codes;
    }

    public String getFull_names() {
        return full_names;
    }

    public void setFull_names(String full_names) {
        this.full_names = full_names;
    }

    public String getOrg_sequence() {
        return org_sequence;
    }

    public void setOrg_sequence(String org_sequence) {
        this.org_sequence = org_sequence;
    }

    public String getOrg_status() {
        return org_status;
    }

    public void setOrg_status(String org_status) {
        this.org_status = org_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getCall_date() {
        return call_date;
    }

    public void setCall_date(String call_date) {
        this.call_date = call_date;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }


    @Override
    public String toString() {
        return "Dep{" +
                "id='" + id + '\'' +
                ", org_id='" + org_id + '\'' +
                ", org_code='" + org_code + '\'' +
                ", org_name='" + org_name + '\'' +
                ", org_parent_id='" + org_parent_id + '\'' +
                ", parent_code='" + parent_code + '\'' +
                ", parent_name='" + parent_name + '\'' +
                ", waiqin365_parent_id='" + waiqin365_parent_id + '\'' +
                ", full_ids='" + full_ids + '\'' +
                ", full_codes='" + full_codes + '\'' +
                ", full_names='" + full_names + '\'' +
                ", org_sequence='" + org_sequence + '\'' +
                ", org_status='" + org_status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", call_date='" + call_date + '\'' +
                ", bu='" + bu + '\'' +
                '}';
    }
}
