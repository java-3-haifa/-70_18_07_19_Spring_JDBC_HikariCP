package com.telran.jdbcforumexample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostsRepository {
    @Autowired
    DataSource source;

    public List<PostEntity> getAllPosts(){
        List<PostEntity> res = new ArrayList<>();
        try(Connection connection = source.getConnection()){
            Statement stmnt = connection.createStatement();
            ResultSet set = stmnt.executeQuery("SELECT * FROM posts");
            while(set.next()){
                PostEntity entity = new PostEntity();
                entity.id = set.getInt("id");
                entity.content = set.getString("content");
                entity.dateTime = set.getTimestamp("date_time");
                entity.userName = set.getString("username");
                res.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public PostEntity getPostById(int id){
        try(Connection connection = source.getConnection()){
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM posts WHERE id=?");
            ps.setString(1,String.valueOf(id));
            if(ps.execute()){
                ResultSet result = ps.getResultSet();
                if(result.next()){
                    PostEntity entity = new PostEntity();
                    entity.id = result.getInt("id");
                    entity.content = result.getString("content");
                    entity.dateTime = result.getTimestamp("date_time");
                    entity.userName = result.getString("username");
                    return entity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
