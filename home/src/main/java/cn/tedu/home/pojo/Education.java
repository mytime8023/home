package cn.tedu.home.pojo;

import java.io.Serializable;

public class Education implements Serializable {
    private Integer id;

    private String eduname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEduname() {
        return eduname;
    }

    public void setEduname(String eduname) {
        this.eduname = eduname == null ? null : eduname.trim();
    }

	@Override
	public String toString() {
		return "Education [id=" + id + ", eduname=" + eduname + "]";
	}
    
}