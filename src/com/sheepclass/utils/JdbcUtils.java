
package com.sheepclass.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * jabc工具类
 * 
 * @author azhu
 *
 */
public final class JdbcUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	static {
		try {
			Properties ps = new Properties();
			InputStream instream = JdbcUtils.class.getResourceAsStream("/db.properties");
			ps.load(instream); //读入配置文件
			driver = ps.getProperty("driver");
			url = ps.getProperty("url");
			user = ps.getProperty("user");
			password = ps.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(driver);//创建类
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 与数据库取得连接
	 * 
	 * @return Connection
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 查询列表
	 * 
	 * @param clazz
	 *            类名
	 * @param sql
	 *            数据库语句
	 * @return 返回一个List
	 */
	public static List getList(Class clazz, String sql) {
		List list = new ArrayList();
		Connection conn = getConn();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();// 执行查询
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) { // 当查询结果还有下一条继续执行
				Object obj = clazz.newInstance();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					System.out.println( rs.getObject(i)+"------");
					System.out.println( metaData.getColumnName(i));
					
					BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
				}
				list.add(obj); // 向链表中加入对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {//关闭连接
			close(rs, st, conn);
		}
		return list;
	}

	/**
	 * 获取单个结果
	 * 
	 * @param clazz
	 *            类名
	 * @param sql
	 *            数据库语句
	 * @return 对象
	 */
	public static Object getObjectById(Class clazz, String sql,Object id) {
		Connection conn = getConn();
		PreparedStatement st = null;
		ResultSet rs = null;
		Object obj = null;
		try {
			st = conn.prepareStatement(sql);
			st.setObject(1,id);
			rs = st.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			if (rs.next()) { // 有结果
				obj = clazz.newInstance(); // 创建实例
				for (int i = 1; i <= metaData.getColumnCount(); i++) { // 横向读入（按列读入）
					BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			close(rs, st, conn);
		}
		return obj;
	}

	/**
	 * 获取结果数量
	 * 
	 * @param sql
	 *            查询语句
	 * @return Int
	 */
	public static int getListCount(String sql) {
		int result = 0;
		Connection conn = getConn();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();// 执行查询语句
			while (rs.next()) {
				result++;// 数量+1
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, st, conn);
		}
		return result;
	}

	/**
	 * 执行数据库语句
	 * 
	 * @param sql
	 *            数据库语句
	 * @param ps
	 *            参数
	 * @return 受影响的行数
	 */
	public static int executeSQL(String sql, Object... ps) {
		Connection conn = getConn();
		int executeUpdate = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(sql);
			for (int i = 1; i <= ps.length; i++) {// 读入参数
				prepareStatement.setObject(i, ps[i - 1]);
			}
			executeUpdate = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, prepareStatement, conn);
		}
		return executeUpdate;
	}
	
	public static void test(){
		System.out.println("test");
	}

	/**
	 * 建立事务
	 * 
	 * @param sqlArray
	 *           
	 */
	public static void executeTran(String... sqlArray) {
		Connection conn = getConn();
		PreparedStatement prepareStatement = null;
		try {
			conn.setAutoCommit(false);// 关闭自动提交
			if (sqlArray.length > 0) {
				for (String sql : sqlArray) {
					prepareStatement = conn.prepareStatement(sql);
					prepareStatement.execute();
				}
			}
			conn.commit();// 提交
			conn.setAutoCommit(true);// 开启自动提交
		} catch (SQLException e) {
			try {
				conn.rollback();// 回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			close(null, prepareStatement, conn);
		}
	}

	/**
	 *关闭数据库连接
	 * @param rs 
	 * @param st 
	 * @param conn 
	 */
	private static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}		
			if (st != null) {

				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
