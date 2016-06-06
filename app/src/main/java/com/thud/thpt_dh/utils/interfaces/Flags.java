package com.thud.thpt_dh.utils.interfaces;

import java.util.ArrayList;

/**
 * Created by khanh on 5/11/2016.
 */
public class Flags {
    public static int synch_data = 0;
    public static int chosen_mon = 0;
    public static int chosen_dethi = 0;
    public static int chosen_fragment_vitri = 0;
    public static int chosen_fragment_vitri_dethi = 0;

    public static int soluong_cauhoi = 0;
    public static int vitri_cauhoi = 1;
    public static int thoigian_lambai = 0;

    public static boolean main_toan = false;
    public static boolean main_nguvan = false;
    public static boolean main_dethi = false;
    public static boolean loai_dethi = false; //Tự luận: true, Trắc nghiệm: false

    public static String machuong = Def.STRING_EMPTY;
    public static String mabaihoc = Def.STRING_EMPTY;
    public static String madethi = Def.STRING_EMPTY;
    public static String malinhvuc = Def.STRING_EMPTY;

    public static ArrayList<Integer> traloi_tracnghiem = null;
}
