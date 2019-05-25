import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MyDB {
public static void main(String[] args) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn=null;
		cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?characterEncoding=UTF-8",
                "root", "admin");
		cn.setAutoCommit(false);
		java.sql.Statement st=cn.createStatement();
		st.execute("CREATE TABLE employee (emno varchar(4),name varchar(8),sex varchar(2),salary float) DEFAULT CHARSET=utf8;");
		st.execute("insert into employee values('1001','张强','男','675.20')");
		st.execute("insert into employee values('1004','李香','女','842.00')");
		st.execute("insert into employee values('1001','王大山','男','765.00')");
		st.execute("insert into employee values('1001','赵玉花','女','690.00')");
		ResultSet rs1=st.executeQuery("SELECT *FROM employee where sex='男'");
		System.out.println("（筛选出）男员工信息如下：");
		while(rs1.next()) {
			StringBuffer build=new StringBuffer(rs1.getString(1));
			build.append("\t");
			build.append(rs1.getString(2));
			build.append("\t");
			build.append(rs1.getString(3));
			build.append("\t");
			build.append(rs1.getFloat("salary"));
			System.out.println(build.toString());
		
		}
		System.out.println("插入邢雪花信息后员工信息表如下：");
		st.execute("insert into employee values('2017','邢雪花','女','650.00')");
		ResultSet rs2=st.executeQuery("SELECT * FROM employee");
		while(rs2.next()) {
			StringBuffer build=new StringBuffer(rs2.getString(1));
			build.append("\t");
			build.append(rs2.getString(2));
			build.append("\t");
			build.append(rs2.getString(3));
			build.append("\t");
			build.append(rs2.getFloat("salary"));
			System.out.println(build.toString());
			
		}
		st.execute("UPDATE employee SET salary=900 WHERE emno='2017'");
		System.out.println("（修改工号2017员工工资900后）员工信息如下：");
		ResultSet rs3=st.executeQuery("SELECT * FROM employee");
		while(rs3.next()) {
			StringBuffer build=new StringBuffer(rs3.getString(1));
			build.append("\t");
			build.append(rs3.getString(2));
			build.append("\t");
			build.append(rs3.getString(3));
			build.append("\t");
			build.append(rs3.getFloat("salary"));
			System.out.println(build.toString());
		}
		st.execute("drop table employee");
		System.out.println("*********************");
		System.out.println("操作结束已将数据库表employee删除");
		st.close();
		rs1.close();
		rs2.close();
		rs3.close();
		cn.commit();
		cn.close();
	} catch (ClassNotFoundException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
}
