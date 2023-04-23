package com.example.phonestore.model;

import java.io.Serializable;

public class ThuThu implements Serializable {
    private String taiKhoan;
    private int maThuThu;
    private String maKhau;
    private String diachi;
    public ThuThu() {
    }

    public ThuThu(String taiKhoan, int maThuThu, String maKhau, String diachi) {
        this.taiKhoan = taiKhoan;
        this.maThuThu = maThuThu;
        this.maKhau = maKhau;
        this.diachi = diachi;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(int maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
