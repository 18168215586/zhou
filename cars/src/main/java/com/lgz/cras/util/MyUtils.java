package com.lgz.cras.util;

import com.lgz.cras.pojo.User;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 通用工具包
 * @author 吴彦祖
 *
 */
public class MyUtils {
	
	private static final String DATE_REGX="yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 判断是否是闰年
	 * @param year	四位数年份
	 * @return	是否闰年
	 */
	public static boolean isYear(int year){
		return (year%4==0&&year%100!=0)||year%400==0;
	}
	
	/**
	 * 根据传递的文件名获取文件类型
	 * @param fileName	文件名
	 * @return  文件类型
	 */
	public static String getFileType(String fileName){
		String[] fs=fileName.split("\\.");
		return fs[fs.length-1];
	}
	
	/**
	 * 获取指定格式的当前系统时间
	 * @param regx	指定的格式
	 * @return 字符串格式的时间
	 */
	public static String getNowTime(String regx){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(regx);
		return sdf.format(date);
	}
	
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static String getNowTime(){
		return getNowTime(DATE_REGX);
	}
	
	/**
	 * 获取当前系统时间，返回date对象
	 * @return
	 */
	public static Date getNowDate(String regx,String time){
		SimpleDateFormat sdf=new SimpleDateFormat(regx);
		Date d=null;
		try{
			d=sdf.parse(time);
		}catch (Exception e) {
			System.out.println("日期格式转化出错！");
		}finally{
			System.out.println("不管有没有问题，我都得运行");
		}
		return d;
	}

	/**
	 * 根据字符串类型时间，转换Date类型时间
	 * @param time
	 * @return
	 */
	public static Date getNowDate(String time){
		return getNowDate(DATE_REGX, time);
	}

	/**
	 *
	 * @param num1
	 * @param num2
	 * @return
	 * @throws Exception
	 */

	
	public static int mekeNum(int num1,int num2) throws Exception{
		if(num2==0){
			throw new Error("除数不能为0");
		}
		int result = 0;
		try {
			result = num1/num2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static int getRandom(int max,int min){
		return (int)(Math.floor(Math.random()*(max-min+1))+min);
	}



	public static void delFile(String path){
		//实例化file对象
		File file=new File(path);
		//file对象是否存在硬盘上
		if(file.exists()){
			if(file.isFile()){
				file.delete();//文件可以直接删除
			}else{
				File[] fs=file.listFiles();
				for(File f:fs){//遍历所有文件对象
					delFile(f.getAbsolutePath());//调取自己的方法   递归算法
				}
				//递归的for循环结束，表示当前目录为空，则可以直接删除
				file.delete();
			}
		}
		System.out.println("删除完毕！");
	}

	public static boolean createFile(String path){
		File file =new File(path);
		if(file.exists()){
			return  true;
		}
		return file.mkdirs();
	}

	public static void copyFile(String iPath,String oPath) {
		//实例化被复制的对象
		File iFile = new File(iPath);
		if (iFile.exists()) {
			if (iFile.isFile()) {
				try {
					FileInputStream fis = new FileInputStream(iPath);
					FileOutputStream fos = new FileOutputStream(oPath);
					byte[] buff = new byte[1024 * 1024];
					int data;
					while ((data = fis.read(buff))!=-1){
						fos.write(buff, 0, data);
					}
					fos.close();
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				File oFile = new File(oPath);
				if (!oFile.exists()) {
					oFile.mkdirs();
				}
				File[] fs = iFile.listFiles();
				for (File f : fs) {

					copyFile(f.getAbsolutePath(),oPath+"/"+f.getName());
				}
			}

		} else {
			System.out.println("目标不存在");
		}
	}

	public static Boolean isEmpty(String str){
		return str==null||str.length()==0;
	}

	public static Boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	public static String getUUID(){
		return UUID.randomUUID().toString();
	}




	public static String getLoginName(HttpSession session){
		User user= (User) session.getAttribute("loginUser");
		if (user!=null){
			return user.getRealname();
		}
		return null;
	}

	public static Integer getLoginPowerId(HttpSession session){
		User user= (User) session.getAttribute("loginUser");
		if (user!=null){
			return user.getPowerid();
		}
		return null;
	}


	public static User getLoginUser(HttpSession session){
		User user= (User) session.getAttribute("loginUser");
		if (user!=null){
			return user;
		}
		return new User();
	}

	public static void main(String[] args){
		for (int i=0;i<20;i++){
			System.out.println(getUUID());
		}
	}
}

