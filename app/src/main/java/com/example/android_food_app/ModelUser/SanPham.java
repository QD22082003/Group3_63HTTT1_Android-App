package com.example.android_food_app.Model;

public class SanPham {
    private int imgMonNgonID_Trangchu;

    private String phanTram_Trangchu;
    private String tenMon_Trangchu;
    private String giaCu_Trangchu;
    private String giaMoi_Trangchu;

    public SanPham(int imgMonNgonID_Trangchu, String phanTram_Trangchu, String tenMon_Trangchu, String giaCu_Trangchu, String giaMoi_Trangchu) {
        this.imgMonNgonID_Trangchu = imgMonNgonID_Trangchu;
        this.phanTram_Trangchu = phanTram_Trangchu;
        this.tenMon_Trangchu = tenMon_Trangchu;
        this.giaCu_Trangchu = giaCu_Trangchu;
        this.giaMoi_Trangchu = giaMoi_Trangchu;
    }

    public int getImgMonNgonID_Trangchu() {
        return imgMonNgonID_Trangchu;
    }

    public void setImgMonNgonID_Trangchu(int imgMonNgonID_Trangchu) {
        this.imgMonNgonID_Trangchu = imgMonNgonID_Trangchu;
    }

    public String getPhanTram_Trangchu() {
        return phanTram_Trangchu;
    }

    public void setPhanTram_Trangchu(String phanTram_Trangchu) {
        this.phanTram_Trangchu = phanTram_Trangchu;
    }

    public String getTenMon_Trangchu() {
        return tenMon_Trangchu;
    }

    public void setTenMon_Trangchu(String tenMon_Trangchu) {
        this.tenMon_Trangchu = tenMon_Trangchu;
    }

    public String getGiaCu_Trangchu() {
        return giaCu_Trangchu;
    }

    public void setGiaCu_Trangchu(String giaCu_Trangchu) {
        this.giaCu_Trangchu = giaCu_Trangchu;
    }

    public String getGiaMoi_Trangchu() {
        return giaMoi_Trangchu;
    }

    public void setGiaMoi_Trangchu(String giaMoi_Trangchu) {
        this.giaMoi_Trangchu = giaMoi_Trangchu;
    }
}
