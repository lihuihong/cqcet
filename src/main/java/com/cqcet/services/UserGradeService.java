package com.cqcet.services;

import com.cqcet.dao.UserGradeMapper;
import com.cqcet.dao.UserMapper;
import com.cqcet.entity.UserGrade;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 那个谁 on 2018/9/30.
 */
@Service("UserGradeService")
public class UserGradeService {

    @Autowired
    private UserGradeMapper userGradeMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户组
     * @return
     */
    public List<UserGrade> list(){
        return userGradeMapper.list();
    }

    /**
     * 保存用户组
     */
    public void save(List<UserGrade> userGradeList) throws LException {
        for (UserGrade userGrade : userGradeList) {
            //判断试需要插入还是更新
            if (StringUtils.isEmpty(userGrade.getId())){
                //判断用户组名是否重复
                int count = userGradeMapper.countByName(userGrade.getName(),null);
                if (count > 0){
                    throw new LException("组头衔已存在");
                }
                //判断该积分是否重复
                count  =userGradeMapper.countByIntegral(userGrade.getIntegral(),null);
                if (count > 0){
                    throw new LException("最低积分已存在");
                }
                // 插入
                userGradeMapper.insert(userGrade);
            }else {
                //更新
                //判断用户组名是否重复
                int count = userGradeMapper.countByName(userGrade.getName(),userGrade.getId());
                if (count > 0){
                    throw new LException("组头衔已存在");
                }
                //判断该积分是否重复
                count  =userGradeMapper.countByIntegral(userGrade.getIntegral(),userGrade.getId());
                if (count > 0){
                    throw new LException("积分已存在");
                }
                // 插入
                userGradeMapper.update(userGrade);
            }
        }
    }

    /**
     * 删除用户组
     * @param idArr
     * @throws LException
     */
    public void delete(String[] idArr) throws LException {
        // 判断该用户组id有没有被使用
        for (int i = 0; i < idArr.length; i++) {
            int nCount = userMapper.countByGradeId(idArr[i]);
            if (nCount > 0) {
                // 被占用了，禁止删除
                throw new LException("该用户组被占用!!!");
            }
        }
        userGradeMapper.delete(idArr);
    }
}
