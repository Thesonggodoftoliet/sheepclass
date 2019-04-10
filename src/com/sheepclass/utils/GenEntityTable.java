package com.sheepclass.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenEntityTable {

    private static final String PROPERTIES_NAME = "/db.properties";

    private static final String PACKAGE_OUT_PATH = "com.sheepclass.entity";// 指定实体生成所在包的路径



    private static String tableName = "";// 表名

    private static String remarkes = "";// 数据库表注释

    private static LinkedList<Map> resultCol = null;// 列信息

    private static boolean utilPackage = false; // 是否需要导入包java.util.*

    private static boolean sqlPackage = false; // 是否需要导入包java.sql.*

    private static Connection connection = null;

    private static ResultSet resultSet = null;


    public GenEntityTable() {

        super();

    }


    public GenEntityTable(final String driver, final String url, final String name, final String pass) {

        try {

            Properties props = new Properties();

            props.setProperty("user", name);

            props.setProperty("password", pass);

            props.setProperty("remarks", "true"); // 设置可以获取remarks信息

            props.setProperty("useInformationSchema", "true");// 设置可以获取tables remarks信息


            // 创建连接

            Class.forName(driver);

            // getConnection = DriverManager.getConnection(URL, NAME, PASS);

            connection = (Connection) DriverManager.getConnection(url, props);

            System.out.println("数据库连接成功");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("数据库驱动加载或连接异常！");

            e.printStackTrace();

        }

    }


    /**
     * <p>Title: 读取配置文件连接数据库</p>
     *
     * <p>Description: </p>
     *
     * @author H.Yang
     * @date 2018年3月12日
     */

    public static void init() throws IOException {

        try {

            Properties properties = new Properties();

            InputStream in = GenEntityTable.class.getResourceAsStream("/db.properties");

            properties.load(in);


            Properties props = new Properties();

            props.setProperty("user", properties.getProperty("user"));

            props.setProperty("password", properties.getProperty("password"));

            props.setProperty("remarks", "true"); // 设置可以获取remarks信息

            props.setProperty("useInformationSchema", "true");// 设置可以获取tables remarks信息


            // 创建连接

            Class.forName(properties.getProperty("driver"));

            connection = (Connection) DriverManager.getConnection(properties.getProperty("url"), props);

            System.out.println("数据库连接成功");

            in.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }


    }


    /**
     * <p>Title: 获取指定表的基本信息：字段名、字段类型、字段注释</p>
     *
     * <p>Description: 自带关闭连接</p>
     *
     * @author H.Yang
     * @date 2018年3月12日
     */

    private void startTable() {

        try {

            resultCol = new LinkedList<>();

            Map<String, Object> map = null;

            resultSet = (ResultSet) connection.getMetaData().getTables(null, "%", tableName, new String[]{"TABLE"});

            while (resultSet.next()) {

                remarkes = resultSet.getString("REMARKS");


                ResultSet rs = (ResultSet) connection.getMetaData().getColumns(null, "%", resultSet.getString("TABLE_NAME").toUpperCase(), "%");

                while (rs.next()) {

                    map = new HashMap<>();

                    map.put("columnName", rs.getString("COLUMN_NAME"));

                    map.put("remarks", rs.getString("REMARKS"));

                    map.put("dbType", rs.getString("TYPE_NAME"));

                    map.put("valueType", sqlType2JavaType(rs.getString("TYPE_NAME")));


                    if (rs.getString("TYPE_NAME").equalsIgnoreCase("datetime")) {

                        utilPackage = true;

                    }

                    if (rs.getString("TYPE_NAME").equalsIgnoreCase("image") || rs.getString("TYPE_NAME").equalsIgnoreCase("text")) {

                        sqlPackage = true;

                    }

                    resultCol.add(map);

                }

                if (rs != null) {

                    rs.close();

                }

            }


            // 在内存中生成代码

            String content = parse(tableName);

            // 写入到文件中

            File directory = new File("");

            String outputPath = directory.getAbsolutePath() + "/src/main/java/" + PACKAGE_OUT_PATH.replace(".", "/") + "/"

                    + initcap(tableName) + ".java";

            System.out.println("写出的路径:" + outputPath);

            // 创建文件

            File file = new File(outputPath);

            if (!file.exists()) {

                file.createNewFile();

            }

            // 写出到硬盘

            FileWriter fw = new FileWriter(file);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(content);

            pw.flush();

            pw.close();

        } catch (SQLException | IOException e) {

            e.printStackTrace();

        } finally {

            this.close();

        }

    }


    /**
     * <p>Title: 获取当前是数据库下的所有表的基本信息：字段名、字段类型、字段注释</p>
     *
     * <p>Description: </p>
     *
     * @author H.Yang
     * @date 2018年3月13日
     */

    private void startMultitermTable() {

        try {

            Map<String, Object> map = null;

            resultSet = (ResultSet) connection.getMetaData().getTables(null, "%", null, new String[]{"TABLE"});

            while (resultSet.next()) {

                resultCol = new LinkedList<>();

                tableName = resultSet.getString("TABLE_NAME");

                remarkes = resultSet.getString("REMARKS");

                ResultSet rs = (ResultSet) connection.getMetaData().getColumns(null, "%", tableName.toUpperCase(), "%");

                while (rs.next()) {

                    map = new HashMap<>();

                    map.put("columnName", rs.getString("COLUMN_NAME"));

                    map.put("remarks", rs.getString("REMARKS"));

                    map.put("dbType", rs.getString("TYPE_NAME"));

                    map.put("valueType", sqlType2JavaType(rs.getString("TYPE_NAME")));


                    if (rs.getString("TYPE_NAME").equalsIgnoreCase("datetime")) {

                        utilPackage = true;

                    }

                    if (rs.getString("TYPE_NAME").equalsIgnoreCase("image") || rs.getString("TYPE_NAME").equalsIgnoreCase("text")) {

                        sqlPackage = true;

                    }

                    resultCol.add(map);

                }

                if (rs != null) {

                    rs.close();

                }


                // 在内存中生成代码

                String content = parse(transVar(tableName));

                // 写入到文件中

                File directory = new File("");

                String outputPath = directory.getAbsolutePath() + "/src/" + PACKAGE_OUT_PATH.replace(".", "/") + "/"

                        + transVar(tableName) + ".java";

                System.out.println("写出的路径:" + outputPath);

                // 创建文件

                File file = new File(outputPath);

                if (!file.exists()) {

                    file.createNewFile();

                }

                // 写出到硬盘

                FileWriter fw = new FileWriter(file);

                PrintWriter pw = new PrintWriter(fw);

                pw.println(content);

                pw.flush();

                pw.close();

            }


        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            this.close();

        }

    }


    /**
     * <p>Title: 关闭数据库连接</p>
     *
     * <p>Description: </p>
     *
     * @author H.Yang
     * @date 2018年3月12日
     */

    private static void close() {

        try {

            if (resultSet != null) {

                resultSet.close();

            }

            if (connection != null) {

                connection.close();

            }

            System.out.println("数据库连接关闭");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private String parse(String tablename) {

        StringBuffer sb = new StringBuffer();

        sb.append("package " + PACKAGE_OUT_PATH + ";\r\n");

        sb.append("\r\n");

        // 判断是否导入工具包

        if (utilPackage) {

            sb.append("import java.util.Date;\r\n");

        }

        if (sqlPackage) {

            sb.append("import java.sql.*;\r\n");

        }

        // 注释部分

        sb.append("\r\n");

        sb.append("/**\r\n");

        sb.append(" * <p>Title: " + remarkes + "</p>\r\n");

        sb.append(" * <p>Description: </p>\r\n");

        sb.append(" * \r\n");

        sb.append(" * \r\n");

        sb.append(" * @date " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\r\n");

        sb.append(" */ \r\n");

        // 实体部分

        sb.append("public class " + tablename + "{\r\n");

        processAllAttrs(sb);// 属性

        processAllMethod(sb);// get set方法

        sb.append("}");


        return sb.toString();

    }


    /**
     * <p>Title: 生成所有属性 </p>
     *
     * <p>Description: </p>
     *
     * @param sb
     * @author H.Yang
     * @date 2018年3月12日
     */

    private void processAllAttrs(StringBuffer sb) {

        // 列名集合

        for (Map<String, String> mapAttr : resultCol) {

            if (StringUtils.isNotBlank(mapAttr.get("remarks"))) {

                sb.append("\t// " + mapAttr.get("remarks") + "\r\n");

            }

            sb.append("\tprivate " + mapAttr.get("valueType") + " " + defineVar(mapAttr.get("columnName")) + ";\r\n");

        }

    }


    /**
     * <p>Title: 生成所有方法 </p>
     *
     * <p>Description: </p>
     *
     * @param sb
     * @author H.Yang
     * @date 2018年3月12日
     */

    private void processAllMethod(StringBuffer sb) {


        for (Map<String, String> mapMethod : resultCol) {

            // SET

            sb.append("\r\n");

            if (StringUtils.isNotBlank(mapMethod.get("remarks"))) {

                sb.append("\t/**\r\n");

                sb.append("\t * " + mapMethod.get("remarks") + "\r\n");

                sb.append("\t */\r\n");

            }

            sb.append("\tpublic void set" + transVar(mapMethod.get("columnName")) + "(" + mapMethod.get("valueType") + " "

                    + defineVar(mapMethod.get("columnName")) + ") {\r\n");

            sb.append("\t\tthis." + defineVar(mapMethod.get("columnName")) + " = " + defineVar(mapMethod.get("columnName")) + ";\r\n");

            sb.append("\t}\r\n");


            // GET

            sb.append("\r\n");

            if (StringUtils.isNotBlank(mapMethod.get("remarks"))) {

                sb.append("\t/**\r\n");

                sb.append("\t * " + mapMethod.get("remarks") + "\r\n");

                sb.append("\t */\r\n");

            }

            sb.append("\tpublic " + mapMethod.get("valueType") + " get" + initcap(transVar(mapMethod.get("columnName"))) + "() {\r\n");

            sb.append("\t\treturn " + defineVar(mapMethod.get("columnName")) + ";\r\n");

            sb.append("\t}\r\n");

        }

    }


    /**
     * <p>Title: 将输入字符串的首字母改成大写</p>
     *
     * <p>Description: </p>
     *
     * @param str
     * @return
     * @author H.Yang
     * @date 2018年3月12日
     */

    private String initcap(String str) {

        char[] ch = str.toCharArray();

        if (ch[0] >= 'a' && ch[0] <= 'z') {

            ch[0] = (char) (ch[0] - 32);

        }

        return new String(ch);

    }


    /**
     * <p>Title: 用于生成get/set方法时  </p>
     *
     * <p>Description: 第一个字母大写，“_”后面一个字母大写，并去掉“_”</p>
     *
     * @param str
     * @return
     * @author H.Yang
     * @date 2018年3月12日
     */

    private String transVar(String str) {

        StringBuffer sb = new StringBuffer(str.toLowerCase());

        String sign = "_";

        int index = 0;

        while ((index = sb.indexOf(sign, index)) != -1) {

            sb.replace(index, (index + sign.length()), "");


            char[] ch = new String(sb).toCharArray();

            if (ch[0] >= 'a' && ch[0] <= 'z') {

                ch[0] = (char) (ch[0] - 32);

            }

            if (index != 0 && index != ch.length) {

                ch[index] = (char) (ch[index] - 32);

            }

            sb = new StringBuffer(new String(ch));

            index = index + sign.length();

        }

        return sb.toString();

    }


    /**
     * <p>Title: 用于定义变量名  </p>
     *
     * <p>Description: 首字母小写，“_”后面一个字母大写，并去掉“_”</p>
     *
     * @param str
     * @return
     * @author H.Yang
     * @date 2018年3月12日
     */

    private String defineVar(String str) {

        StringBuffer sb = new StringBuffer(str.toLowerCase());

        String sign = "_";

        int index = 0;

        while ((index = sb.indexOf(sign, index)) != -1) {

            sb.replace(index, (index + sign.length()), "");

            char[] ch = new String(sb).toCharArray();

            if (ch[0] >= 'a' && ch[0] <= 'z' && index == 0) {

                ch[0] = (char) (ch[0] - 32);

            }

            if (index != 0 && index != ch.length) {

                ch[index] = (char) (ch[index] - 32);

            }

            sb = new StringBuffer(new String(ch));

            index = index + sign.length();

        }

        return sb.toString();

    }


    /**
     * <p>Title: 获得列的数据类型 </p>
     *
     * <p>Description: </p>
     *
     * @param sqlType
     * @return
     * @author H.Yang
     * @date 2018年3月12日
     */

    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {

            return "boolean";

        } else if (sqlType.equalsIgnoreCase("tinyint")) {

            return "byte";

        } else if (sqlType.equalsIgnoreCase("smallint")) {

            return "short";

        } else if (sqlType.equalsIgnoreCase("int")) {

            return "int";

        } else if (sqlType.equalsIgnoreCase("bigint unsigned")) {

            return "long";

        } else if (sqlType.equalsIgnoreCase("float")) {

            return "float";

        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")

                || sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {

            return "double";

        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar")

                || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("text")) {

            return "String";

        } else if (sqlType.equalsIgnoreCase("datetime")) {

            return "Date";

        } else if (sqlType.equalsIgnoreCase("image")) {

            return "Blod";

        }


        return null;

    }


    public static void main(String[] args) throws IOException {


        // GenEntityTable entity = new GenEntityTable("com.mysql.jdbc.Driver",

        // "jdbc:mysql://localhost:3306/springwind?useUnicode=true&characterEncoding=utf-8", "root", "admini");

        GenEntityTable entity = new GenEntityTable();

        entity.init();

        entity.startMultitermTable();

        // entity.startTable();


    }

}
