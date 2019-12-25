package cn.tedu.home.pojo;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    private Integer id;

    private String adminname;

    private String password;

    private String sex;

    private String phone;

    private String city;

    private Long status;

    private String email;

    private Integer level;

    private String avatar;

    private Date jointime;

    private Integer experience;

    private String ip;

    private Integer logins;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getLogins() {
        return logins;
    }

    public void setLogins(Integer logins) {
        this.logins = logins;
    }

	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminname=" + adminname + ", password=" + password + ", sex=" + sex + ", phone="
				+ phone + ", city=" + city + ", status=" + status + ", email=" + email + ", level=" + level
				+ ", avatar=" + avatar + ", jointime=" + jointime + ", experience=" + experience + ", ip=" + ip
				+ ", logins=" + logins + "]";
	}
    
}