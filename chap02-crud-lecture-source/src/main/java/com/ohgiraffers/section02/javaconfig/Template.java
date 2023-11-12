package com.ohgiraffers.section02.javaconfig;


import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Template {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menu";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffers";

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        if(sqlSessionFactory == null) {
            Environment environment =
                    new Environment("dev"
                            , new JdbcTransactionFactory()
                            , new PooledDataSource(DRIVER, URL, USER, PASSWORD));

            Configuration configuration = new Configuration(environment);

            /* 설명. 매퍼는 인터페이스 만들고 나서 추가 */
            configuration.addMapper(MenuMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        }

        return sqlSessionFactory.openSession(false);

    }
}
