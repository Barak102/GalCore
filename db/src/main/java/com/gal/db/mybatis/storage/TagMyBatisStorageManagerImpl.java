package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.gal.entities.Tag;
import gap.app.contracts.TagStorageManager;

@Service("TagMyBatisStorageManagerImpl")
public class TagMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements TagStorageManager {
    @Override
    public Collection<Tag> getByOrderId(int orderId) {


        /*
         * Steps:
         * 1. insert to tag table the missing tags
         * 2. insert to ordertag table the tags ids
         */


        SqlSession sqlSession = openSqlSession();
        Collection<Tag> tags = sqlSession.selectList("Tag.getByOrderId", orderId);
        sqlSession.commit();
        sqlSession.close();
        return tags;
    }

    @Override
    public void saveTagNamesToOrder(int orderId, String[] tags) {
        SqlSession sqlSession = openSqlSession();
        try {
            Collection<Tag> existedTags = sqlSession.selectList("Tag.getByNames", tags);
            Collection<String> insertedTags = new ArrayList<>();
            for (Object t : Arrays.stream(tags).filter(t -> (existedTags.stream().filter(e -> e.getName().equals(t))).count() <= 0).toArray()) {
                insertedTags.add((String) t);
            }
            if (insertedTags.size() > 0) {
                sqlSession.insert("Tag.insertTagNames", insertedTags);
            }
            Collection<Tag> tagsSelect = sqlSession.selectList("Tag.getByNames", tags);
            tagsSelect.forEach(t -> t.setOrderId(orderId));

            Collection<Tag> includedTags = sqlSession.selectList("Tag.getByOrderId", orderId);

            Collection<Tag> insertedTagsToOrder = new ArrayList<>();
            for (Tag tag : tagsSelect) {
                if (includedTags.stream().filter(t -> t.getId() == tag.getId()).count() == 0) {
                    insertedTagsToOrder.add(tag);
                }
            }

            if (insertedTagsToOrder.size() > 0) {
                sqlSession.insert("Tag.insertTagsToOrder", insertedTagsToOrder);
            }
            sqlSession.commit();

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }

    }

    @Override
    public void deleteTagNamesFromOrder(int orderId, String[] tags) {
        SqlSession sqlSession = openSqlSession();
        Collection<Tag> existedTags = sqlSession.selectList("Tag.getByNames", tags);
        if (existedTags.size() > 0) {
            sqlSession.delete("Tag.deleteTagsFromOrder", existedTags);
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int save(Tag obj) throws IOException {
        SqlSession sqlSession = openSqlSession();
        if (obj.getId() > 0) {
            sqlSession.update("Tag.update", obj);
        } else {
            sqlSession.insert("Tag.insert", obj);
        }
        sqlSession.commit();
        sqlSession.close();
        return obj.getId();
    }

    @Override
    public Tag getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        Tag tag = sqlSession.selectOne("Tag.getById", id);
        sqlSession.commit();
        sqlSession.close();
        return tag;
    }

    @Override
    public Collection<Tag> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<Tag> tags = sqlSession.selectList("Tag.getByIds", ids);
        sqlSession.commit();
        sqlSession.close();
        return tags;
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.delete("Tag.deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<Tag> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<Tag> tags = sqlSession.selectList("Tag.getAll");
        sqlSession.commit();
        sqlSession.close();
        return tags;
    }


}
