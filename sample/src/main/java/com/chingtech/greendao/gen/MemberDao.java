package com.chingtech.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.chingtech.sample.bean.Member;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MEMBER".
*/
public class MemberDao extends AbstractDao<Member, Long> {

    public static final String TABLENAME = "MEMBER";

    /**
     * Properties of entity Member.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MemberId = new Property(0, long.class, "memberId", true, "_id");
        public final static Property MemberSex = new Property(1, int.class, "memberSex", false, "MEMBER_SEX");
        public final static Property MemberNickname = new Property(2, String.class, "memberNickname", false, "MEMBER_NICKNAME");
        public final static Property MemberMobile = new Property(3, String.class, "memberMobile", false, "MEMBER_MOBILE");
    }


    public MemberDao(DaoConfig config) {
        super(config);
    }
    
    public MemberDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MEMBER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: memberId
                "\"MEMBER_SEX\" INTEGER NOT NULL ," + // 1: memberSex
                "\"MEMBER_NICKNAME\" TEXT," + // 2: memberNickname
                "\"MEMBER_MOBILE\" TEXT UNIQUE );"); // 3: memberMobile
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MEMBER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Member entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getMemberId());
        stmt.bindLong(2, entity.getMemberSex());
 
        String memberNickname = entity.getMemberNickname();
        if (memberNickname != null) {
            stmt.bindString(3, memberNickname);
        }
 
        String memberMobile = entity.getMemberMobile();
        if (memberMobile != null) {
            stmt.bindString(4, memberMobile);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Member entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getMemberId());
        stmt.bindLong(2, entity.getMemberSex());
 
        String memberNickname = entity.getMemberNickname();
        if (memberNickname != null) {
            stmt.bindString(3, memberNickname);
        }
 
        String memberMobile = entity.getMemberMobile();
        if (memberMobile != null) {
            stmt.bindString(4, memberMobile);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Member readEntity(Cursor cursor, int offset) {
        Member entity = new Member( //
            cursor.getLong(offset + 0), // memberId
            cursor.getInt(offset + 1), // memberSex
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // memberNickname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // memberMobile
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Member entity, int offset) {
        entity.setMemberId(cursor.getLong(offset + 0));
        entity.setMemberSex(cursor.getInt(offset + 1));
        entity.setMemberNickname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMemberMobile(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Member entity, long rowId) {
        entity.setMemberId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Member entity) {
        if(entity != null) {
            return entity.getMemberId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Member entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
