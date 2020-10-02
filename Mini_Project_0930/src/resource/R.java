package resource;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controller.Controller;

public interface R {

	String title = "zephyr's Reservation App";
	String pathname = "src/resource/data.json";
	
	Font introFont = new Font("굴림", Font.ITALIC, 19);
	Font titleFont = new Font("굴림", Font.ITALIC, 30);
	Font koreanFont = new Font("굴림", Font.PLAIN, 15);
	Font koreanFont12 = new Font("굴림", Font.PLAIN, 12);
	Font koreanFont17 = new Font("굴림", Font.PLAIN, 17);
	
	int MAINFRAME_WIDTH = 800;
	int MAINFRAME_HEIGHT = 600;
	
	int SHOWFRAME_WIDTH = 1600;
	int SHOWFRAME_HEIGHT = 1024;
	
	int MAX_CENTERPANE = 35;
	
	int MAX_MONTH = 31;
	int DAYLIST_SIZE = 5;
	
	Color backgroundColor = new Color(0, 128, 128);
	Color lightGray = new Color(192, 192, 192);
	Color barColor = new Color(1, 0, 130);
	
	EtchedBorder border = new EtchedBorder(EtchedBorder.RAISED);
	
	ImageIcon img1 = new ImageIcon("icon/computer.png");
	ImageIcon img2 = new ImageIcon("icon/append.png");
	ImageIcon img3 = new ImageIcon("icon/search.png");
	ImageIcon img4 = new ImageIcon("icon/modify.png");
	ImageIcon img5 = new ImageIcon("icon/trash.png");
	
	JButton btnShow = new JButton(img1);
	JButton btnAdd = new JButton(img2);
	JButton btnSearch = new JButton(img3);
	JButton btnModify = new JButton(img4);
	JButton btnDelete = new JButton(img5);
	
	// 1, 2, 10, 31 같이 날짜로 이루어진 버튼 배열
	static ArrayList<JButton> numberBtnArray = new ArrayList<>();
	
	// 날짜별로 리스트를 담는 배열
	static ArrayList<ArrayList> monthBtnList = new ArrayList<>();
	
	
	static ArrayList<JTextField> textFieldList = new ArrayList<>();
	
	
	String[] component = {"Name : ", "Phone : ", "Table No : ", "Memo : "};
	
	String[] function = {"추가", "검색", "수정", "삭제"};
	ImageIcon[] iconArray = {img2, img3, img4, img5};
	
	JButton[] funcBtnArray = {btnAdd, btnSearch, btnModify, btnDelete};
	
	HashMap<JButton, Controller> ctrlMap = new HashMap<>(); 
	
	
}
